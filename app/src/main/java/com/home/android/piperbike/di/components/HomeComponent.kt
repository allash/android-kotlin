package com.home.android.piperbike.di.components

import com.home.android.piperbike.di.module.NetworkModule
import com.home.android.piperbike.views.activities.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface HomeComponent: BaseActivitiyComponent<MainActivity> {

    @Component.Builder
    interface Builder {
        fun build(): HomeComponent
        @BindsInstance fun mainActivity(activity: MainActivity): Builder
        fun networkModule(networkModule: NetworkModule): Builder
       //  fun sharedPreferencesModule(module: SharedPreferencesModule): Builder
    }
}