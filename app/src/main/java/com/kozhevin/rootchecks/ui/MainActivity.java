/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017  Dmitrii Kozhevin <kozhevin.dima@gmail.com>
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 *  and associated documentation files (the “Software”), to deal in the Software without
 *  restriction, including without limitation the rights to use, copy, modify, merge, publish,
 *  distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the
 *  Software is furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all copies or
 *  substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 *  BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 *  DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 *  FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package com.kozhevin.rootchecks.ui;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.kozhevin.rootchecks.R;
import com.kozhevin.rootchecks.data.CheckInfo;
import com.kozhevin.rootchecks.data.TotalResult;
import com.kozhevin.rootchecks.util.CheckTask;
import com.kozhevin.rootchecks.util.IChecksResultListener;

import java.util.ArrayList;

import static com.kozhevin.rootchecks.constant.GeneralConst.CH_STATE_CHECKED_ERROR;
import static com.kozhevin.rootchecks.constant.GeneralConst.CH_STATE_CHECKED_ROOT_DETECTED;
import static com.kozhevin.rootchecks.constant.GeneralConst.CH_STATE_CHECKED_ROOT_NOT_DETECTED;
import static com.kozhevin.rootchecks.constant.GeneralConst.CH_STATE_STILL_GOING;
import static com.kozhevin.rootchecks.constant.GeneralConst.CH_STATE_UNCHECKED;
import static com.kozhevin.rootchecks.constant.GeneralConst.GITHUB;

public class MainActivity extends AppCompatActivity implements IChecksResultListener {

    private CheckTask mTask;
    private RecyclerView mList;
    private ImageView mResultState;
    private FloatingActionButton mFapMe; // I'm so sorry ;)
    private ProgressBar mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        mProgress = findViewById(R.id.result_progress);
        mProgress.setVisibility(View.GONE);
        mResultState = findViewById(R.id.result_image_view);
        mList = findViewById(R.id.list);
        mList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mList.setAdapter(new ResultAdapter());
        mFapMe = findViewById(R.id.fab);
        mFapMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTask != null) {
                    mTask.cancel(false);
                }
                mTask = new CheckTask(MainActivity.this, false);
                mTask.execute((Void[]) null);
            }
        });
        mTask = new CheckTask(MainActivity.this, true);
        mTask.execute((Void[]) null);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_github) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(GITHUB));
            PackageManager manager = getPackageManager();
            if (i.resolveActivity(manager) != null) {
                startActivity(i);
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onProcessStarted() {
        mResultState.setVisibility(View.INVISIBLE);
        mFapMe.hide();
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void onUpdateResult(TotalResult result) {
        if (result != null && mList.getAdapter() != null) {
            ((ResultAdapter) mList.getAdapter()).setData((ArrayList<CheckInfo>) result.getList());
        }
    }

    @Override
    public void onProcessFinished(TotalResult result) {
        if (mTask != null) {
            mTask = null;
        }
        mProgress.setVisibility(View.GONE);
        mFapMe.show();
        if (result != null) {
            ((ResultAdapter) mList.getAdapter()).setData((ArrayList<CheckInfo>) result.getList());

            switch (result.getCheckState()) {

                case CH_STATE_CHECKED_ROOT_DETECTED:
                    mResultState.setVisibility(View.VISIBLE);
                    mResultState.setImageResource(R.drawable.ic_rooted);
                    break;

                case CH_STATE_CHECKED_ROOT_NOT_DETECTED:
                    mResultState.setVisibility(View.VISIBLE);
                    mResultState.setImageResource(R.drawable.ic_notrooted);
                    break;

                case CH_STATE_STILL_GOING:
                    mResultState.setVisibility(View.INVISIBLE);
                    break;

                case CH_STATE_UNCHECKED:
                    mResultState.setVisibility(View.INVISIBLE);
                    break;

                case CH_STATE_CHECKED_ERROR:
                    mResultState.setVisibility(View.INVISIBLE);
                    break;

                default:
                    throw new IllegalStateException("Unknown state of the result");
            }
        }

    }

}
