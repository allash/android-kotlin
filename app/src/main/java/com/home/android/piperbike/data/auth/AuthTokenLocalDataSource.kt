package com.home.android.piperbike.data.auth

import android.content.SharedPreferences
import androidx.core.content.edit

class AuthTokenLocalDataSource (
    private val prefs: SharedPreferences
) {
    private var _authToken: String? = prefs.getString(X_AUTH_TOKEN, null)

    var authToken: String? = _authToken
        set(value) {
            prefs.edit { putString(X_AUTH_TOKEN, value) }
            field = value
        }

    fun clearData() {
        prefs.edit { X_AUTH_TOKEN to null }
        authToken = null
    }

    companion object {
        const val X_AUTH_TOKEN = "x-auth-token"

        @Volatile
        private var INSTANCE: AuthTokenLocalDataSource? = null

        fun getInstance(
            sharedPreferences: SharedPreferences
        ): AuthTokenLocalDataSource {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: AuthTokenLocalDataSource(sharedPreferences).also {
                    INSTANCE = it
                }
            }
        }
    }
}