package com.wpf.rxjavaretrofitdemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wpf.rxjavaretrofitdemo.common.Utils;

public class IndicatorDecoration extends RecyclerView.ItemDecoration {

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
    private Context mContext;

    public IndicatorDecoration(Context context, int totalPages) {
        mContext = context;
        mTotalPages = totalPages;
        init();
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int fakePosition = ((LinearLayoutManager)parent.getLayoutManager()).findFirstVisibleItemPosition();
        float ratio = calculateProgress(parent, fakePosition);
        onPageScrolled(fakePosition, 1 - ratio);
        drawIndicator(c, parent);
    }

    private float calculateProgress(RecyclerView parent, int fakePosition) {
        View viewFirst = parent.getLayoutManager().findViewByPosition(fakePosition);
        float width = parent.getWidth();
        if (width != 0 && viewFirst != null) {
            float right = viewFirst.getRight();
            return right / width;
        }
        return 0f;
    }

    private void onPageScrolled(int position, float positionOffset) {
        mCurrentPage = position % mTotalPages;
        mCurrentPageOffset = positionOffset;
    }

    private void drawIndicator(Canvas canvas, RecyclerView parent) {
        float startX = (parent.getWidth() - mTotalLength) / 2F;
        float indicatorY = parent.getHeight() - mRadius - Utils.dp2px(mContext, 4);

        if (mTotalPages <= 0) return;
        if (mTotalPages == 1) {
            mPaint.setColor(mColor);
            canvas.drawLine(startX, indicatorY, startX + mShortLength + mDifference, indicatorY, mPaint);
        } else {
            for (int i = 0; i < mTotalPages; i++) {
                if (i == mCurrentPage) {
                    //往右翻页，offset 0->1，早offset=1时mCurrentPage变成目标页
                    //往左翻页，mCurrentPage马上变成目标页，offset 1->0
                    mPaint.setColor(Utils.evaluateColor(mColor, mUnselectedColor, mCurrentPageOffset));
                    float offset = mDifference * (1 - mCurrentPageOffset);
                    canvas.drawLine(startX, indicatorY, startX + mShortLength + offset, indicatorY, mPaint);
                    startX += mShortLength + offset + mSpacing;
                } else if (i == (mCurrentPage + 1) % mTotalPages) {
                    mPaint.setColor(Utils.evaluateColor(mColor, mUnselectedColor, 1 - mCurrentPageOffset));
                    float offset = mDifference * mCurrentPageOffset;
                    canvas.drawLine(startX, indicatorY, startX + mShortLength + offset, indicatorY, mPaint);
                    startX += mShortLength + offset + mSpacing;
                } else {
                    mPaint.setColor(mUnselectedColor);
                    canvas.drawLine(startX, indicatorY, startX + mShortLength, indicatorY, mPaint);
                    startX += mShortLength + mSpacing;
                }
            }
        }
    }

    private void init() {
        mColor = Color.parseColor("#F26355");
        mUnselectedColor = Color.parseColor("white");

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND); //设置画笔为圆形
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mColor);

        mRadius = (int) Utils.dp2px(mContext, 2);
        mShortLength = (int) Utils.dp2px(mContext, 6);
        mLongLength = (int) Utils.dp2px(mContext, 22);
        mDifference = mLongLength - mShortLength;
        mSpacing = (int) Utils.dp2px(mContext, 4) + mRadius * 2;
        mTotalLength = mLongLength + (mTotalPages - 1) * (mShortLength + mSpacing);

        mPaint.setStrokeWidth(mRadius * 2);
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
