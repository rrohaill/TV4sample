package io.rohail.tv4sample.media_play.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.rohail.tv4sample.media_play.model.MediaUiResult
import io.rohail.tv4sample.media_play.domain.usecase.FetchMediaUseCase
import io.rohail.tv4sample.media_play.domain.usecase.GetMediaUiUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchMediaUseCase: FetchMediaUseCase,
    private val getMediaUiUseCase: GetMediaUiUseCase
) : ViewModel() {

    fun fetchMedia() {
        viewModelScope.launch {
            fetchMediaUseCase()
        }
    }

    fun getMedia():Flow<MediaUiResult> = getMediaUiUseCase()
}