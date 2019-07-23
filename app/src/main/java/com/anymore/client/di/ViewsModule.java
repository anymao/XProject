package com.anymore.client.di;

import com.anymore.client.mvp.login.LoginActivity;
import com.anymore.client.mvp.login.LoginContract;
import dagger.Binds;
import dagger.Module;

/**
 * 所有View层的依赖
 * Created by liuyuanmao on 2019/7/17.
 */
@Module
public abstract class ViewsModule {

    @Binds
    public abstract LoginContract.IView bindLoginView(LoginActivity loginActivity);
}
