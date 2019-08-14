package com.home.android.piperbike.ui

import android.arch.lifecycle.ViewModel
import com.home.android.piperbike.di.DaggerViewModelInjector
import com.home.android.piperbike.di.ViewModelInjector
import com.home.android.piperbike.di.module.NetworkModule
import com.home.android.piperbike.ui.activity.ActivityListViewModel

abstract class BaseViewModel : ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
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