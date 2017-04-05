package com.example.martian.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.martian.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author martian on 2017/3/8.
 */

public class RecycleViewRecycleViewAdapter extends RecyclerView.Adapter<RecycleViewRecycleViewAdapter.ViewHolder> {
    private List<String> mData;

    public RecycleViewRecycleViewAdapter(List<String> mData){
        this.mData = mData;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_nestrecycleviewrecycle,parent,false);
        return new RecycleViewRecycleViewAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(holder.rv.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.rv.setLayoutManager(linearLayoutManager);
        holder.rv.setAdapter(new NestRecycleViewAdapter(getData()));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder  {
        private RecyclerView rv;
        public ViewHolder(View itemView) {
            super(itemView);
            rv = (RecyclerView) itemView.findViewById(R.id.row_nestrecycleviewrecycle_rc);
        }
    }

    private List<String> getData(){
        List<String> list = new ArrayList<>();
        for (int i= 0;i<10;i++){
            list.add("item:"+i);
        }
        return list;
    }
}

