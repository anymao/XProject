package com.anymore.example.mvvm.model

import android.app.Application
import com.anymore.example.mvvm.model.api.KEY
import com.anymore.example.mvvm.model.api.WanAndroidUserApi
import com.anymore.example.mvvm.model.db.AppDatabase
import com.anymore.example.mvvm.model.db.entry.UserInfo
import com.anymore.example.mvvm.model.entry.WanAndroidResponse
import com.anymore.mvvmkit.mvvm.base.BaseModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * 用户管理model层，与用户信息有关的操作在这里
 * Created by liuyuanmao on 2019/3/28.
 */
class UserModel @Inject constructor(application: Application):BaseModel(application){

    private val mUserApi by lazy { mRepositoryComponent.getRepository().obtainRetrofitService(KEY,WanAndroidUserApi::class.java) }
    private val mAppDao by lazy { mRepositoryComponent.getRepository().obtainRoomDatabase(AppDatabase::class.java,AppDatabase.DB_NAME) }
    private val mUserDao by lazy { mAppDao.userInfoDao() }

    fun register(username:String,pwd:String,rePwd:String):Observable<WanAndroidResponse<UserInfo>> = mUserApi
        .register(username,pwd,rePwd)
        .subscribeOn(Schedulers.io())
        .doOnNext{
            if (it.errorCode == 0 && it.data!=null){
                mUserDao.insert(it.data)
            }
        }
        .observeOn(AndroidSchedulers.mainThread())

    fun login(username: String,pwd: String):Observable<WanAndroidResponse<UserInfo>> = mUserApi
        .login(username,pwd)
        .subscribeOn(Schedulers.io())
        .doOnNext{
            if (it.errorCode == 0 && it.data!=null){
                it.data.online = true
                mUserDao.insert(it.data)
            }
        }
        .observeOn(AndroidSchedulers.mainThread())

    fun logout():Observable<WanAndroidResponse<String>> = mUserApi
        .logout()
        .subscribeOn(Schedulers.io())
        .doOnNext{
            if (it.errorCode == 0 && it.data!=null){
                mUserDao.updateOnlineStatus(false)
            }
        }
        .observeOn(AndroidSchedulers.mainThread())

    fun getCurrentUser()=mUserDao.getCurrentUser().subscribeOn(Schedulers.io())

}