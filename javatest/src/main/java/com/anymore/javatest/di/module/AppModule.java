package com.anymore.javatest.di.module;

import android.app.Application;
import dagger.Module;
import dagger.Provides;

/**
 * Created by liuyuanmao on 2019/2/22.
 */
@Module
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    public Application provideApplication(){
        return application;
    }
}
