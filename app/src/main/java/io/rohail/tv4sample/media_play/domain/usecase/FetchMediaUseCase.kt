package io.rohail.tv4sample.media_play.domain.usecase

import io.rohail.tv4sample.media_play.data.MediaRepository
import javax.inject.Inject

interface FetchMediaUseCase {
    suspend operator fun invoke()
}

class FetchMediaUseCaseImpl @Inject constructor(private val mediaRepository: MediaRepository) :
    FetchMediaUseCase {

    override suspend fun invoke() {
        mediaRepository.fetchMedia(client = "android-code-test")
    }

}