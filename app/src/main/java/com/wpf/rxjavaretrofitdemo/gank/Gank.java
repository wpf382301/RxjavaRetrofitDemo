package com.wpf.rxjavaretrofitdemo.gank;

import com.google.gson.annotations.SerializedName;
import com.wpf.rxjavaretrofitdemo.base.BaseDataBean;
import com.wpf.rxjavaretrofitdemo.base.BaseModule;

import java.util.List;

/**
 * Created by wpf on 2017/11/26.
 *
 */

public class Gank extends BaseModule<Gank.ResultsBean> {

    public class ResultsBean extends BaseDataBean {
        /**
         * _id : 5a137c67421aa90fef20354d
         * createdAt : 2017-11-21T09:07:51.275Z
         * desc : iOS 和 Android 开发是否要采用 React Native?
         * publishedAt : 2017-11-24T11:08:03.624Z
         * source : web
         * type : Android
         * url : https://mp.weixin.qq.com/s?__biz=MzU4MjAzNTAwMA==&mid=2247483866&idx=1...
         * used : true
         * who : null
         * images : ["http://img.gank.io/fef497ed-83ba-46f6-8a94-0e7b724e1c10"]
         */
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private Object who;
        private List<String> images;

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public Object getWho() {
            return who;
        }

        public void setWho(Object who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
