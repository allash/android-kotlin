package com.home.android.piperbike.data

import com.home.android.piperbike.util.LocalStorage
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        /*
        * By now token is always null, as login functionality isn't implemented yet
        * Replace with hardcoded token from db or get from POST: /api/auth/login (email: john.doe@mail.com, password: asdf)
        * */
        val token = LocalStorage.token
        if (token == null || token.isEmpty()) {
            return chain.proceed(request)
        }

        val authRequest = request.newBuilder()
            .header(LocalStorage.X_AUTH_TOKEN, token)
            .build()

        return chain.proceed(authRequest)
    }
}