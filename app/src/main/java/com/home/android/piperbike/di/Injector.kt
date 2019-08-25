package com.home.android.piperbike.di

import com.home.android.piperbike.di.Config.PIPERBIKE_SHARED_PREFERENCES
import com.home.android.piperbike.di.component.DaggerActivityComponent
import com.home.android.piperbike.di.component.DaggerLoginComponent
import com.home.android.piperbike.di.module.ActivityModule
import com.home.android.piperbike.di.module.LoginModule
import com.home.android.piperbike.di.module.SharedPreferencesModule
import com.home.android.piperbike.views.activities.LoginActivity
import com.home.android.piperbike.views.fragments.ActivityFragment

object Config {
    const val PIPERBIKE_SHARED_PREFERENCES = "PIPERBIKE_SHARED_PREFERENCES"
}

fun inject(fragment: ActivityFragment) {
    val context = fragment.context ?: throw IllegalArgumentException("Fragment context is null")

    DaggerActivityComponent.builder()
        .fragment(fragment)
        .activityModule(ActivityModule(fragment))
        .sharedPreferencesModule(SharedPreferencesModule(context, PIPERBIKE_SHARED_PREFERENCES))
        .build()
        .inject(fragment)
}

fun inject(activity: LoginActivity) {
    DaggerLoginComponent.builder()
        .activity(activity)
        .loginModule(LoginModule(activity))
        .sharedPreferencesModule(SharedPreferencesModule(activity, PIPERBIKE_SHARED_PREFERENCES))
        .build()
        .inject(activity)
}