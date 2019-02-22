package com.anymore.javatest.di.module;

import android.arch.lifecycle.ViewModelProvider;
import com.anymore.javatest.ViewModelFactory;
import dagger.Binds;
import dagger.Module;

/**
 * Created by liuyuanmao on 2019/2/22.
 */
@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory provideViewModelFactory(ViewModelFactory factory);
}
