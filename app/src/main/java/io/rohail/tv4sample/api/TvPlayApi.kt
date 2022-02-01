package io.rohail.tv4sample.api

import io.rohail.tv4sample.media_play.model.Media
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TvPlayApi {

    @GET("assets")
    suspend fun getPlaces(
        @Query("client") client: String,
    ): Response<Media>

}