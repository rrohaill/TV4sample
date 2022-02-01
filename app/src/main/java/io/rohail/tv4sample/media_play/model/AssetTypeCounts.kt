package io.rohail.tv4sample.media_play.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class AssetTypeCounts(
    val clip: Int,
    val program: Int,
    val trailer: Int,
    @SerializedName("virtual-channel")
    val virtual_channel: Int
)