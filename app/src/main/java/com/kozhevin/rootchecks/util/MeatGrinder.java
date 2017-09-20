package com.kozhevin.rootchecks.util;

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

import android.support.annotation.NonNull;

import com.kozhevin.rootchecks.BuildConfig;

public class MeatGrinder {

    private static final String TAG = MeatGrinder.class.getSimpleName();
    private static Throwable sThrowableInit;

    static {
        try {
            System.loadLibrary("native-lib");
            sThrowableInit = null;
        } catch (UnsatisfiedLinkError e) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace();
            }
            sThrowableInit = e;
        }
    }

    private MeatGrinder() {

    }

    public boolean isLibraryLoaded() {
        return sThrowableInit == null;
    }

    public native boolean isDetectedDevKeys();

    public native boolean isDetectedTestKeys();

    public native boolean isNotFoundReleaseKeys();

    public native boolean isFoundDangerousProps();

    public native boolean isPermissiveSelinux();

    public native boolean isSuExists();

    public native boolean isAccessedSuperuserApk();

    public native boolean isFoundSuBinary();

    public native boolean isFoundBusyboxBinary();

    public native boolean isFoundXposed();

    public native boolean isFoundResetprop();

    public native boolean isFoundWrongPathPermission();

    public native boolean isFoundHooks();

    @NonNull
    public static MeatGrinder getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private static class InstanceHolder {
        private static final MeatGrinder INSTANCE = new MeatGrinder();
    }
}
