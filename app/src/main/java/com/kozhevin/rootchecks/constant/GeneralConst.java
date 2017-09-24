package com.kozhevin.rootchecks.constant;

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

public class GeneralConst {

    private GeneralConst(){

    }

    final static public int CH_TYPE_UNKNOWN = 0;
    final static public int CH_TYPE_TEST_KEYS = 10;
    final static public int CH_TYPE_DEV_KEYS = 20;
    final static public int CH_TYPE_NON_RELEASE_KEYS = 30;
    final static public int CH_TYPE_DANGEROUS_PROPS = 40;
    final static public int CH_TYPE_PERMISSIVE_SELINUX = 50;
    final static public int CH_TYPE_SU_EXISTS = 60;
    final static public int CH_TYPE_SUPER_USER_APK = 70;
    final static public int CH_TYPE_SU_BINARY = 80;
    final static public int CH_TYPE_BUSYBOX_BINARY = 90;
    final static public int CH_TYPE_XPOSED = 100;
    final static public int CH_TYPE_RESETPROP = 110;
    final static public int CH_TYPE_WRONG_PATH_PERMITION = 120;
    final static public int CH_TYPE_HOOKS = 130;

    final static public int CH_STATE_UNCHECKED = 0;
    final static public int CH_STATE_STILL_GOING = 10;
    final static public int CH_STATE_CHECKED_ROOT_DETECTED = 20;
    final static public int CH_STATE_CHECKED_ROOT_NOT_DETECTED = 30;
    final static public int CH_STATE_CHECKED_ERROR = 40;

    final static public String GITHUB = "https://github.com/DimaKoz/meat-grinder";
}
