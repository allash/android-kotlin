package com.home.android.piperbike.di.components

import android.app.Activity

interface BaseComponent<T> {
    fun inject(target: T)
}

interface BaseActivitiyComponent<T: Activity>: BaseComponent<T>