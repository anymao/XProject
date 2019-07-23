package com.anymore.client.mvp.login;

import android.app.Application;
import android.text.TextUtils;
import com.anymore.mvpkit.mvp.base.BaseModel;
import io.reactivex.Observable;
import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

/**
 * Created by liuyuanmao on 2019/7/22.
 */
public class LoginModel extends BaseModel implements LoginContract.IModel{

    @Inject
    public LoginModel(@NotNull Application mApplication) {
        super(mApplication);
    }


    @Override
    public Observable<Boolean> login(String username, String password) {
        return Observable.create(emitter -> {
            Thread.sleep(5000);
            emitter.onNext(TextUtils.equals(username,"anymore") && TextUtils.equals(password,"12345"));
            emitter.onComplete();
        });
    }
}
