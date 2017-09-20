package com.kozhevin.rootchecks.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kozhevin.rootchecks.R;
import com.kozhevin.rootchecks.annotation.CheckingMethodType;
import com.kozhevin.rootchecks.data.CheckInfo;

import static com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_BUSYBOX_BINARY;
import static com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_DANGEROUS_PROPS;
import static com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_DEV_KEYS;
import static com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_HOOKS;
import static com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_NON_RELEASE_KEYS;
import static com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_PERMISSIVE_SELINUX;
import static com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_RESETPROP;
import static com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_SUPER_USER_APK;
import static com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_SU_BINARY;
import static com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_SU_EXISTS;
import static com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_TEST_KEYS;
import static com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_UNKNOWN;
import static com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_WRONG_PATH_PERMITION;
import static com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_XPOSED;


public class ResultItemHolder extends RecyclerView.ViewHolder {

    static Bitmap sNonCheck = null;
    static Bitmap sFound = null;
    static Bitmap sOk = null;

    private final TextView title;
    private final ImageView icon;
    private CheckInfo mCheckInfo;


    public ResultItemHolder(View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.item_check_description_text_view);
        icon = itemView.findViewById(R.id.item_check_icon_image_view);

    }

    public void onBind(@NonNull CheckInfo checkInfo, int position) {
        if(mCheckInfo != null && mCheckInfo.equals(checkInfo)) {
            return;
        }
        mCheckInfo = checkInfo;
        if(sNonCheck == null || sNonCheck.isRecycled()) {
            sNonCheck = BitmapFactory.decodeResource(itemView.getResources(), R.drawable.ic_verified_grey);
        }
        if(sFound == null || sFound.isRecycled()) {
            sFound = BitmapFactory.decodeResource(itemView.getResources(), R.drawable.ic_verified_red);
        }
        if(sOk == null || sOk.isRecycled()) {
            sOk = BitmapFactory.decodeResource(itemView.getResources(), R.drawable.ic_verified_green);
        }

        String desc = getDescription(mCheckInfo.getTypeCheck());
        title.setText(desc);
        if(mCheckInfo.getState() == null) {
            icon.setImageBitmap(sNonCheck);
        } else if(mCheckInfo.getState() == Boolean.TRUE) {
            icon.setImageBitmap(sFound);
        } else {
            icon.setImageBitmap(sOk);
        }

    }

    private String getDescription(@CheckingMethodType int typeCheck) {
        @StringRes
        int descRes;
        switch (typeCheck) {
            case CH_TYPE_TEST_KEYS:
                descRes = R.string.checks_desc_1;
                break;

            case CH_TYPE_DEV_KEYS:
                descRes = R.string.checks_desc_2;
                break;

            case CH_TYPE_NON_RELEASE_KEYS:
                descRes = R.string.checks_desc_3;
                break;

            case CH_TYPE_DANGEROUS_PROPS:
                descRes = R.string.checks_desc_4;
                break;

            case CH_TYPE_PERMISSIVE_SELINUX:
                descRes = R.string.checks_desc_5;
                break;

            case CH_TYPE_SU_EXISTS:
                descRes = R.string.checks_desc_6;
                break;

            case CH_TYPE_SUPER_USER_APK:
                descRes = R.string.checks_desc_7;
                break;

            case CH_TYPE_SU_BINARY:
                descRes = R.string.checks_desc_8;
                break;

            case CH_TYPE_BUSYBOX_BINARY:
                descRes = R.string.checks_desc_9;
                break;

            case CH_TYPE_XPOSED:
                descRes = R.string.checks_desc_10;
                break;

            case CH_TYPE_RESETPROP:
                descRes = R.string.checks_desc_11;
                break;

            case CH_TYPE_WRONG_PATH_PERMITION:
                descRes = R.string.checks_desc_12;
                break;

            case CH_TYPE_HOOKS:
                descRes = R.string.checks_desc_13;
                break;

            case CH_TYPE_UNKNOWN:
            default:
                descRes = R.string.empty;
        }
        return itemView.getContext().getResources().getString(descRes);
    }


}
