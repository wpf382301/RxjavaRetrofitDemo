package com.wpf.rxjavaretrofitdemo.net;

import com.wpf.rxjavaretrofitdemo.gank.Gank;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by wpf on 2017/11/26.
 *
 */

public interface GankService {
    @GET("data/{type}/{pageSize}/{page}")
    Observable<Gank> getList(@Path("type") String type, @Path("pageSize") int pageSize, @Path("page") int page);
}
