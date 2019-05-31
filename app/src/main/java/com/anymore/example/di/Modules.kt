package com.anymore.example.di

import android.app.Activity
import android.app.Application
import android.arch.lifecycle.ViewModel
import com.anymore.example.mvvm.model.MainModel
import com.anymore.example.mvvm.model.TodoModel
import com.anymore.example.mvvm.model.UserModel
import com.anymore.example.mvvm.view.knowledges.KnowledgesArticlesFragment
import com.anymore.example.mvvm.view.SplashActivity
import com.anymore.example.mvvm.view.collect.CollectedArticlesActivity
import com.anymore.example.mvvm.view.login.LoginActivity
import com.anymore.example.mvvm.view.main.DiscoveryFragment
import com.anymore.example.mvvm.view.main.HomeFragment
import com.anymore.example.mvvm.view.main.MainActivity
import com.anymore.example.mvvm.view.main.MyFragment
import com.anymore.example.mvvm.view.register.RegisterActivity
import com.anymore.example.mvvm.view.todo.TodoDetailFragment
import com.anymore.example.mvvm.view.todo.TodoFragment
import com.anymore.example.mvvm.view.web.ExtendedWebActivity
import com.anymore.example.mvvm.viewmodel.*
import com.anymore.mvvmkit.di.key.ViewModelKey
import com.anymore.mvvmkit.di.module.ViewModelFactoryModule
import com.anymore.mvvmkit.di.scope.ActivityScope
import com.anymore.mvvmkit.di.scope.FragmentScope
import com.anymore.mvvmkit.mvvm.base.BaseModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Created by liuyuanmao on 2019/3/11.
 */
@Module(includes = [ViewModelFactoryModule::class,
    SplashActivityModule::class,
    MainActivityModule::class,
    RegisterActivityModule::class,
    LoginActivityModule::class,
    HomeFragmentModule::class,
    DiscoveryFragmentModule::class,
    MyFragmentModule::class,
    KnowledgesArticlesFragmentModule::class,
    CollectedArticlesActivityModule::class,
    ExtendedWebActivityModule::class,
    TodoFragmentModule::class,
    TodoDetailFragmentModule::class])
class ExampleAppModule(private val application: Application){

    @Provides
    fun provideApplication()=application
}
///////////SplashActivity  begin/////////////////////
@Module
abstract class SplashActivityViewModelModule{

    @Binds
    @IntoMap
    @ViewModelKey(SplashActivityViewModel::class)
    abstract fun provideSplashActivityViewModel(viewModel: SplashActivityViewModel):ViewModel
}

@Module
abstract class SplashActivityModule{

    @ActivityScope
    @ContributesAndroidInjector(modules = [UserModelModule::class,SplashActivityViewModelModule::class])
    abstract fun contributeSplashActivity(): SplashActivity
}

/////////////SplashActivity  end/////////////////////



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

/////////////RegisterActivity  start///////////////
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
class UserModelModule{

    @Provides
    @ActivityScope
    fun provideRegisterUserModel(model: UserModel):BaseModel=model
}
/////////////RegisterActivity  end///////////////

/////////////LoginActivity  start////////////////

@Module
abstract class LoginActivityViewModelModule{
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun provideLoginActivityViewModel(viewModel: LoginViewModel):ViewModel
}

@Module(subcomponents = [LoginActivitySubComponent::class])
abstract class LoginActivityModule{
    @Binds
    @IntoMap
    @ActivityKey(LoginActivity::class)
    abstract fun bindActivityInjectorFactory(builder: LoginActivitySubComponent.Builder): AndroidInjector.Factory<out Activity>

}
/////////////LoginActivity  end////////////////

/////////////HomeFragment  start////////////////

@Module
abstract class HomeFragmentModule{

    @FragmentScope
    @ContributesAndroidInjector(modules = [MainModelModule::class,HomeFragmentViewModelModule::class])
    abstract fun contributeHomeFragment():HomeFragment
}

@Module
abstract class HomeFragmentViewModelModule{

    @Binds
    @IntoMap
    @ViewModelKey(HomeFragmentViewModel::class)
    abstract fun bindHomeFragmentViewModel(viewModel: HomeFragmentViewModel):ViewModel
}

/////////////HomeFragment  end////////////////

/////////////DiscoveryFragment  start////////////////

@Module
abstract class DiscoveryFragmentModule{

    @FragmentScope
    @ContributesAndroidInjector(modules = [MainModelModule::class,DiscoveryFragmentViewModelModule::class])
    abstract fun contributeDiscoveryFragment():DiscoveryFragment
}

@Module
abstract class DiscoveryFragmentViewModelModule{

    @Binds
    @IntoMap
    @ViewModelKey(DiscoveryFragmentViewModel::class)
    abstract fun bindDiscoveryFragmentViewModel(viewModel: DiscoveryFragmentViewModel):ViewModel
}

/////////////DiscoveryFragment  end////////////////

/////////////MyFragment  start////////////////

@Module
abstract class MyFragmentModule{

    @FragmentScope
    @ContributesAndroidInjector(modules = [MainModelModule::class,MyFragmentViewModelModule::class])
    abstract fun contributeMyFragment(): MyFragment
}

@Module
abstract class MyFragmentViewModelModule{

    @Binds
    @IntoMap
    @ViewModelKey(MyFragmentViewModel::class)
    abstract fun bindMyFragmentViewModel(viewModel: MyFragmentViewModel):ViewModel
}

/////////////DiscoveryFragment  end////////////////

/////////////KnowledgesArticlesFragment  start////////////////

@Module
abstract class KnowledgesArticlesFragmentModule{

    @FragmentScope
    @ContributesAndroidInjector(modules = [MainModelModule::class,KnowledgesArticlesFragmentViewModelModule::class])
    abstract fun contributeKnowledgesArticlesFragment(): KnowledgesArticlesFragment
}

@Module
abstract class KnowledgesArticlesFragmentViewModelModule{

    @Binds
    @IntoMap
    @ViewModelKey(KnowledgesArticlesFragmentViewModel::class)
    abstract fun bindKnowledgesArticlesFragmentViewModel(viewModel: KnowledgesArticlesFragmentViewModel):ViewModel
}

/////////////KnowledgesArticlesFragment  end////////////////

///////////CollectedArticlesActivity  begin/////////////////////
@Module
abstract class CollectedArticlesActivityViewModelModule{

    @Binds
    @IntoMap
    @ViewModelKey(CollectedArticlesActivityViewModel::class)
    abstract fun provideCollectedArticlesActivityViewModel(viewModel: CollectedArticlesActivityViewModel):ViewModel
}

@Module
abstract class CollectedArticlesActivityModule{

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainModelModule::class,CollectedArticlesActivityViewModelModule::class])
    abstract fun contributeCollectedArticlesActivity(): CollectedArticlesActivity
}

/////////////CollectedArticlesActivity  end/////////////////////

///////////ExtendedWebActivity  begin/////////////////////
@Module
abstract class ExtendedWebActivityViewModelModule{

    @Binds
    @IntoMap
    @ViewModelKey(ExtendedWebActivityViewModel::class)
    abstract fun provideExtendedWebActivityViewModel(viewModel: ExtendedWebActivityViewModel):ViewModel
}

@Module
abstract class ExtendedWebActivityModule{

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainModelModule::class,ExtendedWebActivityViewModelModule::class])
    abstract fun contributeExtendedWebActivity(): ExtendedWebActivity
}

/////////////ExtendedWebActivity  end/////////////////////

/////////////TodoFragment  start////////////////

@Module
abstract class TodoFragmentModule{

    @FragmentScope
    @ContributesAndroidInjector(modules = [TodoModelModule::class,TodoFragmentViewModelModule::class])
    abstract fun contributeTodoFragment(): TodoFragment
}

@Module
abstract class TodoFragmentViewModelModule{

    @Binds
    @IntoMap
    @ViewModelKey(TodoFragmentViewModel::class)
    abstract fun bindTodoFragmentViewModel(viewModel: TodoFragmentViewModel):ViewModel
}

@Module
class TodoModelModule{

    @FragmentScope
    @Provides
    fun provideTodoModel(model:TodoModel):BaseModel=model
}

/////////////TodoFragment  end////////////////

/////////////TodoDetailFragment  start////////////////

@Module
abstract class TodoDetailFragmentModule{

    @FragmentScope
    @ContributesAndroidInjector(modules = [TodoModelModule::class,TodoDetailFragmentViewModelModule::class])
    abstract fun contributeTodoDetailFragment(): TodoDetailFragment
}

@Module
abstract class TodoDetailFragmentViewModelModule{

    @Binds
    @IntoMap
    @ViewModelKey(TodoDetailFragmentViewModel::class)
    abstract fun bindTodoDetailFragmentViewModel(viewModel: TodoDetailFragmentViewModel):ViewModel
}

/////////////TodoDetailFragment  end////////////////