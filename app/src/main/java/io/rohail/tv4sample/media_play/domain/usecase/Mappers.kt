package io.rohail.tv4sample.media_play.domain.usecase

import io.rohail.tv4sample.media_play.model.Media
import io.rohail.tv4sample.media_play.model.MediaUI

fun Media.toUI():List<MediaUI> = this.data.map {
    MediaUI(
        title = it.title,
        imageUrl = it.image,
        description = it.description,
        isLive = it.is_live
    )
}