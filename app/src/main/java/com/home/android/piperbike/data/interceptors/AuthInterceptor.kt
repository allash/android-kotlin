package com.home.android.piperbike.data.interceptors

import com.home.android.piperbike.data.auth.AuthTokenLocalDataSource
import com.home.android.piperbike.data.auth.AuthTokenLocalDataSource.Companion.X_AUTH_TOKEN
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val authTokenLocalDataSource: AuthTokenLocalDataSource
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val token = authTokenLocalDataSource.authToken
        if (token == null || token.isEmpty()) {
            return chain.proceed(request)
        }

        val authRequest = request.newBuilder()
            .header(X_AUTH_TOKEN, token)
            .build()

        return chain.proceed(authRequest)
    }
}