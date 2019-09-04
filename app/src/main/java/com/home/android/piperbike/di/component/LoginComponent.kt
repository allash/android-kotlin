package com.home.android.piperbike.di.component

import com.home.android.piperbike.di.BaseActivityComponent
import com.home.android.piperbike.di.module.LoginModule
import com.home.android.piperbike.di.module.NetworkModule
import com.home.android.piperbike.di.module.SharedPreferencesModule
import com.home.android.piperbike.views.activities.LoginActivity
import dagger.BindsInstance
import dagger.Component

@Component(modules = [LoginModule::class, NetworkModule::class, SharedPreferencesModule::class])
interface LoginComponent: BaseActivityComponent<LoginActivity> {
    @Component.Builder
    interface Builder {
        fun build(): LoginComponent
        @BindsInstance fun activity(activity: LoginActivity): Builder
        fun loginModule(loginModule: LoginModule): Builder
        fun networkModule(networkModule: NetworkModule): Builder
        fun sharedPreferencesModule(sharedPreferencesModule: SharedPreferencesModule): Builder
    }
}