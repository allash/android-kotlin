package com.home.android.piperbike.data.activities

import com.home.android.piperbike.data.Result
import com.home.android.piperbike.data.activities.model.DtoGetActivityResponse
import com.home.android.piperbike.data.api.PiperbikeService
import com.home.android.piperbike.util.safeApiCall
import java.io.IOException
import java.util.*

class ActivityRepository (
    private val piperbikeService: PiperbikeService
) {
    suspend fun getActivities(): Result<List<DtoGetActivityResponse>> = safeApiCall(
        call = { requestGetActivities() },
        errorMessage = "Error: get activities"
    )

    suspend fun getActivityById(id: UUID): Result<DtoGetActivityResponse> = safeApiCall(
        call = { requestGetActivityById(id) },
        errorMessage = "Error: get activity by id"
    )

    private suspend fun requestGetActivities(): Result<List<DtoGetActivityResponse>> {
        val response = piperbikeService.getActivities()
        if (response.isSuccessful) {
            val activities = response.body()
            if (activities != null && activities.isNotEmpty()) {
                return Result.Success(activities)
            }
        }

        return Result.Error(IOException(
            "Failed to get activities: ${response.code()} ${response.message()}"
        ))
    }

    private suspend fun requestGetActivityById(id: UUID): Result<DtoGetActivityResponse> {
        val response = piperbikeService.getActivityById(id)
        if (response.isSuccessful) {
            val activity = response.body()
            if (activity != null) {
                return Result.Success(activity)
            }
        }

        return Result.Error(IOException(
            "Failed to get activity by id: $id, response: ${response.code()} ${response.message()}"
        ))
    }

    companion object {
        @Volatile
        private var INSTANCE: ActivityRepository? = null

        fun getInstance(piperbikeService: PiperbikeService): ActivityRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: ActivityRepository(piperbikeService)
                    .also { INSTANCE = it }
            }
        }
    }
}