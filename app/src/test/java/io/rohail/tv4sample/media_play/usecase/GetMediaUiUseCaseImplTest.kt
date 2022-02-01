package io.rohail.tv4sample.media_play.usecase

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.rohail.tv4sample.api.ApiError
import io.rohail.tv4sample.media_play.data.MediaRepository
import io.rohail.tv4sample.media_play.domain.usecase.GetMediaUiUseCaseImpl
import io.rohail.tv4sample.media_play.model.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetMediaUiUseCaseImplTest {

    private val successResult = MediaResult.Success(
        data = Media(
            emptyList(),
            Meta(AssetTypeCounts(-1, -1, -1, -1), "", -1)
        )
    )
    private val errorResult = MediaResult.Error(ApiError("", "", ""))

    private val successUiResult = MediaUiResult.Success(emptyList())
    private val errorUiResult = MediaUiResult.Error("")

    @Test
    fun `get media ui result success`() = runBlocking {
        val uc = GetMediaUiUseCaseImpl(mediaRepository = getMediaRepoMock(true))

        assertEquals(successUiResult, uc().first())
    }

    @Test
    fun `get media ui result error`() = runBlocking {
        val uc = GetMediaUiUseCaseImpl(mediaRepository = getMediaRepoMock(false))

        assertEquals(errorUiResult, uc().first())
    }

    private fun getMediaRepoMock(hasValue: Boolean): MediaRepository = mock {
        onBlocking {
            getMediaResult()
        } doReturn if (hasValue) flow { emit(successResult) } else flow { emit(errorResult) }
    }
}