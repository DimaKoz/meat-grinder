package com.kozhevin.rootchecks.util;

import android.os.AsyncTask;

import com.kozhevin.rootchecks.BuildConfig;
import com.kozhevin.rootchecks.constant.GeneralConst;
import com.kozhevin.rootchecks.data.CheckInfo;
import com.kozhevin.rootchecks.data.TotalResult;

import java.util.ArrayList;

import static com.kozhevin.rootchecks.constant.GeneralConst.CH_STATE_CHECKED_ROOT_DETECTED;
import static com.kozhevin.rootchecks.constant.GeneralConst.CH_STATE_CHECKED_ROOT_NOT_DETECTED;
import static com.kozhevin.rootchecks.constant.GeneralConst.CH_STATE_UNCHECKED;
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
import static com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_WRONG_PATH_PERMITION;
import static com.kozhevin.rootchecks.constant.GeneralConst.CH_TYPE_XPOSED;

/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2017  Dmitrii Kozhevin <kozhevin.dima@gmail.com>
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the “Software”), to deal in the Software without
 * restriction, including without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

public class CheckTask extends AsyncTask<Void, TotalResult, TotalResult> {
    private IChecksResultListener mListener;
    private boolean mIsEmptyResult;

    public CheckTask(IChecksResultListener listener, boolean isEmptyResult) {
        mListener = listener;
        mIsEmptyResult = isEmptyResult;
    }

    @Override
    protected TotalResult doInBackground(Void[] objects) {
        TotalResult totalResult = null;

        ArrayList<CheckInfo> list = new ArrayList<>();
        CheckInfo checkInfo = new CheckInfo(null, GeneralConst.CH_TYPE_TEST_KEYS);
        list.add(checkInfo);
        checkInfo = new CheckInfo(null, GeneralConst.CH_TYPE_DEV_KEYS);
        list.add(checkInfo);
        checkInfo = new CheckInfo(null, GeneralConst.CH_TYPE_NON_RELEASE_KEYS);
        list.add(checkInfo);
        checkInfo = new CheckInfo(null, GeneralConst.CH_TYPE_DANGEROUS_PROPS);
        list.add(checkInfo);
        checkInfo = new CheckInfo(null, GeneralConst.CH_TYPE_PERMISSIVE_SELINUX);
        list.add(checkInfo);
        checkInfo = new CheckInfo(null, GeneralConst.CH_TYPE_SU_EXISTS);
        list.add(checkInfo);
        checkInfo = new CheckInfo(null, GeneralConst.CH_TYPE_SUPER_USER_APK);
        list.add(checkInfo);
        checkInfo = new CheckInfo(null, GeneralConst.CH_TYPE_SU_BINARY);
        list.add(checkInfo);
        checkInfo = new CheckInfo(null, GeneralConst.CH_TYPE_BUSYBOX_BINARY);
        list.add(checkInfo);
        checkInfo = new CheckInfo(null, GeneralConst.CH_TYPE_XPOSED);
        list.add(checkInfo);
        checkInfo = new CheckInfo(null, GeneralConst.CH_TYPE_RESETPROP);
        list.add(checkInfo);
        checkInfo = new CheckInfo(null, GeneralConst.CH_TYPE_WRONG_PATH_PERMITION);
        list.add(checkInfo);
        checkInfo = new CheckInfo(null, GeneralConst.CH_TYPE_HOOKS);
        list.add(checkInfo);
        if (isCancelled()) {
            mListener = null;
            return null;
        }
        totalResult = new TotalResult(list, CH_STATE_UNCHECKED);
        publishProgress(totalResult);
        if (!mIsEmptyResult) {
            if (!MeatGrinder.getInstance().isLibraryLoaded()) {
                totalResult = new TotalResult(list, GeneralConst.CH_STATE_CHECKED_ERROR);
                return totalResult;
            }
            boolean isFoundRoot = false;
            for (CheckInfo item : list) {
                if (isCancelled()) {
                    mListener = null;
                    return null;
                }
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    if (BuildConfig.DEBUG) {
                        e.printStackTrace();
                    }
                }
                final int type = item.getTypeCheck();
                switch (type) {
                    case GeneralConst.CH_TYPE_TEST_KEYS:
                        item.setState(MeatGrinder.getInstance().isDetectedTestKeys());
                        break;
                    case CH_TYPE_DEV_KEYS:
                        item.setState(MeatGrinder.getInstance().isDetectedDevKeys());
                        break;

                    case CH_TYPE_NON_RELEASE_KEYS:
                        item.setState(MeatGrinder.getInstance().isNotFoundReleaseKeys());
                        break;

                    case CH_TYPE_DANGEROUS_PROPS:
                        item.setState(MeatGrinder.getInstance().isFoundDangerousProps());
                        break;

                    case CH_TYPE_PERMISSIVE_SELINUX:
                        item.setState(MeatGrinder.getInstance().isPermissiveSelinux());
                        break;

                    case CH_TYPE_SU_EXISTS:
                        item.setState(MeatGrinder.getInstance().isSuExists());
                        break;

                    case CH_TYPE_SUPER_USER_APK:
                        item.setState(MeatGrinder.getInstance().isAccessedSuperuserApk());
                        break;

                    case CH_TYPE_SU_BINARY:
                        item.setState(MeatGrinder.getInstance().isFoundSuBinary());
                        break;

                    case CH_TYPE_BUSYBOX_BINARY:
                        item.setState(MeatGrinder.getInstance().isFoundBusyboxBinary());
                        break;

                    case CH_TYPE_XPOSED:
                        item.setState(MeatGrinder.getInstance().isFoundXposed());
                        break;

                    case CH_TYPE_RESETPROP:
                        item.setState(MeatGrinder.getInstance().isFoundResetprop());
                        break;

                    case CH_TYPE_WRONG_PATH_PERMITION:
                        item.setState(MeatGrinder.getInstance().isFoundWrongPathPermission());
                        break;

                    case CH_TYPE_HOOKS:
                        item.setState(MeatGrinder.getInstance().isFoundHooks());
                        break;

                }
                if (item.getState() != null && item.getState() == Boolean.TRUE && !isFoundRoot) {
                    isFoundRoot = true;
                }
                totalResult = new TotalResult(list, isFoundRoot ? CH_STATE_CHECKED_ROOT_DETECTED : CH_STATE_CHECKED_ROOT_NOT_DETECTED);
                publishProgress(totalResult);
            } //for (CheckInfo item : list)
            totalResult = new TotalResult(list, isFoundRoot ? CH_STATE_CHECKED_ROOT_DETECTED : CH_STATE_CHECKED_ROOT_NOT_DETECTED);
            return totalResult;
        }
        return totalResult;
    }

    @Override
    protected void onProgressUpdate(TotalResult... progress) {
        super.onProgressUpdate(progress);
        if (isCancelled()) {
            mListener = null;
            return;
        }
        if (progress != null && progress.length > 0 && mListener != null) {
            mListener.onUpdateResult(progress[0]);
        }
    }

    @Override
    protected void onPostExecute(TotalResult totalResult) {
        super.onPostExecute(totalResult);
        if (mListener != null) {
            mListener.onProcessFinished(totalResult);
        }
    }

    @Override
    protected void onPreExecute() {
        if (mListener != null) {
            mListener.onProcessStarted();
        }
    }
}
