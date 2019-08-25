package com.home.android.piperbike.ui.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.home.android.piperbike.data.activities.ActivityRepository
import javax.inject.Inject

class ActivityListViewModelFactory @Inject constructor(
    private val activityRepository: ActivityRepository
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass != ActivityListViewModel::class.java) {
            throw IllegalArgumentException("Unknown view model")
        }

        return ActivityListViewModel(activityRepository) as T
    }
}