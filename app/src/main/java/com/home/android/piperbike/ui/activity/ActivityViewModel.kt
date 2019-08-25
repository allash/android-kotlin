package com.home.android.piperbike.ui.activity

import androidx.lifecycle.MutableLiveData
import com.home.android.piperbike.data.activities.model.DtoGetActivityResponse
import com.home.android.piperbike.ui.BaseViewModel

class ActivityViewModel : BaseViewModel() {
    private val activityTitle = MutableLiveData<String>()

    fun bind(activity: DtoGetActivityResponse) {
        activityTitle.value = activity.name
    }

    fun getActivityTitle(): MutableLiveData<String> {
        return activityTitle
    }
}