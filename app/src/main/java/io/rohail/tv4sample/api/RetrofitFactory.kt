package io.rohail.tv4sample.api

import android.content.Context
import io.rohail.tv4sample.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitFactory {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private fun buildOkHttpClient(
        context: Context
    ): OkHttpClient {
        val builder = OkHttpClient().newBuilder()
            .addInterceptor(NetworkConnectionInterceptor(context))
            .addInterceptor(NonSuccessfulResponseInterceptor())
            .callTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)

        //Log auth key and request headers/body if debug
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(loggingInterceptor)
        }

        return builder.build()
    }

    fun retrofit(
        baseUrl: String,
        context: Context,
    ): Retrofit {

        retrofit = Retrofit.Builder()
            .client(buildOkHttpClient(context))
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit as Retrofit
    }

    var retrofit: Retrofit? = null
}