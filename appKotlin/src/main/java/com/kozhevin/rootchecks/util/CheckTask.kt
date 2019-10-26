package com.kozhevin.rootchecks.util

import android.os.AsyncTask

import com.kozhevin.rootchecks.BuildConfig
import com.kozhevin.rootchecks.constant.GeneralConst
import com.kozhevin.rootchecks.data.CheckInfo
import com.kozhevin.rootchecks.data.TotalResult

import java.util.ArrayList

import com.kozhevin.rootchecks.constant.GeneralConst.CH_STATE_CHECKED_ROOT_DETECTED
import com.kozhevin.rootchecks.constant.GeneralConst.CH_STATE_CHECKED_ROOT_NOT_DETECTED
import com.kozhevin.rootchecks.constant.GeneralConst.CH_STATE_UNCHECKED
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
import com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_WRONG_PATH_PERMITION
import com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_XPOSED


class CheckTask(private var mListener: IChecksResultListener?, private val mIsEmptyResult: Boolean) : AsyncTask<Void, TotalResult, TotalResult>() {

    override fun doInBackground(objects: Array<Void>): TotalResult? {
        var totalResult: TotalResult? = null

        val list = ArrayList<CheckInfo>()
        var checkInfo = CheckInfo(null, GeneralConst.CH_TYPE_TEST_KEYS)
        list.add(checkInfo)
        checkInfo = CheckInfo(null, GeneralConst.CH_TYPE_DEV_KEYS)
        list.add(checkInfo)
        checkInfo = CheckInfo(null, GeneralConst.CH_TYPE_NON_RELEASE_KEYS)
        list.add(checkInfo)
        checkInfo = CheckInfo(null, GeneralConst.CH_TYPE_DANGEROUS_PROPS)
        list.add(checkInfo)
        checkInfo = CheckInfo(null, GeneralConst.CH_TYPE_PERMISSIVE_SELINUX)
        list.add(checkInfo)
        checkInfo = CheckInfo(null, GeneralConst.CH_TYPE_SU_EXISTS)
        list.add(checkInfo)
        checkInfo = CheckInfo(null, GeneralConst.CH_TYPE_SUPER_USER_APK)
        list.add(checkInfo)
        checkInfo = CheckInfo(null, GeneralConst.CH_TYPE_SU_BINARY)
        list.add(checkInfo)
        checkInfo = CheckInfo(null, GeneralConst.CH_TYPE_BUSYBOX_BINARY)
        list.add(checkInfo)
        checkInfo = CheckInfo(null, GeneralConst.CH_TYPE_XPOSED)
        list.add(checkInfo)
        checkInfo = CheckInfo(null, GeneralConst.CH_TYPE_RESETPROP)
        list.add(checkInfo)
        checkInfo = CheckInfo(null, GeneralConst.CH_TYPE_WRONG_PATH_PERMITION)
        list.add(checkInfo)
        checkInfo = CheckInfo(null, GeneralConst.CH_TYPE_HOOKS)
        list.add(checkInfo)
        if (isCancelled) {
            mListener = null
            return null
        }
        totalResult = TotalResult(list, CH_STATE_UNCHECKED)
        publishProgress(totalResult)
        if (!mIsEmptyResult) {
            if (!MeatGrinder.instance.isLibraryLoaded) {
                totalResult = TotalResult(list, GeneralConst.CH_STATE_CHECKED_ERROR)
                return totalResult
            }
            var isFoundRoot = false
            for (item in list) {
                if (isCancelled) {
                    mListener = null
                    return null
                }
                try {
                    Thread.sleep(150)
                } catch (e: InterruptedException) {
                    if (BuildConfig.DEBUG) {
                        e.printStackTrace()
                    }
                }

                val type = item.typeCheck
                when (type) {
                    GeneralConst.CH_TYPE_TEST_KEYS -> item.state = MeatGrinder.instance.isDetectedTestKeys
                    CH_TYPE_DEV_KEYS -> item.state = MeatGrinder.instance.isDetectedDevKeys

                    CH_TYPE_NON_RELEASE_KEYS -> item.state = MeatGrinder.instance.isNotFoundReleaseKeys

                    CH_TYPE_DANGEROUS_PROPS -> item.state = MeatGrinder.instance.isFoundDangerousProps

                    CH_TYPE_PERMISSIVE_SELINUX -> item.state = MeatGrinder.instance.isPermissiveSelinux

                    CH_TYPE_SU_EXISTS -> item.state = MeatGrinder.instance.isSuExists

                    CH_TYPE_SUPER_USER_APK -> item.state = MeatGrinder.instance.isAccessedSuperuserApk

                    CH_TYPE_SU_BINARY -> item.state = MeatGrinder.instance.isFoundSuBinary

                    CH_TYPE_BUSYBOX_BINARY -> item.state = MeatGrinder.instance.isFoundBusyboxBinary

                    CH_TYPE_XPOSED -> item.state = MeatGrinder.instance.isFoundXposed

                    CH_TYPE_RESETPROP -> item.state = MeatGrinder.instance.isFoundResetprop

                    CH_TYPE_WRONG_PATH_PERMITION -> item.state = MeatGrinder.instance.isFoundWrongPathPermission

                    CH_TYPE_HOOKS -> item.state = MeatGrinder.instance.isFoundHooks
                }
                if (item.state != null && item.state === java.lang.Boolean.TRUE && !isFoundRoot) {
                    isFoundRoot = true
                }
                totalResult = TotalResult(list, if (isFoundRoot) CH_STATE_CHECKED_ROOT_DETECTED else CH_STATE_CHECKED_ROOT_NOT_DETECTED)
                publishProgress(totalResult)
            } //for (CheckInfo item : list)
            totalResult = TotalResult(list, if (isFoundRoot) CH_STATE_CHECKED_ROOT_DETECTED else CH_STATE_CHECKED_ROOT_NOT_DETECTED)
            return totalResult
        }
        return totalResult
    }

    override fun onProgressUpdate(vararg progress: TotalResult) {
        super.onProgressUpdate(*progress)
        if (isCancelled) {
            mListener = null
            return
        }
        if (progress.isNotEmpty()) {
            mListener?.onUpdateResult(progress[0])
        }
    }

    override fun onPostExecute(totalResult: TotalResult) {
        super.onPostExecute(totalResult)
        mListener?.onProcessFinished(totalResult)
    }

    override fun onPreExecute() {
        mListener?.onProcessStarted()
    }
}
