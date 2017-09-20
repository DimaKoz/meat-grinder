package com.kozhevin.rootchecks.data;

import android.support.annotation.Nullable;

import com.kozhevin.rootchecks.annotation.CheckingMethodType;

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

public class CheckInfo {

    @Nullable
    private Boolean mState;

    @CheckingMethodType
    private int mTypeCheck;

    public CheckInfo(@Nullable Boolean state,@CheckingMethodType int typeCheck) {
        this.mState = state;
        this.mTypeCheck = typeCheck;
    }

    @Nullable
    public Boolean getState() {
        return mState;
    }

    public void setState(@Nullable Boolean state) {
        this.mState = state;
    }

    @CheckingMethodType
    public int getTypeCheck() {
        return mTypeCheck;
    }

    public void setTypeCheck(int typeCheck) {
        this.mTypeCheck = typeCheck;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CheckInfo)) return false;

        CheckInfo checkInfo = (CheckInfo) o;

        if (mTypeCheck != checkInfo.mTypeCheck) return false;
        return mState != null ? mState.equals(checkInfo.mState) : checkInfo.mState == null;

    }

    @Override
    public int hashCode() {
        int result = mState != null ? mState.hashCode() : 0;
        result = 31 * result + mTypeCheck;
        return result;
    }
}
