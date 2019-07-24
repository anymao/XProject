package com.anymore.client.di.module

import com.anymore.client.ui.mvp.login.fragment.LoginFragment
import com.anymore.mvpkit.di.scope.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by liuyuanmao on 2019/7/24.
 */
@Module
abstract class FragmentBindingModule {

    /**
     * 建议将ModelsModule拆分成为LoginModelModule，XXXModelModule,....等
     * 同理PresentersModule和ViewsModule也做如此的拆分，这样做，在多个Fragment或者Activity实现同一个接口的时候
     * 依赖关系更加的明确
     */
    @FragmentScope
    @ContributesAndroidInjector(modules = [LoginModelModule::class,LoginPresenter2Module::class,LoginFragmentViewModule::class])
    abstract fun contributeLoginFragment():LoginFragment
}