package com.anymore.javatest;

import android.app.Application;
import android.arch.lifecycle.*;
import android.support.annotation.NonNull;

import javax.inject.Inject;

/**
 * Created by liuyuanmao on 2019/2/22.
 */
public class MainActivityViewModel extends AndroidViewModel implements LifecycleObserver {
    private final MutableLiveData<String> mMsg = new MutableLiveData<>();
    @Inject
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart(){
        mMsg.postValue("onStart");
    }

    public MutableLiveData<String> getMessage() {
        return mMsg;
    }
}
