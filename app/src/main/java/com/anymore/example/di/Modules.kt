package com.anymore.example.di

import android.app.Activity
import android.app.Application
import android.arch.lifecycle.ViewModel
import com.anymore.mvvmkit.di.key.ViewModelKey
import com.anymore.mvvmkit.di.module.ViewModelFactoryModule
import com.anymore.mvvmkit.di.scope.ActivityScope
import com.anymore.mvvmkit.mvvm.base.BaseModel
import com.anymore.example.mvvm.view.main.MainActivity
import com.anymore.example.mvvm.viewmodel.MainActivityViewModel
import com.anymore.example.mvvm.model.MainModel
import com.anymore.example.mvvm.model.RegisterModel
import com.anymore.example.mvvm.view.register.RegisterActivity
import com.anymore.example.mvvm.viewmodel.RegisterActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by liuyuanmao on 2019/3/11.
 */
@Module(includes = [ViewModelFactoryModule::class,
    MainActivityModule::class,
    RegisterActivityModule::class])
class ExampleAppModule(private val application: Application){

    @Provides
    fun provideApplication()=application
}


/////////////MainActivity  begin/////////////////////
@Module
abstract class MainActivityViewModelModule{

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun provideMainActivityViewModel(viewModel: MainActivityViewModel):ViewModel
}

@Module(subcomponents = [MainActivitySubComponent::class])
abstract class MainActivityModule{

    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    abstract fun bindActivityInjectorFactory(builder: MainActivitySubComponent.Builder): AndroidInjector.Factory<out Activity>

}

@Module
class MainModelModule{

    @Provides
    @ActivityScope
    fun provideMainModel(model: MainModel):BaseModel= model
}

/////////////MainActivity  end/////////////////////

/////////////RegisterActivity  end/////////////////////
@Module
abstract class RegisterActivityViewModelModule{
    @Binds
    @IntoMap
    @ViewModelKey(RegisterActivityViewModel::class)
    abstract fun provideRegisterActivityViewModel(viewModel: RegisterActivityViewModel):ViewModel
}

@Module(subcomponents = [RegisterActivitySubComponent::class])
abstract class RegisterActivityModule{

    @Binds
    @IntoMap
    @ActivityKey(RegisterActivity::class)
    abstract fun bindActivityInjectorFactory(builder: RegisterActivitySubComponent.Builder): AndroidInjector.Factory<out Activity>
}

@Module
class RegisterModelModule{

    @Provides
    @ActivityScope
    fun provideRegisterModel(model: RegisterModel):BaseModel=model
}