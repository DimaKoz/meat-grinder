package com.kozhevin.rootchecks.ui

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kozhevin.rootchecks.R
import com.kozhevin.rootchecks.data.CheckInfo
import java.util.ArrayList

class ResultAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val mFullData: ArrayList<CheckInfo> = ArrayList()
    fun setData(pDataList: ArrayList<CheckInfo>) {
        mFullData.clear()
        mFullData.addAll(pDataList)
        notifyDataSetChanged()
    }
    override fun getItemViewType(position: Int): Int = com.kozhevin.rootchecks.constant.GeneralConst.ITEM_CHECK_RESULT
    fun getItem(position: Int): CheckInfo = mFullData[position]
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder = ResultItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_check, parent, false))
    override fun onBindViewHolder(pHolder: RecyclerView.ViewHolder, position: Int) {
        (pHolder as ResultItemHolder).onBind(getItem(position))
    }
    override fun getItemId(position: Int): Long = mFullData[position].hashCode().toLong()
    override fun getItemCount(): Int = mFullData.size
}
