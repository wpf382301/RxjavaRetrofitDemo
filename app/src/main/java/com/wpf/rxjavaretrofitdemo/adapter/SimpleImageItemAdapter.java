package com.wpf.rxjavaretrofitdemo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wpf.rxjavaretrofitdemo.R;

import java.util.List;

public class SimpleImageItemAdapter extends RecyclerView.Adapter<SimpleImageItemAdapter.ViewHolder> {
    private List<Integer> imageViews;

    public SimpleImageItemAdapter(List<Integer> imageViews) {
        this.imageViews = imageViews;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.iamge_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(imageViews.get(position % imageViews.size()));
    }

    @Override
    public int getItemCount() {
        return imageViews == null ? 0 : imageViews.size() > 1 ? Integer.MAX_VALUE : 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.image);
        }
    }
}
