package com.anymore.kotlindaggertest

import android.app.Activity
import android.app.Application
import android.content.Context
import com.anymore.kotlindaggertest.di.AppModule
import com.anymore.kotlindaggertest.di.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by liuyuanmao on 2019/2/22.
 */
class APP:Application(),HasActivityInjector {

    @Inject
    lateinit var mActivityInjector: DispatchingAndroidInjector<Activity>

    override fun attachBaseContext(base: Context?) {
        DaggerAppComponent.builder().appModule(AppModule(this)).build().inject(this)
        super.attachBaseContext(base)
    }

    override fun activityInjector()=mActivityInjector
}