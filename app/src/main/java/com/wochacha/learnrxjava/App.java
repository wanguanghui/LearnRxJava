package com.wochacha.learnrxjava;

import android.app.Application;

/**
 * Created by guanghui_wan on 2016/11/30.
 */

public class App extends Application{

    private static App instance;

    public static App getInstance(){
     return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
