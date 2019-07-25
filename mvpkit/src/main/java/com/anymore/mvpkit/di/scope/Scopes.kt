package com.anymore.mvpkit.di.scope

import javax.inject.Scope

/**
 * Dagger2 作用域限定符
 * Created by liuyuanmao on 2019/2/21.
 */
@Scope
@Retention(AnnotationRetention .RUNTIME)
annotation class ActivityScope

@Scope
@Retention(AnnotationRetention .RUNTIME)
annotation class FragmentScope

@Scope
@Retention(AnnotationRetention .RUNTIME)
annotation class ApplicationScope