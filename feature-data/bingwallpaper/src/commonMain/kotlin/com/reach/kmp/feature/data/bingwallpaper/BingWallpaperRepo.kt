/*
 * Copyright 2024 Reach Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
