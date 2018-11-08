package com.wpf.rxjavaretrofitdemo.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wpf.rxjavaretrofitdemo.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wpf on 2017/11/25.
 *
 */

public class BaseItemAdapter<T extends BaseDataBean> extends RecyclerView.Adapter<BaseItemAdapter.ViewHolder> {
    private List<T> list;
    private Context context;
    private OnBindDataListener onBindDataListener;

    public BaseItemAdapter(List<T> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (onBindDataListener != null) {
            onBindDataListener.OnBindData(context, holder, list.get(position));
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position, List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            if (onBindDataListener != null) {
                onBindDataListener.OnBindDataList(context, holder, payloads);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public interface OnBindDataListener {
        void OnBindData(Context context, ViewHolder holder, Object dataBean);

        void OnBindDataList(Context context, ViewHolder holder, List<Object> dataBean);
    }

    public void setOnBindDataListener(OnBindDataListener onBindDataListener) {
        this.onBindDataListener = onBindDataListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_img)
        public ImageView itemImg;
        @BindView(R.id.item_title)
        public TextView itemTitle;
        @BindView(R.id.item_left)
        public TextView itemLeft;
        @BindView(R.id.item_right)
        public TextView itemRight;
        @BindView(R.id.item_all)
        public LinearLayout itemAll;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public void setData(List<T> list) {
        this.list = list;
    }
}
