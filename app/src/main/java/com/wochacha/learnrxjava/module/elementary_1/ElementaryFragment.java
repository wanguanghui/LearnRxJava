package com.wochacha.learnrxjava.module.elementary_1;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.wochacha.learnrxjava.BaseFragment;
import com.wochacha.learnrxjava.R;
import com.wochacha.learnrxjava.adapter.ZhuangBiListAdapter;
import com.wochacha.learnrxjava.model.ZhuangBiImage;
import com.wochacha.learnrxjava.network.Network;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class ElementaryFragment extends BaseFragment {

    @Bind(R.id.swipeRefreshLayout) SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.gridRv) RecyclerView gridRv;
    @Bind(R.id.et_content) EditText etContent;
    @Bind(R.id.lL_input) LinearLayout lLInput;

    ZhuangBiListAdapter adapter = new ZhuangBiListAdapter();


    Observer<List<ZhuangBiImage>> observer = new Observer<List<ZhuangBiImage>>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            swipeRefreshLayout.setRefreshing(false);
            Log.e("abc",e.getMessage());
            Toast.makeText(getActivity(),"数据加载失败",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNext(List<ZhuangBiImage> zhuangBiImages) {
            swipeRefreshLayout.setRefreshing(false);
            adapter.setImages(zhuangBiImages);
            Log.e("abc",zhuangBiImages.toString());
        }
    };

    Observer<String> observer2 = new Observer<String>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {
            Log.e("abc",s);
        }
    };


    @OnCheckedChanged({R.id.searchRb1,R.id.searchRb2,R.id.searchRb3,R.id.searchRb4,R.id.searchRb5})
    void onTabChecked(RadioButton searchRb , boolean isChecked){
        if (isChecked){
            unsubscribe();
            adapter.setImages(null);
            if ("其他".equals(searchRb.getText().toString())){
                lLInput.setVisibility(View.VISIBLE);
            }else {
                swipeRefreshLayout.setRefreshing(true);
                lLInput.setVisibility(View.GONE);
                search(searchRb.getText().toString());
            }
        }
    }

    @OnClick({R.id.btn_search})
    void onClick(){
        swipeRefreshLayout.setRefreshing(true);
        search(etContent.getText().toString().trim());
    }

    private void search(String key){
        subscription = Network.getZhuangBiApi()
                .search(key)
//                .map(new Func1<String, List<ZhuangBiImage>>() {
//
//                    @Override
//                    public List<ZhuangBiImage> call(String s) {
//                        return new Gson().fromJson(s,new TypeToken<List<ZhuangBiImage>>(){}.getType());
//                    }
//                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_elementary, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gridRv.setLayoutManager(new GridLayoutManager(getActivity(),2));
        gridRv.setAdapter(adapter);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        swipeRefreshLayout.setEnabled(false);

    }

    @Override
    protected int getDialogRes() {
        return 0;
    }

    @Override
    protected int getTitleRes() {
        return R.string.title_elementary;
    }
}
