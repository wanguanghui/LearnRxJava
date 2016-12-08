package com.wochacha.learnrxjava.module.zip_3;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wochacha.learnrxjava.BaseFragment;
import com.wochacha.learnrxjava.R;
import com.wochacha.learnrxjava.adapter.ItemListAdapter;
import com.wochacha.learnrxjava.model.Item;
import com.wochacha.learnrxjava.model.ZhuangBiImage;
import com.wochacha.learnrxjava.module.map_2.GankBeautyResultToItemsMapper;
import com.wochacha.learnrxjava.network.Network;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * 压合
 * A simple {@link Fragment} subclass.
 */
public class ZipFragment extends BaseFragment {

    @Bind(R.id.swipeRefreshLayout) SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.gridRv) RecyclerView rvZip;
    ItemListAdapter adapter = new ItemListAdapter();

    public ZipFragment() {

    }

    Observer<List<Item>> observer = new Observer<List<Item>>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(List<Item> items) {
            swipeRefreshLayout.setRefreshing(false);
            adapter.setItems(items);
        }
    };



    private void loadData() {
        swipeRefreshLayout.setRefreshing(true);
        unsubscribe();
        subscription = Observable
                .zip(
                        Network.getGankApi().getBeauties(200, 1).map(GankBeautyResultToItemsMapper.getInstance()),
                        Network.getZhuangBiApi().search("可爱"),
                        new Func2<List<Item>, List<ZhuangBiImage>, List<Item>>() {
                            @Override
                            public List<Item> call(List<Item> gankItems, List<ZhuangBiImage> zhuangBiImages) {
                                List<Item> items = new ArrayList<>();
                                for (int i = 0;i<gankItems.size()/2&&i<zhuangBiImages.size();i++){
                                    items.add(gankItems.get(i*2));
                                    items.add(gankItems.get(i*2+1));
                                    Item zhuangBiItem = new Item();
                                    ZhuangBiImage zhuangBiImage = zhuangBiImages.get(i);
                                    zhuangBiItem.setDescription(zhuangBiImage.getDescription());
                                    zhuangBiItem.setImageUrl(zhuangBiImage.getImage_url());
                                    items.add(zhuangBiItem);
                                }
                                return items;
                            }
                        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zip,container,false);
        ButterKnife.bind(this,view);

        rvZip.setLayoutManager(new GridLayoutManager(getActivity(),2));
        rvZip.setAdapter(adapter);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        swipeRefreshLayout.setEnabled(false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadData();
    }

    @Override
    protected int getDialogRes() {
        return 0;
    }

    @Override
    protected int getTitleRes() {
        return 0;
    }
}
