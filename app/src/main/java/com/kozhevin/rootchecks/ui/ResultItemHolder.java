package com.kozhevin.rootchecks.ui;

import android.content.res.Resources;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kozhevin.rootchecks.R;
import com.kozhevin.rootchecks.data.CheckInfo;
import com.kozhevin.rootchecks.util.ChecksHelper;

import static com.kozhevin.rootchecks.util.ChecksHelper.getFound;
import static com.kozhevin.rootchecks.util.ChecksHelper.getNonCheck;
import static com.kozhevin.rootchecks.util.ChecksHelper.getOk;


public class ResultItemHolder extends RecyclerView.ViewHolder {


    private final TextView title;
    private final ImageView icon;
    private CheckInfo mCheckInfo;


    public ResultItemHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.item_check_description_text_view);
        icon = itemView.findViewById(R.id.item_check_icon_image_view);

    }

    public void onBind(@NonNull CheckInfo checkInfo, int position) {
        if (mCheckInfo != null && mCheckInfo.equals(checkInfo)) {
            return;
        }
        mCheckInfo = checkInfo;
        Resources res = itemView.getResources();
        String desc = res.getString(ChecksHelper.getCheckStringId(mCheckInfo.getTypeCheck()));
        title.setText(desc);
        if (mCheckInfo.getState() == null) {
            icon.setImageBitmap(getNonCheck(itemView.getContext()));
        } else if (mCheckInfo.getState() == Boolean.TRUE) {
            icon.setImageBitmap(getFound(itemView.getContext()));
        } else {
            icon.setImageBitmap(getOk(itemView.getContext()));
        }

    }


}
