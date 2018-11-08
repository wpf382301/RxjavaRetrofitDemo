package com.wpf.rxjavaretrofitdemo.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.request.RequestOptions;
import com.wpf.rxjavaretrofitdemo.R;


/**
 * 作者：Leon
 * 描述: 图片处理类
 */
public class ImageManager {

    private ImageManager() {
    }

    public static ImageManager getInstance() {
        return ImageManagerHolder.instance;
    }

    public static class ImageManagerHolder {
        private static final ImageManager instance = new ImageManager();
    }

    /**
     * 加载图片
     *
     * @param context
     * @param path
     * @param targetImageView 使用默认的占位等待图片和错误图片
     */
    public void loadImage(Context context, Object path, ImageView targetImageView) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error);
        Glide.with(context)
                .load(path)
                .apply(options)
                .into(targetImageView);
    }

    /**
     * 加载图片
     *
     * @param context
     * @param path
     * @param targetImageView 使用默认的占位等待图片和错误图片
     * @param transformation  图片转换器
     */
    public void loadImage(Context context, Object path, ImageView targetImageView, Transformation transformation) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .bitmapTransform(transformation)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error);
        Glide.with(context)
                .load(path)
                .apply(options)
                .into(targetImageView);
    }

    /**
     * 加载图片
     *
     * @param context
     * @param path
     * @param targetImageView
     * @param placeHolderResourceId 加载中占位图片
     * @param errorResourceId       加载错误占位图片
     */
    public void loadImage(Context context, Object path, ImageView targetImageView, int placeHolderResourceId, int errorResourceId) {
        RequestOptions options = new RequestOptions()
                .placeholder(placeHolderResourceId)
                .error(errorResourceId);
        Glide.with(context)
                .load(path)
                .apply(options)
                .into(targetImageView);
    }
}
