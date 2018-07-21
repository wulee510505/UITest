package com.example.wulee.uitest;

import android.app.Application;
import android.content.Context;

/**
 * create by  wulee   2018/5/17 17:21
 * desc:
 */
public class App extends Application {

    private static App instance;
    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        context = getApplicationContext();
    }

    public synchronized static App getInstance() {
        return instance;
    }

    public Context getContext() {
        return context;
    }
}
