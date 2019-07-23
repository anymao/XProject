package com.anymore.client.mvp.login;

import android.app.Application;
import com.anymore.client.utils.RxUtils;
import com.anymore.mvpkit.di.scope.ActivityScope;
import com.anymore.mvpkit.mvp.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

/**
 * Created by liuyuanmao on 2019/7/16.
 */
@ActivityScope
public class LoginPresenter extends BasePresenter<LoginContract.IView> implements LoginContract.IPresenter {

    private LoginContract.IModel mModel;

    @Inject
    public LoginPresenter(@NotNull Application application,LoginContract.IView view,LoginContract.IModel model) {
        super(application,view);
        this.mModel = model;
    }

    @Override
    public void mockLogin(String username, String password) {
        Disposable disposable = mModel.login(username, password)
                .compose(RxUtils.withLoading(getMView(),"正在登陆...",false))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(success -> {
                    if (success) {
                        getMView().loginSuccess();
                    } else {
                        getMView().showError("账号或者密码错误!");
                    }
                }, throwable -> getMView().showError(throwable.getMessage()));
        addDisposable(disposable);
    }

}
