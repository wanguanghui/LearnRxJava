package com.wochacha.learnrxjava.network.api;

import android.support.annotation.NonNull;

import com.wochacha.learnrxjava.model.FakeThing;
import com.wochacha.learnrxjava.model.FakeToken;

import java.util.Random;

import rx.Observable;
import rx.functions.Func1;

/**
 * @version V9.0.0
 * @author: guanghui_wan
 * @date: 2016/12/12
 */

public class FakeApi {

    Random random = new Random();

    public Observable<FakeToken> getFakeToken(@NonNull final String fakeAuth){
        return Observable.just(fakeAuth)
                .map(new Func1<String, FakeToken>() {
                    @Override
                    public FakeToken call(String s) {
                        int fakeNetworkTimeCost = random.nextInt(500)+500;
                        try {
                            Thread.sleep(fakeNetworkTimeCost);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        FakeToken fakeToken = new FakeToken();
                        fakeToken.token = createToken();
                        return fakeToken;
                    }
                });
    }

    private static String createToken(){
        return "fake_token_" + System.currentTimeMillis()%10000;
    }

    public Observable<FakeThing> getFakeData(FakeToken fakeToken){

        return Observable.just(fakeToken)
                .map(new Func1<FakeToken, FakeThing>() {
                    @Override
                    public FakeThing call(FakeToken fakeToken) {
                        int fakeNetworkTimecost = random.nextInt(500) + 500;
                        try {
                            Thread.sleep(fakeNetworkTimecost);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (fakeToken.expired){
                            throw new IllegalArgumentException("Token expried");
                        }

                        FakeThing fakeData = new FakeThing();
                        fakeData.setId((int) (System.currentTimeMillis()%10000));
                        fakeData.setName("FAKE_User_" + fakeData.getId());
                        return fakeData;
                    }
                });
    }

}
