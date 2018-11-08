package com.wpf.rxjavaretrofitdemo.base;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wpf on 2017/11/26.
 *
 */

public class BaseDataBean {
    @SerializedName(value = "postid", alternate = {"_id"})
    private String postid;

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }
}
