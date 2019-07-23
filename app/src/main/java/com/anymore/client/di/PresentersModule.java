package com.anymore.client.di;

import com.anymore.client.mvp.login.LoginContract;
import com.anymore.client.mvp.login.LoginPresenter;
import dagger.Binds;
import dagger.Module;

/**
 * 所有Presenter的依赖
 * Created by liuyuanmao on 2019/7/16.
 */
@Module
public abstract class PresentersModule {
    
    @Binds
    public abstract LoginContract.IPresenter bindLoginPresenter(LoginPresenter presenter);
}
