package io.rohail.tv4sample.media_play.model


import androidx.annotation.Keep

@Keep
data class Data(
    val allow_embed: Boolean,
    val brand_id: String,
    val broadcast_date_time: String,
    val category_title_nids: List<String>,
    val content_api_id: String,
    val content_api_season_id: String,
    val content_api_series_id: String,
    val description: String,
    val duration: Int,
    val encoder_group_id: Int,
    val episode: Int,
    val expire_date_time: String,
    val expire_date_time_webtve: String,
    val hide_ads: Boolean,
    val id: String,
    val image: String,
    val is_clip: Boolean,
    val is_drm_protected: Boolean,
    val is_geo_restricted: Boolean,
    val is_live: Boolean,
    val keywords: List<String>,
    val louise_product_key: String,
    val program_nid: String,
    val publish_date_time_tv4: String,
    val publish_date_time_webtve: String,
    val published_date_time: String,
    val region_availibility: String,
    val season: Int,
    val show_logo: Boolean,
    val start_over: Boolean,
    val tags: List<String>,
    val title: String,
    val type: String
)