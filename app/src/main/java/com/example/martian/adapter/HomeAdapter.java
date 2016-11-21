package com.example.martian.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.martian.R;

import java.util.List;

/**
 * Created by Administrator on 2016/7/6.
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{

    private List<String> mData;

    public OnItemClickListener itemClickListtener;


    public HomeAdapter(List<String> mData){
        this.mData = mData;
    }

    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycleview,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HomeAdapter.ViewHolder holder, int position) {
        holder.textView.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }




    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.row_text);
            textView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(itemClickListtener !=null){
                itemClickListtener.onItemClick(v,getPosition());
            }
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListtener){
        this.itemClickListtener = itemClickListtener;
    }
}
