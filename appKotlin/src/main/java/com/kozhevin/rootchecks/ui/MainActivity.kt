package com.kozhevin.rootchecks.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kozhevin.rootchecks.R
import com.kozhevin.rootchecks.constant.GeneralConst.CH_STATE_CHECKED_ERROR
import com.kozhevin.rootchecks.constant.GeneralConst.CH_STATE_CHECKED_ROOT_DETECTED
import com.kozhevin.rootchecks.constant.GeneralConst.CH_STATE_CHECKED_ROOT_NOT_DETECTED
import com.kozhevin.rootchecks.constant.GeneralConst.CH_STATE_STILL_GOING
import com.kozhevin.rootchecks.constant.GeneralConst.CH_STATE_UNCHECKED
import com.kozhevin.rootchecks.constant.GeneralConst.GITHUB
import com.kozhevin.rootchecks.data.CheckInfo
import com.kozhevin.rootchecks.data.TotalResult
import com.kozhevin.rootchecks.util.CheckTask
import com.kozhevin.rootchecks.util.IChecksResultListener
import java.util.*

class MainActivity : AppCompatActivity(), IChecksResultListener {
    private var mTask: CheckTask? = null
    private var mList: RecyclerView? = null
    private var mResultState: ImageView? = null
    private var mFab: FloatingActionButton? = null
    private var mProgress: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = ""
        setSupportActionBar(toolbar)
        mProgress = findViewById(R.id.result_progress)
        mProgress!!.visibility = View.GONE
        mResultState = findViewById(R.id.result_image_view)
        mList = findViewById(R.id.list)
        mList!!.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mList!!.adapter = ResultAdapter()
        mFab = findViewById(R.id.fab)
        mFab!!.setOnClickListener {
            mTask?.cancel(false)
            mTask = CheckTask(this@MainActivity, false)
            mTask!!.execute()
        }
        mTask = CheckTask(this@MainActivity, true)
        mTask!!.execute()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = if (item.itemId == R.id.action_github) {
        val i = Intent(Intent.ACTION_VIEW, Uri.parse(GITHUB))
        if (i.resolveActivity(packageManager) != null) {
            startActivity(i)
        }
        true
    } else {
        super.onOptionsItemSelected(item)
    }

    override fun onProcessStarted() {
        mResultState!!.visibility = View.INVISIBLE
        mFab!!.hide()
        mProgress!!.visibility = View.VISIBLE
    }

    override fun onUpdateResult(result: TotalResult) {
        if (mList!!.adapter != null) {
            (mList!!.adapter as ResultAdapter).setData(result.list as ArrayList<CheckInfo>)
        }
    }

    override fun onProcessFinished(result: TotalResult) {
        if (mTask != null) {
            mTask = null
        }
        mProgress!!.visibility = View.GONE
        mFab!!.show()
        (mList!!.adapter as ResultAdapter).setData(result.list as ArrayList<CheckInfo>)
        when (result.checkState) {
            CH_STATE_CHECKED_ROOT_DETECTED -> {
                mResultState!!.visibility = View.VISIBLE
                mResultState!!.setImageResource(R.drawable.ic_rooted)
            }
            CH_STATE_CHECKED_ROOT_NOT_DETECTED -> {
                mResultState!!.visibility = View.VISIBLE
                mResultState!!.setImageResource(R.drawable.ic_notrooted)
            }
            CH_STATE_STILL_GOING -> mResultState!!.visibility = View.INVISIBLE
            CH_STATE_UNCHECKED -> mResultState!!.visibility = View.INVISIBLE
            CH_STATE_CHECKED_ERROR -> mResultState!!.visibility = View.INVISIBLE
            else -> throw IllegalStateException("Unknown state of the result")
        }
    }
}
