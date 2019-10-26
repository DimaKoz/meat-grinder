package com.kozhevin.rootchecks.ui;



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
