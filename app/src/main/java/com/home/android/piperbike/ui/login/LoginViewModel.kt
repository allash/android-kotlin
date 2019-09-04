package com.home.android.piperbike.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.home.android.piperbike.data.Result
import com.home.android.piperbike.data.auth.AuthRepository
import kotlinx.coroutines.*
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private var authJob: Job? = null

    private val _uiState = MutableLiveData<LoginUiModel>()
    val uiState: LiveData<LoginUiModel>
        get() = _uiState

    fun login(email: String, password: String) {
        if (authJob?.isActive == true) {
            return
        }

        authJob = launchLogin(email, password)
    }

    private fun launchLogin(email: String, password: String): Job {
        return CoroutineScope(Dispatchers.IO).launch {
            val result = authRepository.login(email, password)

            withContext(Dispatchers.Main) {
                if (result is Result.Success) {
                    emitUiState(success = true)
                } else {
                    // TODO: pass all kinds of errors, e.g. NetworkFailure, BackendNotAvailable, etc
                    emitUiState(success = false, error = "Invalid credentials")
                }
            }
        }
    }

    private fun emitUiState(success: Boolean, error: String? = null) {
        val uiModel = LoginUiModel(success = success, error = error)
        _uiState.value = uiModel
    }

    override fun onCleared() {
        super.onCleared()
        authJob?.cancel()
    }

    data class LoginUiModel (
        val success: Boolean,
        val error: String? = null
    )
}