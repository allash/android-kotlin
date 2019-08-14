package com.home.android.piperbike.ui.activity

import android.arch.lifecycle.MutableLiveData
import com.home.android.piperbike.ui.BaseViewModel
import com.home.android.piperbike.data.model.DtoGetActivityResponse

class ActivityViewModel : BaseViewModel() {
    private val activityTitle = MutableLiveData<String>()

    fun bind(activity: DtoGetActivityResponse) {
        activityTitle.value = activity.name
    }

    fun getActivityTitle(): MutableLiveData<String> {
        return activityTitle
    }
}