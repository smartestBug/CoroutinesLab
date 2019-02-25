package dev.msemyak.coroutineslab.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dev.msemyak.coroutineslab.data.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

const val SERVER_ENDPOINT = "https://restcountries.eu/rest/v2/"
const val HTTP_LOGGING_TIMBER_TAG = "OkHttp"

fun provideNetworkService(retrofit: Retrofit): ApiService {
    return provideRetrofit(provideOkHttpClient())
        .create(ApiService::class.java)
}

fun provideOkHttpClient(): OkHttpClient {
    val httpClient = OkHttpClient.Builder()

    //timeout for network operations
    httpClient.readTimeout(30, TimeUnit.SECONDS)
    httpClient.connectTimeout(30, TimeUnit.SECONDS)

    // Logging Interceptor
    val loggingInterceptor =
        HttpLoggingInterceptor { message -> Timber.tag(HTTP_LOGGING_TIMBER_TAG).d(message) }
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    httpClient.addInterceptor(loggingInterceptor)

    return httpClient.build()
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(SERVER_ENDPOINT)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
}
