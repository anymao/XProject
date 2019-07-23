package com.anymore.client.di;

import android.app.Application;
import dagger.Module;
import dagger.Provides;

/**
 * Created by liuyuanmao on 2019/7/16.
 */
@Module(includes = ActivityBindingModule.class)
public class AppModule {
    private Application mApplication;

    public AppModule(Application application) {
        this.mApplication = application;
    }

    @Provides
    public Application provideApplication(){
        return mApplication;
    }
}
