package com.wpf.rxjavaretrofitdemo.common;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 作者：Leon
 * 描述:
 */
public class Utils {
    public static String formatDateFromStr(Long date) {
        Date d = new Date(date);
        String dateStr = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        try {
            dateStr = sdf.format(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }

    public static String formatDateFromStr(String dateStr) {
        Date date;
        String d = "";
        if (!TextUtils.isEmpty(dateStr)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.CHINA);
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
            try {
                date = sdf.parse(dateStr);
                d = sdf2.format(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return d;
    }
}
