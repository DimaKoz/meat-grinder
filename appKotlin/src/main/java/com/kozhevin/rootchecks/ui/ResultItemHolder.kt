package com.kozhevin.rootchecks.ui

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kozhevin.rootchecks.R
import com.kozhevin.rootchecks.data.CheckInfo
import com.kozhevin.rootchecks.util.ChecksHelper
import com.kozhevin.rootchecks.util.ChecksHelper.getFound
import com.kozhevin.rootchecks.util.ChecksHelper.getNonCheck
import com.kozhevin.rootchecks.util.ChecksHelper.getOk

class ResultItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val title: TextView = itemView.findViewById(R.id.item_check_description_text_view)
    private val icon: ImageView = itemView.findViewById(R.id.item_check_icon_image_view)
    private var mCheckInfo: CheckInfo? = null

    fun onBind(checkInfo: CheckInfo) {
        mCheckInfo = checkInfo
        mCheckInfo?.let {
            title.text = itemView.resources.getString(ChecksHelper.getCheckStringId(it.typeCheck))
            when {
                it.state == null -> icon.setImageBitmap(getNonCheck(itemView.context))
                it.state === java.lang.Boolean.TRUE -> icon.setImageBitmap(getFound(itemView.context))
                else -> icon.setImageBitmap(getOk(itemView.context))
            }
        }
    }


}
