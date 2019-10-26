package com.kozhevin.rootchecks.data;

import androidx.annotation.NonNull;

import com.kozhevin.rootchecks.annotation.CheckingState;

import java.util.List;


public class TotalResult {
    @NonNull
    private final List<CheckInfo> mList;

    @CheckingState
    private final int mCheckState;

    public TotalResult(@NonNull List<CheckInfo> list, @CheckingState int checkState) {
        mList = list;
        mCheckState = checkState;
    }

    @NonNull
    public List<CheckInfo> getList() {
        return mList;
    }

    @CheckingState
    public int getCheckState() {
        return mCheckState;
    }
}
