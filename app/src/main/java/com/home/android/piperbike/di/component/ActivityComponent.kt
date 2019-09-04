package com.home.android.piperbike.di.component

import com.home.android.piperbike.di.BaseFragmentComponent
import com.home.android.piperbike.di.module.ActivityModule
import com.home.android.piperbike.di.module.NetworkModule
import com.home.android.piperbike.di.module.SharedPreferencesModule
import com.home.android.piperbike.views.fragments.ActivityFragment
import dagger.BindsInstance
import dagger.Component

@Component(modules = [ActivityModule::class, NetworkModule::class, SharedPreferencesModule::class])
interface ActivityComponent: BaseFragmentComponent<ActivityFragment> {
    @Component.Builder
    interface Builder {
        fun build(): ActivityComponent
        @BindsInstance fun fragment(activityFragment: ActivityFragment): Builder
        fun activityModule(activityModule: ActivityModule): Builder
        fun networkModule(networkModule: NetworkModule): Builder
        fun sharedPreferencesModule(sharedPreferencesModule: SharedPreferencesModule): Builder
    }
}