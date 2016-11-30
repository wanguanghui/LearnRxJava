package com.wochacha.learnrxjava.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wochacha.learnrxjava.R;
import com.wochacha.learnrxjava.module.ZhuangBiImage;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by guanghui_wan on 2016/11/30.
 */

public class ZhuangBiListAdapter extends RecyclerView.Adapter{

    List<ZhuangBiImage> images;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid,parent,false);
        return new ZBViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ZBViewHolder viewHolder = (ZBViewHolder) holder;
        Glide.with(holder.itemView.getContext()).load(images.get(position).getImage_url()).into(viewHolder.ivImage);
        viewHolder.tvDesc.setText(images.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return images == null?0:images.size();
    }

    public void setImages(List<ZhuangBiImage> images){
        this.images = images;
        notifyDataSetChanged();

    }

    class ZBViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.iv_image) ImageView ivImage;
        @Bind(R.id.tv_desc) TextView tvDesc;

        public ZBViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
