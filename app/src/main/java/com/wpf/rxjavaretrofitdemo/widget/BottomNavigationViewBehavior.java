package com.wpf.rxjavaretrofitdemo.widget;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wpf on 2017/11/27.
 *
 */

public class BottomNavigationViewBehavior extends CoordinatorLayout.Behavior<View> {
    public BottomNavigationViewBehavior() {
    }

    public BottomNavigationViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        //因为Behavior只对CoordinatorLayout的直接子View生效，因此将依赖关系转移到AppBarLayout
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        //得到依赖View的滑动距离
        int top = ((AppBarLayout.Behavior) ((CoordinatorLayout.LayoutParams) dependency.getLayoutParams()).getBehavior()).getTopAndBottomOffset();
        float status_bar_height = dependency.getResources().getDimension(com.mikepenz.materialize.R.dimen.materialize_statusbar);
        if (Math.abs(top) > status_bar_height) {
            child.setTranslationY(Math.abs(top) - status_bar_height);
        } else {
            child.setTranslationY(0);
        }
        return true;
    }
}
