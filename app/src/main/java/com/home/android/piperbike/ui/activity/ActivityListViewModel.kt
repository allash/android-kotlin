package com.home.android.piperbike.ui.activity

import com.home.android.piperbike.data.Result
import com.home.android.piperbike.data.activities.ActivityRepository
import com.home.android.piperbike.ui.BaseViewModel
import com.home.android.piperbike.views.adapters.ActivityListAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ActivityListViewModel : BaseViewModel() {

    @Inject
    lateinit var activityRepository: ActivityRepository

    var activityListAdapter: ActivityListAdapter = ActivityListAdapter()

    lateinit var activityListJob: Job

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