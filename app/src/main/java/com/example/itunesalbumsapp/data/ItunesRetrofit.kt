package com.example.itunesalbumsapp.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import timber.log.Timber

class ItunesRetrofit {
    private fun buildOkHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .addNetworkInterceptor(
                HttpLoggingInterceptor { httpMessage ->
                    Timber.e(httpMessage)
                }
                    .setLevel(HttpLoggingInterceptor.Level.BODY)
            )
        return okHttpClientBuilder.build()
    }

    private var retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(buildOkHttpClient())
        .build()

    val itunesApi: ItunesApi
        get() = retrofit.create()

    companion object {
        private const val BASE_URL = "https://itunes.apple.com/"
    }
}