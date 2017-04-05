package com.example.martian.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.martian.R;

import java.util.List;

/**
 * @author martian on 2017/3/8.
 */

public class NestRecycleViewAdapter extends RecyclerView.Adapter<NestRecycleViewAdapter.ViewHolder>{

    private List<String> mData;

    public NestRecycleViewAdapter(List<String> mData){
        this.mData = mData;
    }

    @Override
    public NestRecycleViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_nestrecycleview,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NestRecycleViewAdapter.ViewHolder holder, int position) {
        holder.textView.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder  {
        private TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.row_nestrecycleview_tx);
        }
    }
}
