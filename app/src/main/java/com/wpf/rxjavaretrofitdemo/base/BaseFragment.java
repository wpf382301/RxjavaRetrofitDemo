package com.wpf.rxjavaretrofitdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mikepenz.itemanimators.ScaleUpAnimator;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wang.avi.AVLoadingIndicatorView;
import com.wpf.rxjavaretrofitdemo.R;
import com.wpf.rxjavaretrofitdemo.common.DiffCallback;
import com.wpf.rxjavaretrofitdemo.widget.EmptyRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
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
 * Created by wpf on 2017/11/25.
 *
 */

public abstract class BaseFragment<T extends BaseDataBean, E extends BaseModule<T>> extends Fragment {
    @BindView(R.id.basefragment_rv)
    EmptyRecyclerView basefragmentRv;
    @BindView(R.id.basefragment_sr)
    SmartRefreshLayout basefragmentSr;
    @BindView(R.id.basefragment_av)
    AVLoadingIndicatorView basefragmentAv;
    @BindView(R.id.basefragment_ev)
    View basefragmentEv;
    Unbinder unbinder;
    private BaseItemAdapter baseAdapter;
    private int page;
    private boolean NO_MORE_DATA = false;
    private List<T> list = new ArrayList<>();
    private List<T> newList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.basefragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        initConfigData();
        initView();
        initListener();
        loadData(page++, GET_DATA_TYPE_NORMAL);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void initConfigData(){
        page = 1;
        list.clear();
        newList.clear();
    }

    private void initView() {
        basefragmentAv.bringToFront();
        basefragmentAv.show();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        basefragmentRv.setLayoutManager(linearLayoutManager);
        baseAdapter = initAdapter(new ArrayList<T>());
        basefragmentRv.setAdapter(baseAdapter);
        basefragmentRv.setItemAnimator(new ScaleUpAnimator());
        basefragmentRv.setEmptyView(basefragmentEv);
    }

    protected abstract BaseItemAdapter initAdapter(List<T> list);

    protected abstract Observable<E> initObservable(int page);

    /**
     * @param page The data page
     * @param type Loadmore = 1 or Refresh = 0
     */
    protected void loadData(int page, final int type){
        Observable<E> observable = initObservable(page);
        observable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(new Function<E, DiffUtil.DiffResult>() {
                    @Override
                    public DiffUtil.DiffResult apply(E e) {
                        if (e.getData().size() < PAGE_SIZE) {
                            setNoMoreData(true);
                        }
                        newList.clear();
                        if (type == GET_DATA_TYPE_LOADMORE) {
                            newList.addAll(list);
                        }
                        newList.addAll(e.getData());
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
                        baseAdapter.setData(newList);
                        diffResult.dispatchUpdatesTo(baseAdapter);
                        list.clear();
                        list.addAll(newList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        hiedAllFailed();
                        Toast.makeText(getContext(), "网络错误！", Toast.LENGTH_SHORT).show();
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
                loadData(page++, GET_DATA_TYPE_NORMAL);
            }
        });
        basefragmentSr.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                loadData(page++, GET_DATA_TYPE_LOADMORE);
            }
        });
    }

    protected void hiedAllFailed() {
        if (basefragmentSr != null) {
            basefragmentSr.finishLoadmore(false);
            basefragmentSr.finishRefresh(false);
        }
        if (basefragmentAv != null) {
            basefragmentAv.hide();
        }
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
        if (basefragmentAv != null) {
            basefragmentAv.hide();
        }
    }

    protected void setNoMoreData(boolean NO_MORE_DATA) {
        this.NO_MORE_DATA = NO_MORE_DATA;
    }
}
