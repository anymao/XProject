package com.anymore.client.di;

import com.anymore.client.App;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by liuyuanmao on 2019/7/16.
 */
@Component(modules = {AndroidSupportInjectionModule.class,AppModule.class})
public interface AppComponent {
    void inject(App app);
}
