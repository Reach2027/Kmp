package com.reach.kmp.feature.data.bingwallpaper

import com.reach.kmp.data.base.common.Result
import com.reach.kmp.data.base.common.flowResult
import com.reach.kmp.feature.data.bingwallpaper.model.BingWallpaperModel
import com.reach.kmp.feature.data.bingwallpaper.source.BingWallpaperApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

interface BingWallpaperRepo {

    fun getTodayWallpaper(): Flow<Result<BingWallpaperModel>>

}

internal class DefaultBingWallpaperRepo(
    private val bingWallpaperApi: BingWallpaperApi,
    private val dispatcher: CoroutineDispatcher,
) : BingWallpaperRepo {

    override fun getTodayWallpaper(): Flow<Result<BingWallpaperModel>> =
        flowResult(dispatcher) {
            bingWallpaperApi.getBingWallpapers(0, 1)
                .images[0]
        }

}