package com.wpf.rxjavaretrofitdemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import com.wpf.rxjavaretrofitdemo.common.Utils;

public class ViewPagerIndicator extends View implements ViewPager.OnPageChangeListener {

    //总页数
    private int mTotalPages;
    //两者之间的距离
    private int mSpacing;
    //半径
    private int mRadius;
    //长条
    private int mLongLength;
    //短条
    private int mShortLength;
    private int mColor;
    private int mUnselectedColor;

    private Paint mPaint;
    //总长度
    private int mTotalLength;
    //当前页
    private int mCurrentPage;
    //偏移量
    private float mCurrentPageOffset;
    //长条短条的差值
    private int mDifference;
    private Point mCenter;

    public ViewPagerIndicator(Context context) {
        this(context, null);
    }

    public ViewPagerIndicator(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ViewPagerIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mCurrentPage = position % mTotalPages;
        mCurrentPageOffset = positionOffset;
        invalidate();
    }

    @Override
    public void onPageSelected(int position) {
        mCurrentPage = position % mTotalPages;
        mCurrentPageOffset = 0;
        invalidate();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mCenter == null) {
            mCenter = getCenter();
        }

        float startX = mCenter.x - mTotalLength / 2;

        if (mTotalPages <= 0) return;
        if (mTotalPages == 1) {
            mPaint.setColor(mColor);
            canvas.drawLine(startX, mCenter.y, startX + mShortLength + mDifference, mCenter.y, mPaint);
        } else {
            for (int i = 0; i < mTotalPages; i++) {
                if (i == mCurrentPage) {
                    //往右翻页，offset 0->1，早offset=1时mCurrentPage变成目标页
                    //往左翻页，mCurrentPage马上变成目标页，offset 1->0
                    mPaint.setColor(Utils.evaluateColor(mColor, mUnselectedColor, mCurrentPageOffset));
                    float offset = mDifference * (1 - mCurrentPageOffset);
                    canvas.drawLine(startX, mCenter.y, startX + mShortLength + offset, mCenter.y, mPaint);
                    startX += mShortLength + offset + mSpacing;
                } else if (i == (mCurrentPage + 1) % mTotalPages) {
                    mPaint.setColor(Utils.evaluateColor(mColor, mUnselectedColor, 1 - mCurrentPageOffset));
                    float offset = mDifference * mCurrentPageOffset;
                    canvas.drawLine(startX, mCenter.y, startX + mShortLength + offset, mCenter.y, mPaint);
                    startX += mShortLength + offset + mSpacing;
                } else {
                    mPaint.setColor(mUnselectedColor);
                    canvas.drawLine(startX, mCenter.y, startX + mShortLength, mCenter.y, mPaint);
                    startX += mShortLength + mSpacing;
                }
            }
        }
    }

    private void init(Context context) {
        mColor = Color.parseColor("#F26355");
        mUnselectedColor = Color.parseColor("white");

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND); //设置画笔为圆形
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mColor);

        mRadius = (int) Utils.dp2px(context, 2);
        mShortLength = (int) Utils.dp2px(context, 6);
        mLongLength = (int) Utils.dp2px(context, 22);
        mDifference = mLongLength - mShortLength;
        mSpacing = (int) Utils.dp2px(context, 4) + mRadius * 2;

        mPaint.setStrokeWidth(mRadius * 2);
    }

    /**
     * 获得控件中心点的坐标
     *
     * @return 中心点的坐标
     */
    private Point getCenter() {
        return new Point((getRight() - getLeft()) / 2, (getBottom() - getTop()) / 2);
    }

    /**
     * 设置指示器的数量
     *
     * @param totalPages total pages
     */
    public void setTotalPages(int totalPages) {
        mTotalPages = totalPages;
        mTotalLength = mLongLength + (mTotalPages - 1) * (mShortLength + mSpacing);
        invalidate();
    }

    /**
     * 设定指示器的半径
     *
     * @param size radius
     */
    public void setRadius(int size) {
        mRadius = size;
        mPaint.setStrokeWidth(mRadius * 2);
    }

    /**
     * 设定指示器的间距
     *
     * @param spacing spacing
     */
    public void setSpacing(int spacing) {
        mSpacing = spacing + mRadius * 2;
    }

    public void setLongLength(int mLongLength) {
        this.mLongLength = mLongLength;
        mDifference = this.mLongLength - mShortLength;
    }

    public void setShortLength(int mShortLength) {
        this.mShortLength = mShortLength;
        mDifference = this.mLongLength - mShortLength;
    }

    public void setColor(int mColor) {
        this.mColor = mColor;
    }

    public void setUnselectedColor(int mUnselectedColor) {
        this.mUnselectedColor = mUnselectedColor;
    }
}
