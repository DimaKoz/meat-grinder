package com.kozhevin.rootchecks.util;

import android.support.annotation.StringRes;

import com.kozhevin.rootchecks.R;
import com.kozhevin.rootchecks.annotation.CheckingMethodType;

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

public class ChecksHelper {
    private ChecksHelper() {
    }

    @StringRes
    public static int getCheckStringId(@CheckingMethodType int typeCheck) {
        @StringRes int result = 0;
        switch (typeCheck) {
            case CH_TYPE_TEST_KEYS:
                result = R.string.checks_desc_1;
                break;

            case CH_TYPE_DEV_KEYS:
                result = R.string.checks_desc_2;
                break;

            case CH_TYPE_NON_RELEASE_KEYS:
                result = R.string.checks_desc_3;
                break;

            case CH_TYPE_DANGEROUS_PROPS:
                result = R.string.checks_desc_4;
                break;

            case CH_TYPE_PERMISSIVE_SELINUX:
                result = R.string.checks_desc_5;
                break;

            case CH_TYPE_SU_EXISTS:
                result = R.string.checks_desc_6;
                break;

            case CH_TYPE_SUPER_USER_APK:
                result = R.string.checks_desc_7;
                break;

            case CH_TYPE_SU_BINARY:
                result = R.string.checks_desc_8;
                break;

            case CH_TYPE_BUSYBOX_BINARY:
                result = R.string.checks_desc_9;
                break;

            case CH_TYPE_XPOSED:
                result = R.string.checks_desc_10;
                break;

            case CH_TYPE_RESETPROP:
                result = R.string.checks_desc_11;
                break;

            case CH_TYPE_WRONG_PATH_PERMITION:
                result = R.string.checks_desc_12;
                break;

            case CH_TYPE_HOOKS:
                result = R.string.checks_desc_13;
                break;

            case CH_TYPE_UNKNOWN:
            default:
                result = R.string.empty;
        }
        return result;
    }
}
