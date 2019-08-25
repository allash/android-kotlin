package com.home.android.piperbike.di

import com.home.android.piperbike.di.components.DaggerHomeComponent
import com.home.android.piperbike.di.module.NetworkModule
import com.home.android.piperbike.views.activities.MainActivity

fun inject(activity: MainActivity) {
    DaggerHomeComponent.builder()
        .networkModule(NetworkModule())
        // .sharedPreferencesModule(SharedPreferencesModule(activity, "shared_prefs"))
        .mainActivity(activity)
        .build()
        .inject(activity)
}