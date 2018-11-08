package com.wpf.rxjavaretrofitdemo.movie;

import com.wpf.rxjavaretrofitdemo.base.BaseDataBean;
import com.wpf.rxjavaretrofitdemo.base.BaseModule;

import java.util.List;

/**
 * Created by wpf on 2017/11/20.
 *
 */

public class Movie extends BaseModule<Movie.DataBean> {

    public class DataBean extends BaseDataBean {
        /**
         * postid : 52948
         * title : 马云主演剧情短片《功守道》完整版
         * wx_small_app_title : 马云主演剧情短片《功守道》完整版
         * pid : 1
         * app_fu_title :
         * is_xpc : 0
         * is_promote : 0
         * is_xpc_zp : 0
         * is_xpc_cp : 0
         * is_xpc_fx : 0
         * is_album : 0
         * tags :
         * recent_hot : 0
         * discussion : 0
         * image : http://cs.vmovier.com/Uploads/cover/2017-11-12/5a07210221fe2_cut.jpeg
         * rating : 5.8
         * duration : 1364
         * publish_time : 1510416660
         * like_num : 1964
         * share_num : 5983
         * post_type : 1
         * cates : [{"cateid":"17","catename":"剧情"}]
         * request_url : http://app.vmoiver.com/52948?qingapp=app_new
         * ispromote : 0
         * isalbum : 0
         * cate : [{"cateid":"17","catename":"剧情"}]
         */

        private String title;
        private String wx_small_app_title;
        private String pid;
        private String app_fu_title;
        private String is_xpc;
        private String is_promote;
        private String is_xpc_zp;
        private String is_xpc_cp;
        private String is_xpc_fx;
        private String is_album;
        private String tags;
        private String recent_hot;
        private String discussion;
        private String image;
        private String rating;
        private String duration;
        private Long publish_time;
        private String like_num;
        private String share_num;
        private String post_type;
        private String request_url;
        private String ispromote;
        private String isalbum;
        private List<CatesBean> cates;
        private List<CateBean> cate;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getWx_small_app_title() {
            return wx_small_app_title;
        }

        public void setWx_small_app_title(String wx_small_app_title) {
            this.wx_small_app_title = wx_small_app_title;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getApp_fu_title() {
            return app_fu_title;
        }

        public void setApp_fu_title(String app_fu_title) {
            this.app_fu_title = app_fu_title;
        }

        public String getIs_xpc() {
            return is_xpc;
        }

        public void setIs_xpc(String is_xpc) {
            this.is_xpc = is_xpc;
        }

        public String getIs_promote() {
            return is_promote;
        }

        public void setIs_promote(String is_promote) {
            this.is_promote = is_promote;
        }

        public String getIs_xpc_zp() {
            return is_xpc_zp;
        }

        public void setIs_xpc_zp(String is_xpc_zp) {
            this.is_xpc_zp = is_xpc_zp;
        }

        public String getIs_xpc_cp() {
            return is_xpc_cp;
        }

        public void setIs_xpc_cp(String is_xpc_cp) {
            this.is_xpc_cp = is_xpc_cp;
        }

        public String getIs_xpc_fx() {
            return is_xpc_fx;
        }

        public void setIs_xpc_fx(String is_xpc_fx) {
            this.is_xpc_fx = is_xpc_fx;
        }

        public String getIs_album() {
            return is_album;
        }

        public void setIs_album(String is_album) {
            this.is_album = is_album;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public String getRecent_hot() {
            return recent_hot;
        }

        public void setRecent_hot(String recent_hot) {
            this.recent_hot = recent_hot;
        }

        public String getDiscussion() {
            return discussion;
        }

        public void setDiscussion(String discussion) {
            this.discussion = discussion;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public Long getPublish_time() {
            return publish_time;
        }

        public void setPublish_time(Long publish_time) {
            this.publish_time = publish_time;
        }

        public String getLike_num() {
            return like_num;
        }

        public void setLike_num(String like_num) {
            this.like_num = like_num;
        }

        public String getShare_num() {
            return share_num;
        }

        public void setShare_num(String share_num) {
            this.share_num = share_num;
        }

        public String getPost_type() {
            return post_type;
        }

        public void setPost_type(String post_type) {
            this.post_type = post_type;
        }

        public String getRequest_url() {
            return request_url;
        }

        public void setRequest_url(String request_url) {
            this.request_url = request_url;
        }

        public String getIspromote() {
            return ispromote;
        }

        public void setIspromote(String ispromote) {
            this.ispromote = ispromote;
        }

        public String getIsalbum() {
            return isalbum;
        }

        public void setIsalbum(String isalbum) {
            this.isalbum = isalbum;
        }

        public List<CatesBean> getCates() {
            return cates;
        }

        public void setCates(List<CatesBean> cates) {
            this.cates = cates;
        }

        public List<CateBean> getCate() {
            return cate;
        }

        public void setCate(List<CateBean> cate) {
            this.cate = cate;
        }

        public class CatesBean {
            /**
             * cateid : 17
             * catename : 剧情
             */

            private String cateid;
            private String catename;

            public String getCateid() {
                return cateid;
            }

            public void setCateid(String cateid) {
                this.cateid = cateid;
            }

            public String getCatename() {
                return catename;
            }

            public void setCatename(String catename) {
                this.catename = catename;
            }
        }

        public class CateBean {
            /**
             * cateid : 17
             * catename : 剧情
             */

            private String cateid;
            private String catename;

            public String getCateid() {
                return cateid;
            }

            public void setCateid(String cateid) {
                this.cateid = cateid;
            }

            public String getCatename() {
                return catename;
            }

            public void setCatename(String catename) {
                this.catename = catename;
            }
        }
    }
}
