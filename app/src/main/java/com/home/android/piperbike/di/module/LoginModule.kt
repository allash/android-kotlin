package com.home.android.piperbike.di.module

import androidx.lifecycle.ViewModelProviders
import com.home.android.piperbike.data.auth.AuthRepository
import com.home.android.piperbike.ui.login.LoginViewModel
import com.home.android.piperbike.ui.login.LoginViewModelFactory
import com.home.android.piperbike.views.activities.LoginActivity
import dagger.Module
import dagger.Provides

@Module
class LoginModule(private val activity: LoginActivity) {
    @Provides
    fun provideLoginViewModel(
        factory: LoginViewModelFactory
    ): LoginViewModel {
        return ViewModelProviders.of(activity, factory).get(LoginViewModel::class.java)
    }

    @Provides
    fun provideLoginViewModelFactory(authRepository: AuthRepository): LoginViewModelFactory {
        return LoginViewModelFactory(authRepository)
    }
}