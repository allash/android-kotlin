package com.home.android.piperbike.data.auth

class AuthTokenLocalDataSource (
    // private val prefs: SharedPreferences
) {
    private var _authToken: String? = "4f02712325d843d98ad536623b91e47a"// prefs.getString(X_AUTH_TOKEN, null)

    var authToken: String? = _authToken
        set(value) {
            // prefs.edit { putString(X_AUTH_TOKEN, value) }
            field = value
        }

    fun clearData() {
        // prefs.edit { X_AUTH_TOKEN to null }
        authToken = null
    }

    companion object {
        private const val X_AUTH_TOKEN = "X_AUTH_TOKEN"

        @Volatile
        private var INSTANCE: AuthTokenLocalDataSource? = null

        fun getInstance(
            // sharedPreferences: SharedPreferences
        ): AuthTokenLocalDataSource {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: AuthTokenLocalDataSource().also {
                    INSTANCE = it
                }
            }
        }
    }
}