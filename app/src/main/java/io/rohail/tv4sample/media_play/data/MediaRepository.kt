package io.rohail.tv4sample.media_play.data

import io.rohail.tv4sample.api.Result2
import io.rohail.tv4sample.media_play.model.MediaResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

interface MediaRepository {

    suspend fun fetchMedia(client: String)

    fun getMediaResult(): Flow<MediaResult>
}

class MediaRepositoryImpl @Inject constructor(private val mediaDataSource: MediaDataSource) :
    MediaRepository {

    private val mediaResult = MutableSharedFlow<MediaResult>()

    override suspend fun fetchMedia(client: String) {
        mediaDataSource.fetchMedia(client = client).let { response ->
            when (response) {
                is Result2.Success -> mediaResult.emit(MediaResult.Success(data = response.data))
                is Result2.Error -> mediaResult.emit(MediaResult.Error(error = response.error))
            }
        }
    }

    override fun getMediaResult(): Flow<MediaResult> = mediaResult

}