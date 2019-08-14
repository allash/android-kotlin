package com.home.android.piperbike.di.module

import com.home.android.piperbike.data.api.PiperbikeService
import com.home.android.piperbike.data.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@Suppress("unused")
object NetworkModule {

    private const val BASE_URL = "http://10.0.2.2:4000/api/"

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideApiService(retrofit: Retrofit): PiperbikeService {
        return retrofit.create(PiperbikeService::class.java)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofit(): Retrofit {
        val httpInterceptor = HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(httpInterceptor)
            .addInterceptor(AuthInterceptor())
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}