package com.wpf.rxjavaretrofitdemo.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.mikepenz.itemanimators.ScaleUpAnimator;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wang.avi.AVLoadingIndicatorView;
import com.wpf.rxjavaretrofitdemo.R;
import com.wpf.rxjavaretrofitdemo.base.BaseItemAdapter;
import com.wpf.rxjavaretrofitdemo.base.BaseActivity;
import com.wpf.rxjavaretrofitdemo.common.DiffCallback;
import com.wpf.rxjavaretrofitdemo.common.Utils;
import com.wpf.rxjavaretrofitdemo.gank.Gank;
import com.wpf.rxjavaretrofitdemo.gank.GankAdapter;
import com.wpf.rxjavaretrofitdemo.image.ImageManager;
import com.wpf.rxjavaretrofitdemo.net.GankService;
import com.wpf.rxjavaretrofitdemo.net.HttpManager;
import com.wpf.rxjavaretrofitdemo.widget.EmptyRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.wpf.rxjavaretrofitdemo.common.Constant.GET_DATA_TYPE_LOADMORE;
import static com.wpf.rxjavaretrofitdemo.common.Constant.GET_DATA_TYPE_NORMAL;
import static com.wpf.rxjavaretrofitdemo.common.Constant.PAGE_SIZE;

/**
 * Created by wpf on 2017/11/30.
 *
 */

public class GankOtherActivity extends BaseActivity {
    @BindView(R.id.gank_other_toolbar)
    Toolbar gankOtherToolbar;
    @BindView(R.id.basefragment_rv)
    EmptyRecyclerView basefragmentRv;
    @BindView(R.id.basefragment_sr)
    SmartRefreshLayout basefragmentSr;
    @BindView(R.id.basefragment_av)
    AVLoadingIndicatorView basefragmentAv;
    @BindView(R.id.basefragment_ev)
    View basefragmentEv;
    private GankAdapter gankAdapter;
    private List<Gank.ResultsBean> list = new ArrayList<>();
    private List<Gank.ResultsBean> newList = new ArrayList<>();
    private int page = 1;
    private boolean NO_MORE_DATA = false;
    private String gank_type = "all";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            Slide slide = new Slide();
            slide.setSlideEdge(Gravity.END);
            slide.setDuration(500);
            getWindow().setEnterTransition(slide);
        }
        initToolbar();
        Bundle bundle = getIntent().getBundleExtra("gank_type");
        gank_type = bundle.getString("gank_type");
        basefragmentAv.bringToFront();
        basefragmentAv.show();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(GankOtherActivity.this);
        basefragmentRv.setLayoutManager(linearLayoutManager);
        gankAdapter = initAdapter(list, GankOtherActivity.this);
        basefragmentRv.setAdapter(gankAdapter);
        basefragmentRv.setItemAnimator(new ScaleUpAnimator());
        basefragmentRv.setEmptyView(basefragmentEv);
        initListener();
        loadData(page++, GET_DATA_TYPE_LOADMORE, gank_type, gankAdapter);
    }

    @Override
    protected int setContentLayout() {
        return R.layout.activity_gankother;
    }

    private void initToolbar() {
        setSupportActionBar(gankOtherToolbar);
        gankOtherToolbar.setTitle(gank_type);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        gankOtherToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.finishAfterTransition(GankOtherActivity.this);
            }
        });
    }

    public GankAdapter initAdapter(List<Gank.ResultsBean> list, Context context) {
        GankAdapter gankAdapter = new GankAdapter(list, context);
        gankAdapter.setOnBindDataListener(new BaseItemAdapter.OnBindDataListener() {
            @Override
            public void OnBindData(Context context, BaseItemAdapter.ViewHolder holder, Object dataBean) {
                Gank.ResultsBean data = (Gank.ResultsBean) dataBean;
                bindData(context, holder, data);
            }

            @Override
            public void OnBindDataList(Context context, BaseItemAdapter.ViewHolder holder, List<Object> dataBean) {
                List<Gank.ResultsBean> dataList = (List<Gank.ResultsBean>) (Object) dataBean;
                for (Gank.ResultsBean data : dataList) {
                    bindData(context, holder, data);
                }
            }
        });
        return gankAdapter;
    }

    private void bindData(Context context, BaseItemAdapter.ViewHolder holder, Gank.ResultsBean data) {
        if (data.getImages() == null || data.getImages().isEmpty()) {
            holder.itemImg.setVisibility(View.GONE);
        } else {
            ImageManager.getInstance().loadImage(context, data.getImages().get(0), holder.itemImg);
            holder.itemImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(GankOtherActivity.this, "Click img", Toast.LENGTH_SHORT).show();
                }
            });
        }
        holder.itemAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GankOtherActivity.this, "Click item", Toast.LENGTH_SHORT).show();
            }
        });
        holder.itemTitle.setText(data.getDesc());
        holder.itemLeft.setText(data.getType());
        holder.itemRight.setText(Utils.formatDateFromStr(data.getPublishedAt()));
    }

    private void loadData(int page, final int type, String gank_type, final BaseItemAdapter adapter) {
        GankService gankService = HttpManager.getInstance().getGankService();
        Observable<Gank> observable = gankService.getList(gank_type, PAGE_SIZE, page);
        observable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(new Function<Gank, DiffUtil.DiffResult>() {
                    @Override
                    public DiffUtil.DiffResult apply(Gank gank) throws Exception {
                        if (gank.getData().size() < PAGE_SIZE) {
                            setNoMoreData(true);
                        }
                        newList.clear();
                        if (type == GET_DATA_TYPE_LOADMORE) {
                            newList.addAll(list);
                        }
                        newList.addAll(gank.getData());
                        return DiffUtil.calculateDiff(new DiffCallback(newList, list), true);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DiffUtil.DiffResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DiffUtil.DiffResult diffResult) {
                        adapter.setData(newList);
                        diffResult.dispatchUpdatesTo(adapter);
                        list.clear();
                        list.addAll(newList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        hiedAllFailed();
                        Toast.makeText(GankOtherActivity.this, "加载错误！", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        hideAllSucceed();
                    }
                });
    }

    private void initListener() {
        basefragmentSr.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                setNoMoreData(false);
                basefragmentSr.resetNoMoreData();
                loadData(page++, GET_DATA_TYPE_NORMAL, gank_type, gankAdapter);
            }
        });
        basefragmentSr.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                loadData(page++, GET_DATA_TYPE_LOADMORE, gank_type, gankAdapter);
            }
        });
    }

    protected void hiedAllFailed() {
        if (basefragmentSr != null) {
            basefragmentSr.finishLoadmore(false);
            basefragmentSr.finishRefresh(false);
        }
        basefragmentAv.hide();
    }

    protected void hideAllSucceed() {
        if (basefragmentSr != null) {
            if (!NO_MORE_DATA) {
                basefragmentSr.finishLoadmore(true);
            } else {
                basefragmentSr.finishLoadmoreWithNoMoreData();
            }
            basefragmentSr.finishRefresh(true);
        }
        basefragmentAv.hide();
    }

    protected void setNoMoreData(boolean NO_MORE_DATA) {
        this.NO_MORE_DATA = NO_MORE_DATA;
    }
}
