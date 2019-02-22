package com.anymore.javatest.di;

import com.anymore.javatest.APP;
import com.anymore.javatest.di.module.AppModule;
import com.anymore.javatest.di.module.MainActivityModule;
import com.anymore.javatest.di.module.ViewModelFactoryModule;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by liuyuanmao on 2019/2/22.
 */
@Component(modules = {AndroidInjectionModule.class,MainActivityModule.class,ViewModelFactoryModule.class,AppModule.class})
public interface AppComponent {
    void inject(APP app);
}
