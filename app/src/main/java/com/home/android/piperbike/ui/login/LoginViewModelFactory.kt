package com.home.android.piperbike.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.home.android.piperbike.data.auth.AuthRepository
import javax.inject.Inject

class LoginViewModelFactory @Inject constructor(
    private val authRepository: AuthRepository
): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass != LoginViewModel::class.java) {
            throw IllegalArgumentException("Unknown view model")
        }

        return LoginViewModel(authRepository) as T
    }
}