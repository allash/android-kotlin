package com.home.android.piperbike.ui

import androidx.lifecycle.ViewModel
import com.home.android.piperbike.di.DaggerViewModelInjector
import com.home.android.piperbike.di.ViewModelInjector
import com.home.android.piperbike.di.module.NetworkModule
import com.home.android.piperbike.ui.activity.ActivityListViewModel

abstract class BaseViewModel : ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule())
        // .sharedPreferencesModule(SharedPreferencesModule(Activity(), "shared_random")) // todo: Remove this!
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is ActivityListViewModel -> injector.inject(this)
        }
    }
}