package com.wochacha.learnrxjava.module.token_4;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.wochacha.learnrxjava.BaseFragment;
import com.wochacha.learnrxjava.R;
import com.wochacha.learnrxjava.model.FakeThing;
import com.wochacha.learnrxjava.model.FakeToken;
import com.wochacha.learnrxjava.network.Network;
import com.wochacha.learnrxjava.network.api.FakeApi;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class TokenFragment extends BaseFragment {

    @Bind(R.id.tv_token) TextView tvToken;
    @Bind(R.id.swipeRefreshLayout) SwipeRefreshLayout swipeRefreshLayout;

    @OnClick(R.id.btn_request)
    void upload(){

        swipeRefreshLayout.setRefreshing(true);
        unsubscribe();
        final FakeApi fakeApi = Network.getFakeApi();
        subscription = fakeApi.getFakeToken("fake_auth_code")
                .flatMap(new Func1<FakeToken, Observable<FakeThing>>() {
                    @Override
                    public Observable<FakeThing> call(FakeToken fakeToken) {
                        return fakeApi.getFakeData(fakeToken);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<FakeThing>() {
                    @Override
                    public void call(FakeThing fakeThing) {
                        swipeRefreshLayout.setRefreshing(false);
                        tvToken.setText(getString(R.string.got_data, fakeThing.getId(), fakeThing.getName()));
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(getActivity(),"数据加载失败",Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public TokenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_token, container, false);
        ButterKnife.bind(this,view);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW);
        swipeRefreshLayout.setEnabled(false);
        return view;
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
