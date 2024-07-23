package com.reach.kmp.feature.data.bingwallpaper

import com.reach.kmp.data.base.common.Result
import com.reach.kmp.data.base.common.flowResult
import com.reach.kmp.feature.data.bingwallpaper.model.BingWallpaperModel
import com.reach.kmp.feature.data.bingwallpaper.source.BingWallpaperApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

private const val MAX_COUNT = 7

private const val MAX_BEFORE_DAYS = 7

interface BingWallpaperRepo {

    fun getTodayWallpaper(): Flow<Result<BingWallpaperModel>>

    fun getWallpapers(page: Int): Flow<Result<List<BingWallpaperModel>>>
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

    override fun getWallpapers(page: Int): Flow<Result<List<BingWallpaperModel>>> =
        flowResult(dispatcher) {
            if (page < 0 || page > 1) {
                emptyList()
            } else {
                bingWallpaperApi.getBingWallpapers(page * MAX_BEFORE_DAYS, MAX_COUNT)
                    .images
            }
        }
}