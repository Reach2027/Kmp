package com.reach.kmp.feature.data.bingwallpaper.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BingWallpapersModel(
    val images: List<BingWallpaperModel>,
)

@Serializable
data class BingWallpaperModel(
    @SerialName("url")
    var imageUrl: String,
    val copyright: String,
    val title: String,
    @SerialName("startdate")
    val startDate: String,
)
