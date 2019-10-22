package com.kozhevin.rootchecks.ui;

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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.kozhevin.rootchecks.R;
import com.kozhevin.rootchecks.data.CheckInfo;

import java.util.ArrayList;



public class ResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final static int ITEM_CHECK_RESULT = 10;

    private ArrayList<CheckInfo> mFullData;


    public ResultAdapter() {
        mFullData = new ArrayList<>();
    }

    public void setData(@NonNull ArrayList<CheckInfo> pDataList) {

        mFullData.clear();
        mFullData.addAll(pDataList);
        notifyDataSetChanged();

    }

    @Override
    public int getItemViewType(int position) {
        return ITEM_CHECK_RESULT;
    }

    public CheckInfo getItem(int position) {
        return mFullData.get(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ResultItemHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_check, parent, false));


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder pHolder, final int position) {
        ((ResultItemHolder) pHolder).onBind(getItem(position), position);
    }

    @Override
    public long getItemId(int position) {
        return mFullData.get(position).hashCode();

    }

    @Override
    public int getItemCount() {
        return mFullData.size();
    }

}
