package com.anymore.client;

import android.content.Context;
import com.anymore.client.di.AppModule;
import com.anymore.client.di.DaggerAppComponent;
import com.anymore.mvpkit.mvp.lifecycle.KitApplication;
import org.jetbrains.annotations.Nullable;

/**
 * Created by liuyuanmao on 2019/7/16.
 */
public class App extends KitApplication {
    @Override
    protected void attachBaseContext(@Nullable Context base) {
        super.attachBaseContext(base);
        DaggerAppComponent.builder().appModule(new AppModule(this)).build().inject(this);
    }
}
