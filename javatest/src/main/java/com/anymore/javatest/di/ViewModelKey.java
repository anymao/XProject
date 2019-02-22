package com.anymore.javatest.di;

import android.arch.lifecycle.ViewModel;
import dagger.MapKey;
import dagger.internal.Beta;

import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;

/**
 * Created by liuyuanmao on 2019/2/22.
 */
@Beta
@MapKey
@Target(METHOD)
public @interface ViewModelKey {
    Class<? extends ViewModel> value();
}