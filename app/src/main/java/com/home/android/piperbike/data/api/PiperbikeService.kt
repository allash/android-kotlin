package com.home.android.piperbike.data.api

import com.home.android.piperbike.data.model.DtoGetActivityResponse
import retrofit2.Response
import retrofit2.http.GET

interface PiperbikeService {
    @GET("activities")
    suspend fun getActivities(): Response<List<DtoGetActivityResponse>>
}