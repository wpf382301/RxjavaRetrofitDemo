package com.wpf.rxjavaretrofitdemo.net;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wpf on 2017/11/22.
 *
 */

public class HttpManager {
    private HttpManager() {
    }

    public static HttpManager getInstance() {
        return HttpManagerHolder.instance;
    }

    private static class HttpManagerHolder {
        private static final HttpManager instance = new HttpManager();
    }

    public MovieService getMovieService(){
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(15, TimeUnit.SECONDS);
        builder.connectTimeout(10, TimeUnit.SECONDS);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://app.vmovier.com/")
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(MovieService.class);
    }

    public GankService getGankService(){
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(15, TimeUnit.SECONDS);
        builder.connectTimeout(10, TimeUnit.SECONDS);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/api/")
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(GankService.class);
    }

    public MockService getMockService() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(15, TimeUnit.SECONDS);
        builder.connectTimeout(10, TimeUnit.SECONDS);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://127.0.0.1:8080/")
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
        return retrofit.create(MockService.class);
    }
}
