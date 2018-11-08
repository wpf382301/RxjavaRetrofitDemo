package com.wpf.rxjavaretrofitdemo.common;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.wpf.rxjavaretrofitdemo.base.BaseDataBean;

import java.util.List;

/**
 * Created by wpf on 2017/11/21.
 *
 */

public class DiffCallback<T extends BaseDataBean> extends DiffUtil.Callback {
    private List<T> oldList;
    private List<T> newList;

    public DiffCallback(List<T> newList, List<T> oldList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList != null ? oldList.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return newList != null ? newList.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getPostid().equals(newList.get(newItemPosition).getPostid());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return newList.get(newItemPosition);
    }
}
