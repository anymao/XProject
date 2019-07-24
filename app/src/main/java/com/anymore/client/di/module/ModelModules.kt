package com.anymore.client.di.module

import com.anymore.client.ui.mvp.login.LoginContract
import com.anymore.client.ui.mvp.login.LoginModel
import dagger.Binds
import dagger.Module

/**
 * 聚合所有的XXXModelModule的.kt文件
 * 其实，如果Model层的接口不被复用的情况下，即每个IModel接口只有一个实现类的情况下
 * 将所有的Model的Bind操作放到一个ModelsModule类中进行@Binds操作也是可行的
 * 但是，MVP的初衷就是互相以接口调用，不要关心具体实现，所以当一个Module中实现两个相同的
 * 接口Binds的时候，会出现一些问题(当然此问题其实可以用@Named来解决，但是本身框架把注入操作写到基类中，无法再添加@Named注解了)
 * 所以推荐按照M-V-P将三者的DaggerModule部分区分开，这样更加明确Dagger注入时候的依赖关系！
 * Created by liuyuanmao on 2019/7/23.
 */
@Module
abstract class LoginModelModule {
    @Binds
    abstract fun provideLoginModel(loginModel: LoginModel):LoginContract.IModel
}