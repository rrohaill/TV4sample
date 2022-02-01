package io.rohail.tv4sample.media_play.model

sealed class MediaUiResult {
    data class Success(val data: List<MediaUI>) : MediaUiResult()
    data class Error(val errorMessage: String) : MediaUiResult()
}
