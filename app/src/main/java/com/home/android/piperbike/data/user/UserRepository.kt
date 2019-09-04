package com.home.android.piperbike.data.user

import com.home.android.piperbike.data.Result
import com.home.android.piperbike.data.api.PiperbikeService
import com.home.android.piperbike.data.user.model.DtoGetUserProfileResponse
import com.home.android.piperbike.util.safeApiCall
import java.io.IOException

class UserRepository (
    private val piperbikeService: PiperbikeService
) {
    suspend fun getProfile(): Result<DtoGetUserProfileResponse> = safeApiCall(
        call = { requestGetProfile() },
        errorMessage = "Error: get profile"
    )

    private suspend fun requestGetProfile(): Result<DtoGetUserProfileResponse> {
        val response = piperbikeService.getProfile()
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                return Result.Success(body)
            }
        }

        return Result.Error(
            IOException(
                "Failed to get profile, response: ${response.code()} ${response.message()}")
        )
    }

    companion object {
        @Volatile
        private var INSTANCE: UserRepository? = null

        fun getInstance(piperbikeService: PiperbikeService): UserRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: UserRepository(piperbikeService)
                    .also { INSTANCE = it }
            }
        }
    }
}