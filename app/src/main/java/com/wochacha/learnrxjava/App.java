package com.wochacha.learnrxjava;

import android.app.Application;

import com.orhanobut.logger.Logger;

/**
 * Created by guanghui_wan on 2016/11/30.
 */

public class App extends Application{

    private static final String TAG = "abc";
    private static App instance;

    public static App getInstance(){
     return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Logger.init(TAG);
    }
}
