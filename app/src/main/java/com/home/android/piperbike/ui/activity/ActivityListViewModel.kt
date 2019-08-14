package com.home.android.piperbike.ui.activity

import com.home.android.piperbike.data.Result
import com.home.android.piperbike.data.api.PiperbikeService
import com.home.android.piperbike.data.model.DtoGetActivityResponse
import com.home.android.piperbike.ui.BaseViewModel
import com.home.android.piperbike.util.safeApiCall
import com.home.android.piperbike.views.adapters.ActivityListAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class ActivityListViewModel : BaseViewModel() {

    @Inject
    lateinit var piperbikeService: PiperbikeService

    var activityListAdapter: ActivityListAdapter = ActivityListAdapter()

    lateinit var activityListJob: Job

    init {
        loadActivities()
    }

    private fun loadActivities() {
        activityListJob = CoroutineScope(Dispatchers.IO).launch {

            val result = safeApiCall(
                call = { loadActivitiesWrapper() },
                errorMessage = "Error loading activities"
            )

            withContext(Dispatchers.Main) {
                if (result is Result.Success) {
                    activityListAdapter.updateActivityList(result.data)
                } else {
                    // TODO: display error message to UI
                }
            }
        }
    }

    private suspend fun loadActivitiesWrapper(): Result<List<DtoGetActivityResponse>> {
        val response = piperbikeService.getActivities()
        if (response.isSuccessful) {
            val activities = response.body()
            if (activities != null && activities.isNotEmpty()) {
                return Result.Success(activities)
            }
        }

        return Result.Error(IOException("Failed to get activities: ${response.code()} ${response.message()}"))
    }

    override fun onCleared() {
        super.onCleared()
        activityListJob.cancel()
    }
}