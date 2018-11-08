package com.wpf.rxjavaretrofitdemo.base;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by wpf on 2017/11/25.
 *
 */

public class BaseModule<T extends BaseDataBean> {
    private String status;
    private String msg;
    @SerializedName(value = "data", alternate = {"results"})
    private List<T> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
