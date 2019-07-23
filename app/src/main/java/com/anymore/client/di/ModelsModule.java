package com.anymore.client.di;

import com.anymore.client.mvp.login.LoginContract;
import com.anymore.client.mvp.login.LoginModel;
import dagger.Binds;
import dagger.Module;

/**
 * 所有Model的依赖
 * Created by liuyuanmao on 2019/7/22.
 */
@Module
public abstract class ModelsModule {

    @Binds
    abstract LoginContract.IModel bindLoginModel(LoginModel model);
}
