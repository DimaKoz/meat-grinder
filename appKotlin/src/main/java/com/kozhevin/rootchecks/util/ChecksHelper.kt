package com.kozhevin.rootchecks.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.StringRes

import com.kozhevin.rootchecks.R
import com.kozhevin.rootchecks.annotation.CheckingMethodType

import com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_BUSYBOX_BINARY
import com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_DANGEROUS_PROPS
import com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_DEV_KEYS
import com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_HOOKS
import com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_NON_RELEASE_KEYS
import com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_PERMISSIVE_SELINUX
import com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_RESETPROP
import com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_SUPER_USER_APK
import com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_SU_BINARY
import com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_SU_EXISTS
import com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_TEST_KEYS
import com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_UNKNOWN
import com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_WRONG_PATH_PERMITION
import com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_XPOSED


object ChecksHelper {

    private var mNonCheck: Bitmap? = null
    private var mFound: Bitmap? = null
    private var mOk: Bitmap? = null

    @StringRes
    fun getCheckStringId(@CheckingMethodType typeCheck: Int): Int {
        @StringRes var result = 0
        when (typeCheck) {
            CH_TYPE_TEST_KEYS -> result = R.string.checks_desc_1

            CH_TYPE_DEV_KEYS -> result = R.string.checks_desc_2

            CH_TYPE_NON_RELEASE_KEYS -> result = R.string.checks_desc_3

            CH_TYPE_DANGEROUS_PROPS -> result = R.string.checks_desc_4

            CH_TYPE_PERMISSIVE_SELINUX -> result = R.string.checks_desc_5

            CH_TYPE_SU_EXISTS -> result = R.string.checks_desc_6

            CH_TYPE_SUPER_USER_APK -> result = R.string.checks_desc_7

            CH_TYPE_SU_BINARY -> result = R.string.checks_desc_8

            CH_TYPE_BUSYBOX_BINARY -> result = R.string.checks_desc_9

            CH_TYPE_XPOSED -> result = R.string.checks_desc_10

            CH_TYPE_RESETPROP -> result = R.string.checks_desc_11

            CH_TYPE_WRONG_PATH_PERMITION -> result = R.string.checks_desc_12

            CH_TYPE_HOOKS -> result = R.string.checks_desc_13

            CH_TYPE_UNKNOWN -> result = R.string.empty
            else -> result = R.string.empty
        }
        return result
    }

    fun getNonCheck(pContext: Context): Bitmap? {
        if (mNonCheck == null || mNonCheck!!.isRecycled) {
            mNonCheck = BitmapFactory.decodeResource(pContext.resources, R.drawable.ic_verified_grey)
        }
        return mNonCheck
    }

    fun getFound(pContext: Context): Bitmap? {
        if (mFound == null || mFound!!.isRecycled) {
            mFound = BitmapFactory.decodeResource(pContext.resources, R.drawable.ic_verified_red)
        }
        return mFound
    }

    fun getOk(pContext: Context): Bitmap? {
        if (mOk == null || mOk!!.isRecycled) {
            mOk = BitmapFactory.decodeResource(pContext.resources, R.drawable.ic_verified_green)
        }
        return mOk
    }
}
