package com.wochacha.learnrxjava.module.map_2;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.wochacha.learnrxjava.BaseFragment;
import com.wochacha.learnrxjava.R;
import com.wochacha.learnrxjava.adapter.ItemListAdapter;
import com.wochacha.learnrxjava.model.Item;
import com.wochacha.learnrxjava.network.Network;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends BaseFragment {

    private int page = 0;

    @Bind(R.id.tv_page) TextView tvPage;
    @Bind(R.id.btn_previous_page) AppCompatButton btnPreviousPage;
    @Bind(R.id.btn_next_page) AppCompatButton btnNextPage;
    @Bind(R.id.swipeRefreshLayout) SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.gridRv) RecyclerView rvMap;
    private ItemListAdapter mAdapter = new ItemListAdapter();

    public MapFragment() {
        // Required empty public constructor
    }

    Observer<List<Item>> observer = new Observer<List<Item>>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            swipeRefreshLayout.setRefreshing(false);
        }

        @Override
        public void onNext(List<Item> items) {
            swipeRefreshLayout.setRefreshing(false);
            Logger.e(page+" ... "+items.toString());
//            tvPage.setText(getString(R.string.page_with_number,page));
            tvPage.setText(String.format(getString(R.string.page_with_number),page));
            mAdapter.setItems(items);
        }
    };

    Subscriber<List<Item>> subscriber = new Subscriber<List<Item>>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(List<Item> items) {

        }
    };

    public rx.Observable<String> getI(){
        return rx.Observable.just("a","b");
    }

    @OnClick(R.id.btn_previous_page)
    void previousPage(){
        loadPage(--page);
        if (page == 1){
            btnPreviousPage.setEnabled(false);
        }
    }

    @OnClick(R.id.btn_next_page)
    void nextPage(){
        loadPage(++page);
        if (page == 2){
            btnPreviousPage.setEnabled(true);
        }
    }

    private void loadPage(int page){
        swipeRefreshLayout.setRefreshing(true);
        unsubscribe();
        subscription = Network.getGankApi()
                .getBeauties(10,page)
                .map(GankBeautyResultToItemsMapper.getInstance())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvMap.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        rvMap.setAdapter(mAdapter);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        swipeRefreshLayout.setEnabled(false);
        btnNextPage.performClick();
    }

    @Override
    protected int getDialogRes() {
        return 0;
    }

    @Override
    protected int getTitleRes() {
        return R.string.title_map;
    }
}
