package com.wpf.rxjavaretrofitdemo.movie;

import android.content.Context;

import com.wpf.rxjavaretrofitdemo.base.BaseItemAdapter;

import java.util.List;

/**
 * Created by wpf on 2017/11/21.
 *
 */

public class MovieAdapter extends BaseItemAdapter<Movie.DataBean> {

    public MovieAdapter(List<Movie.DataBean> list, Context context) {
        super(list, context);
    }
}
