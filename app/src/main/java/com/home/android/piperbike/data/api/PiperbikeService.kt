package com.home.android.piperbike.data.api

import com.home.android.piperbike.data.activities.model.DtoGetActivityResponse
import com.home.android.piperbike.data.auth.model.DtoLoginResponse
import com.home.android.piperbike.data.auth.model.request.DtoLoginRequest
import com.home.android.piperbike.data.user.model.DtoGetUserProfileResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.UUID

interface PiperbikeService {

    // Activities

    @GET("activities")
    suspend fun getActivities(): Response<List<DtoGetActivityResponse>>

    @GET("activities/{id}")
    suspend fun getActivityById(@Path("id") id: UUID): Response<DtoGetActivityResponse?>

    // User

    @GET("profile")
    suspend fun getProfile(): Response<DtoGetUserProfileResponse>

    // Auth

    @POST("auth/login")
    suspend fun login(@Body body: DtoLoginRequest): Response<DtoLoginResponse>
}