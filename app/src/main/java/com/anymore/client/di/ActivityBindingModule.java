package com.anymore.client.di;

import com.anymore.client.mvp.login.LoginActivity;
import com.anymore.mvpkit.di.scope.ActivityScope;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by liuyuanmao on 2019/7/16.
 */
@Module
public abstract class ActivityBindingModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = {PresentersModule.class,ViewsModule.class,ModelsModule.class})
    public abstract LoginActivity contributesLoginActivity();

}
