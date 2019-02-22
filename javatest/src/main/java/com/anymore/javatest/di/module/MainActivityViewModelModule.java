package com.anymore.javatest.di.module;

import android.arch.lifecycle.ViewModel;
import com.anymore.javatest.MainActivityViewModel;
import com.anymore.javatest.di.ViewModelKey;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by liuyuanmao on 2019/2/22.
 */
@Module
public abstract class MainActivityViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel.class)
    public abstract ViewModel provideMainActivityViewModel(MainActivityViewModel viewModel);
}
