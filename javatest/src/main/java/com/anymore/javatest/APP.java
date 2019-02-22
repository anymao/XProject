package com.anymore.javatest;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import com.anymore.javatest.di.DaggerAppComponent;
import com.anymore.javatest.di.module.AppModule;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

import javax.inject.Inject;

/**
 * Created by liuyuanmao on 2019/2/22.
 */
public class APP extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> mActivityInjector;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        DaggerAppComponent.builder().appModule(new AppModule(this)).build().inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return mActivityInjector;
    }
}
