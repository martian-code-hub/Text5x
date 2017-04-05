package com.martian.myviewpager;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by yangpei on 2017/2/7.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements View.OnClickListener {

    private List<String>  datas;
    private OnRecyclerViewItemClickListener listener;

    public MyAdapter(List<String> datas) {
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recylerview,parent,false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textview.setText(datas.get(position));
        holder.textview.setTag(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public void onClick(View v) {
        if(listener != null){
            listener.onItemClick(v,(String)v.findViewById(R.id.item_textview).getTag());
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textview;
        public ViewHolder(View itemView) {
            super(itemView);
            textview = (TextView) itemView.findViewById(R.id.item_textview);
        }
    }

    public void setListener(OnRecyclerViewItemClickListener listener){
        this.listener = listener;
    }

    public static interface OnRecyclerViewItemClickListener{
        void onItemClick(View view,String content);
    }
}
