package com.anymore.client.mvp.login;

import com.anymore.mvpkit.mvp.base.BaseContract;
import io.reactivex.Observable;

/**
 * Created by liuyuanmao on 2019/7/16.
 */
public interface LoginContract {
    interface IView extends BaseContract.IBaseView{
        void loginSuccess();
    }

    interface IPresenter extends BaseContract.IBasePresenter{
        void mockLogin(String username,String password);
    }

    interface IModel extends BaseContract.IBaseModel{
        Observable<Boolean> login(String username,String password);
    }
}
