package com.wpf.rxjavaretrofitdemo.gank;

import android.content.Context;

import com.wpf.rxjavaretrofitdemo.base.BaseItemAdapter;

import java.util.List;

/**
 * Created by wpf on 2017/11/26.
 *
 */

public class GankAdapter extends BaseItemAdapter<Gank.ResultsBean> {
    public GankAdapter(List<Gank.ResultsBean> list, Context context) {
        super(list, context);
    }
}
