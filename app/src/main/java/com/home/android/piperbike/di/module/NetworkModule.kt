package com.home.android.piperbike.di.module

import android.content.SharedPreferences
import com.home.android.piperbike.data.activities.ActivityRepository
import com.home.android.piperbike.data.api.PiperbikeService
import com.home.android.piperbike.data.auth.AuthRepository
import com.home.android.piperbike.data.auth.AuthTokenLocalDataSource
import com.home.android.piperbike.data.interceptors.AuthInterceptor
import com.home.android.piperbike.data.user.UserRepository
import dagger.Lazy
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@Suppress("unused")
class NetworkModule {

    companion object {
        private const val BASE_URL = "http://10.0.2.2:4000/api/"
    }

    @Provides
    internal fun provideApiService(retrofit: Retrofit): PiperbikeService {
        return retrofit.create(PiperbikeService::class.java)
    }

    @Provides
    internal fun provideActivityRepository(piperbikeService: PiperbikeService): ActivityRepository {
        return ActivityRepository.getInstance(piperbikeService)
    }

    @Provides
    internal fun provideAuthRepository(
        piperbikeService: PiperbikeService,
        authTokenLocalDataSource: AuthTokenLocalDataSource
    ): AuthRepository {
        return AuthRepository.getInstance(piperbikeService, authTokenLocalDataSource)
    }

    @Provides
    internal fun provideUserRepository(piperbikeService: PiperbikeService): UserRepository {
        return UserRepository.getInstance(piperbikeService)
    }

    @Provides
    internal fun provideAuthTokenLocalDataSource(sharedPreferences: SharedPreferences): AuthTokenLocalDataSource {
        return AuthTokenLocalDataSource.getInstance(sharedPreferences)
    }

    @Provides
    internal fun provideOkHttpClient(
        authTokenLocalDataSource: AuthTokenLocalDataSource
    ): OkHttpClient {
        val httpInterceptor = HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(httpInterceptor)
            .addInterceptor(AuthInterceptor(authTokenLocalDataSource))
            .build()
    }

    @Provides
    internal fun provideRetrofit(client: Lazy<OkHttpClient>): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .callFactory { client.get().newCall(it) }
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}