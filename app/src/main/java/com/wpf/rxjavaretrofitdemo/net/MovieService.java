package com.wpf.rxjavaretrofitdemo.net;

import com.wpf.rxjavaretrofitdemo.movie.Movie;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by wpf on 2017/11/20.
 *
 */

public interface MovieService {
    @GET("/apiv3/post/getPostByTab")
    Observable<Movie> getMovieList(@Query("tab") String tab, @Query("p") int page);
}
