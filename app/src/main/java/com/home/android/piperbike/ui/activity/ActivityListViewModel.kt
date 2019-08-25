package com.home.android.piperbike.ui.activity

import androidx.lifecycle.ViewModel
import com.home.android.piperbike.data.Result
import com.home.android.piperbike.data.activities.ActivityRepository
import com.home.android.piperbike.views.adapters.ActivityListAdapter
import kotlinx.coroutines.*
import javax.inject.Inject

class ActivityListViewModel @Inject constructor(
    private val activityRepository: ActivityRepository
) : ViewModel() {

    var activityListAdapter: ActivityListAdapter = ActivityListAdapter()

    private lateinit var activityListJob: Job

    init {
        loadActivities()
    }

    private fun loadActivities() {
        activityListJob = CoroutineScope(Dispatchers.IO).launch {

            val result = activityRepository.getActivities()

            withContext(Dispatchers.Main) {
                if (result is Result.Success) {
                    activityListAdapter.updateActivityList(result.data)
                } else {
                    // TODO: display error message to UI
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        activityListJob.cancel()
    }
}