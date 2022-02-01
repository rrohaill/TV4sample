package io.rohail.tv4sample.media_play.model

import io.rohail.tv4sample.api.ApiError

sealed class MediaResult {
    data class Success(val data: Media) : MediaResult()
    data class Error(val error: ApiError) : MediaResult()
}
