package com.home.android.piperbike.util

import com.home.android.piperbike.data.Result
import java.io.IOException

suspend fun <T: Any> safeApiCall(call: suspend () -> Result<T>, errorMessage: String): Result<T> {
    return try {
        call()
    } catch (e: Exception) {
        Result.Error(IOException(errorMessage, e))
    }
}