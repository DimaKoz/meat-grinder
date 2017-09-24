package com.kozhevin.rootchecks.ui;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kozhevin.rootchecks.R;
import com.kozhevin.rootchecks.data.CheckInfo;
import com.kozhevin.rootchecks.util.ChecksHelper;


public class ResultItemHolder extends RecyclerView.ViewHolder {

    private static Bitmap sNonCheck = null;
    private static Bitmap sFound = null;
    private static Bitmap sOk = null;

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
        if (sNonCheck == null || sNonCheck.isRecycled()) {
            sNonCheck = BitmapFactory.decodeResource(res, R.drawable.ic_verified_grey);
        }
        if (sFound == null || sFound.isRecycled()) {
            sFound = BitmapFactory.decodeResource(res, R.drawable.ic_verified_red);
        }
        if (sOk == null || sOk.isRecycled()) {
            sOk = BitmapFactory.decodeResource(res, R.drawable.ic_verified_green);
        }

        String desc = res.getString(ChecksHelper.getCheckStringId(mCheckInfo.getTypeCheck()));
        title.setText(desc);
        if (mCheckInfo.getState() == null) {
            icon.setImageBitmap(sNonCheck);
        } else if (mCheckInfo.getState() == Boolean.TRUE) {
            icon.setImageBitmap(sFound);
        } else {
            icon.setImageBitmap(sOk);
        }

    }


}
