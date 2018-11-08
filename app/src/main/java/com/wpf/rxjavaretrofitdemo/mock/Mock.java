package com.wpf.rxjavaretrofitdemo.mock;

import com.wpf.rxjavaretrofitdemo.base.BaseDataBean;
import com.wpf.rxjavaretrofitdemo.base.BaseModule;

/**
 * Created by wpf on 2017/12/3.
 *
 */

public class Mock extends BaseModule<Mock.DataBean> {
    public class DataBean extends BaseDataBean{
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
