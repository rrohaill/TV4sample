package io.rohail.tv4sample.media_play.data

import io.rohail.tv4sample.api.BaseDataSource
import io.rohail.tv4sample.api.Result2
import io.rohail.tv4sample.api.TvPlayApi
import io.rohail.tv4sample.media_play.model.Media
import javax.inject.Inject

interface MediaDataSource {
    suspend fun fetchMedia(client: String): Result2<Media>
}

class MediaDataSourceImpl @Inject constructor(private val service: TvPlayApi) : BaseDataSource(),
    MediaDataSource {
    override suspend fun fetchMedia(client: String): Result2<Media> = getResult {
        service.getPlaces(client = client)
    }

}