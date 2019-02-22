package com.anymore.javatest.di.module;

import android.app.Activity;
import com.anymore.javatest.MainActivity;
import com.anymore.javatest.di.component.subcomponent.MainActivitySubComponent;
import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by liuyuanmao on 2019/2/22.
 */
@Module(subcomponents = {MainActivitySubComponent.class})
public abstract class MainActivityModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract public AndroidInjector.Factory<? extends Activity> bindMainActivityInjector(MainActivitySubComponent.Builder builder);
}
