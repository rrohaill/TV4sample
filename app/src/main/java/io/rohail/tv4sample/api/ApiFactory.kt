package io.rohail.tv4sample.api

import android.content.Context
import retrofit2.create

object ApiFactory {
    private lateinit var tvPlayApi: TvPlayApi

    private const val BASE_URL = "https://tv4-search.b17g.net/"

    fun getApi(context: Context): TvPlayApi {
        if (!ApiFactory::tvPlayApi.isInitialized) {
            tvPlayApi = RetrofitFactory.retrofit(
                baseUrl = BASE_URL,
                context = context
            ).create()
        }
        return tvPlayApi
    }
}