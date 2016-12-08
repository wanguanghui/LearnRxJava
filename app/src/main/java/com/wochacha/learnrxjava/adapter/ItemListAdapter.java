package com.wochacha.learnrxjava.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wochacha.learnrxjava.R;
import com.wochacha.learnrxjava.model.Item;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by guanghui_wan on 2016/12/7.
 */

public class ItemListAdapter extends RecyclerView.Adapter {

    List<Item> images;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid,parent,false);
        return new MapViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MapViewHolder mapViewHolder = (MapViewHolder) holder;
        Item image = images.get(position);
        Glide.with(holder.itemView.getContext()).load(image.getImageUrl()).thumbnail(0.1f).into(mapViewHolder.ivImage);
        mapViewHolder.tvDesc.setText(image.getDescription());
    }

    @Override
    public int getItemCount() {
        return images == null?0:images.size();
    }

    public void setItems(List<Item> images){
        this.images = images;
        notifyDataSetChanged();
    }

    class MapViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.iv_image) ImageView ivImage;
        @Bind(R.id.tv_desc) TextView tvDesc;

        public MapViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
