package com.wpf.rxjavaretrofitdemo.net;

import com.wpf.rxjavaretrofitdemo.mock.Mock;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by wpf on 2017/12/3.
 *
 */

public interface MockService {
    @GET("api/{type}")
    Call<Mock> getList(@Path("type") String type);
}
