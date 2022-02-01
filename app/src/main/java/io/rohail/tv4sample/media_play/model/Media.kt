package io.rohail.tv4sample.media_play.model


import androidx.annotation.Keep

@Keep
data class Media(
    val `data`: List<Data>,
    val meta: Meta
)