package com.wpf.rxjavaretrofitdemo.fragment;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.wpf.rxjavaretrofitdemo.base.BaseFragment;
import com.wpf.rxjavaretrofitdemo.base.BaseItemAdapter;
import com.wpf.rxjavaretrofitdemo.common.Constant;
import com.wpf.rxjavaretrofitdemo.common.Utils;
import com.wpf.rxjavaretrofitdemo.gank.Gank;
import com.wpf.rxjavaretrofitdemo.gank.GankAdapter;
import com.wpf.rxjavaretrofitdemo.image.ImageManager;
import com.wpf.rxjavaretrofitdemo.net.GankService;
import com.wpf.rxjavaretrofitdemo.net.HttpManager;

import java.util.List;

import io.reactivex.Observable;

public class ThirdFragment extends BaseFragment<Gank.ResultsBean, Gank> {
    @Override
    protected BaseItemAdapter initAdapter(List<Gank.ResultsBean> list, Context context) {
        GankAdapter gankAdapter = new GankAdapter(list, context);
        gankAdapter.setOnBindDataListener(new BaseItemAdapter.OnBindDataListener() {
            @Override
            public void OnBindData(Context context, BaseItemAdapter.ViewHolder holder, Object dataBean) {
                Gank.ResultsBean data = (Gank.ResultsBean) dataBean;
                bindData(context, holder, data);
            }

            @Override
            @SuppressWarnings("unchecked")
            public void OnBindDataList(Context context, BaseItemAdapter.ViewHolder holder, List<Object> dataBean) {
                List<Gank.ResultsBean> dataList = (List<Gank.ResultsBean>) (Object) dataBean;
                for (Gank.ResultsBean data : dataList) {
                    bindData(context, holder, data);
                }
            }
        });
        return gankAdapter;
    }

    @Override
    protected Observable<Gank> initObservable(int page) {
        GankService gankService = HttpManager.getInstance().getGankService();
        return gankService.getList("iOS", Constant.PAGE_SIZE, page);
    }

    private void bindData(Context context, BaseItemAdapter.ViewHolder holder, Gank.ResultsBean data) {
        if (data.getImages() == null || data.getImages().isEmpty()) {
            holder.itemImg.setVisibility(View.GONE);
        } else {
            ImageManager.getInstance().loadImage(context, data.getImages().get(0), holder.itemImg);
            holder.itemImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), "Click img", Toast.LENGTH_SHORT).show();
                }
            });
        }
        holder.itemAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Click item", Toast.LENGTH_SHORT).show();
            }
        });
        holder.itemTitle.setText(data.getDesc());
        holder.itemLeft.setText(data.getType());
        holder.itemRight.setText(Utils.formatDateFromStr(data.getPublishedAt()));
    }
}
