package io.rohail.tv4sample.media_play.model


import androidx.annotation.Keep

@Keep
data class Meta(
    val asset_type_counts: AssetTypeCounts,
    val timestamp: String,
    val total_hits: Int
)