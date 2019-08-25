package com.home.android.piperbike.di

import com.home.android.piperbike.di.module.NetworkModule
import com.home.android.piperbike.ui.activity.ActivityListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ViewModelInjector {
    fun inject(activityListViewModel: ActivityListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector
        fun networkModule(networkModule: NetworkModule): Builder
        // fun sharedPreferencesModule(sharedPreferencesModule: SharedPreferencesModule): Builder
    }
}