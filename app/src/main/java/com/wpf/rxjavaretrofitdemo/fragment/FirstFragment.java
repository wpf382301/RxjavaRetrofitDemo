package com.wpf.rxjavaretrofitdemo.fragment;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.wpf.rxjavaretrofitdemo.base.BaseFragment;
import com.wpf.rxjavaretrofitdemo.base.BaseItemAdapter;
import com.wpf.rxjavaretrofitdemo.common.Utils;
import com.wpf.rxjavaretrofitdemo.image.ImageManager;
import com.wpf.rxjavaretrofitdemo.movie.Movie;
import com.wpf.rxjavaretrofitdemo.movie.MovieAdapter;
import com.wpf.rxjavaretrofitdemo.net.HttpManager;
import com.wpf.rxjavaretrofitdemo.net.MovieService;

import java.util.List;

import io.reactivex.Observable;

public class FirstFragment extends BaseFragment<Movie.DataBean, Movie> {

    @Override
    public BaseItemAdapter initAdapter(List<Movie.DataBean> list) {
        MovieAdapter movieAdapter = new MovieAdapter(list, getContext());
        movieAdapter.setOnBindDataListener(new BaseItemAdapter.OnBindDataListener() {
            @Override
            public void OnBindData(Context context, BaseItemAdapter.ViewHolder holder, Object dataBean) {
                Movie.DataBean data = (Movie.DataBean) dataBean;
                bindData(context, holder, data);
            }

            @Override
            @SuppressWarnings("unchecked")
            public void OnBindDataList(Context context, BaseItemAdapter.ViewHolder holder, List<Object> dataBean) {
                List<Movie.DataBean> dataList = (List<Movie.DataBean>) (Object) dataBean;
                for (Movie.DataBean data : dataList) {
                    bindData(context, holder, data);
                }
            }
        });
        return movieAdapter;
    }

    @Override
    protected Observable<Movie> initObservable(int page) {
        MovieService movieService = HttpManager.getInstance().getMovieService();
        return movieService.getMovieList("hot", page);
    }

    private void bindData(Context context, BaseItemAdapter.ViewHolder holder, Movie.DataBean data) {
        if (data.getImage() == null || data.getImage().isEmpty()) {
            holder.itemImg.setVisibility(View.GONE);
        } else {
            ImageManager.getInstance().loadImage(context, data.getImage(), holder.itemImg);
            holder.itemImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Click img", Toast.LENGTH_SHORT).show();
                }
            });
        }
        holder.itemAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Click item", Toast.LENGTH_SHORT).show();
            }
        });
        holder.itemTitle.setText(data.getTitle());
        holder.itemLeft.setText(data.getCate().get(0).getCatename());
        holder.itemRight.setText(Utils.formatDateFromStr(data.getPublish_time() * 1000));
    }
}
