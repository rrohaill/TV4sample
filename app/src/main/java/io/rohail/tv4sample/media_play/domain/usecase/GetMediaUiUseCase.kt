package io.rohail.tv4sample.media_play.domain.usecase

import io.rohail.tv4sample.media_play.data.MediaRepository
import io.rohail.tv4sample.media_play.model.MediaResult
import io.rohail.tv4sample.media_play.model.MediaUiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface GetMediaUiUseCase {
    operator fun invoke(): Flow<MediaUiResult>
}

class GetMediaUiUseCaseImpl @Inject constructor(private val mediaRepository: MediaRepository) :
    GetMediaUiUseCase {
    override fun invoke(): Flow<MediaUiResult> =
        mediaRepository.getMediaResult().map { result ->
            when (result) {
                is MediaResult.Success -> MediaUiResult.Success(result.data.toUI())
                is MediaResult.Error -> MediaUiResult.Error(errorMessage = result.error.description)
            }
        }

}