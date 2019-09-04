package com.home.android.piperbike.ui.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.home.android.piperbike.data.activities.model.DtoGetActivityResponse

class ActivityViewModel : ViewModel() {
    private val activityTitle = MutableLiveData<String>()

    fun bind(activity: DtoGetActivityResponse) {
        activityTitle.value = activity.name
    }

    fun getActivityTitle(): MutableLiveData<String> {
        return activityTitle
    }
}