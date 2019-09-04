package com.home.android.piperbike.data.auth

import com.home.android.piperbike.data.Result
import com.home.android.piperbike.data.api.PiperbikeService
import com.home.android.piperbike.data.auth.model.DtoLoginResponse
import com.home.android.piperbike.data.auth.model.request.DtoLoginRequest
import com.home.android.piperbike.util.safeApiCall
import java.io.IOException

class AuthRepository (
    private val piperbikeService: PiperbikeService,
    private val authTokenLocalDataSource: AuthTokenLocalDataSource
) {

    suspend fun login(email: String, password: String): Result<DtoLoginResponse> = safeApiCall(
            call = { requestLogin(email, password) },
            errorMessage = "Error: login"
        )

    private suspend fun requestLogin(email: String, password: String): Result<DtoLoginResponse> {
        val request = DtoLoginRequest(email = email, password = password)
        val response = piperbikeService.login(request)
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                authTokenLocalDataSource.authToken = body.token
                return Result.Success(body)
            }
        }

        return Result.Error(
            IOException(
                "Failed to login, response: ${response.code()} ${response.message()}")
        )
    }

    companion object {
        @Volatile
        private var INSTANCE: AuthRepository? = null

        fun getInstance(
            piperbikeService: PiperbikeService,
            authTokenLocalDataSource: AuthTokenLocalDataSource
        ): AuthRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: AuthRepository(piperbikeService, authTokenLocalDataSource)
                    .also { INSTANCE = it }
            }
        }
    }
}