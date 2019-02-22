package com.anymore.javatest.di.component.subcomponent;

import com.anymore.javatest.MainActivity;
import com.anymore.javatest.di.module.MainActivityViewModelModule;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by liuyuanmao on 2019/2/22.
 */
@Subcomponent(modules = {MainActivityViewModelModule.class,MainActivityViewModelModule.class})
public interface MainActivitySubComponent extends AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity>{}
}
