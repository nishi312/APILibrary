package com.apilibrary.demo;

import android.content.Context;

import com.apilibrary.demo.di.components.AppComponent;
import com.apilibrary.demo.di.components.DaggerAppComponent;
import com.facebook.stetho.Stetho;
import com.tumblr.remember.Remember;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import timber.log.Timber;
import timber.log.Timber.DebugTree;


public class App extends DaggerApplication {

    public static AppComponent appComponent;
    public static Context mContext;

    public static AppComponent getAppComponent() {
        return appComponent;
    }


    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        initRemember();
        initBuildTypeData();
    }


    private void initBuildTypeData() {
        switch (BuildConfig.BUILD_TYPE) {
            case AppConstants.ApkBuildType.UAT:
            case AppConstants.ApkBuildType.PROD: {
                //plantTimber();
                break;
            }
            case AppConstants.ApkBuildType.DEV: {
                plantTimber();
                initStetho();
                break;
            }
            default:
        }
    }

    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }

    private void initRemember() {
        Remember.init(appComponent.appContext(), this.getString(R.string.app_name));
    }

    private void initStetho() {
        Stetho.initializeWithDefaults(this);
    }

    private void plantTimber() {
        Timber.uprootAll();
        Timber.plant((new DebugTree()));
    }
}