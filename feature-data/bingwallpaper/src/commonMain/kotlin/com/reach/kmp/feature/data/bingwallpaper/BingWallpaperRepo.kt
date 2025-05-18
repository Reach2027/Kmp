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

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.reach.kmp.data.base.common.DispatcherNamed
import com.reach.kmp.data.base.common.flow.flowResult
import com.reach.kmp.feature.data.bingwallpaper.datasource.BingWallpaperApi
import com.reach.kmp.feature.data.bingwallpaper.datasource.BingWallpaperPagingSource
import com.reach.kmp.feature.data.bingwallpaper.model.BingWallpaperModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory
import org.koin.core.annotation.Named

interface BingWallpaperRepo {
    fun getTodayWallpaper(): Flow<Result<BingWallpaperModel>>

    fun getWallpapers(): Flow<PagingData<BingWallpaperModel>>
}

@Factory(binds = [BingWallpaperRepo::class])
internal class DefaultBingWallpaperRepo(
    private val api: BingWallpaperApi,
    private val pagingSource: BingWallpaperPagingSource,
    @Named(DispatcherNamed.IO) private val dispatcher: CoroutineDispatcher,
) : BingWallpaperRepo {
    override fun getTodayWallpaper(): Flow<Result<BingWallpaperModel>> =
        flowResult(dispatcher) {
            api
                .getBingWallpapers(0, 1)
                .images[0]
        }

    override fun getWallpapers(): Flow<PagingData<BingWallpaperModel>> =
        Pager(config = PagingConfig(pageSize = 7)) {
            pagingSource
        }.flow
}
