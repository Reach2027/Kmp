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

package com.reach.kmp.feature.bingwallpaper

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.reach.kmp.feature.data.bingwallpaper.BingWallpaperRepo
import com.reach.kmp.feature.data.bingwallpaper.model.BingWallpaperModel
import kotlinx.coroutines.flow.Flow
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
internal class BingWallpaperVM(
    repo: BingWallpaperRepo,
) : ViewModel() {
    val wallpapers: Flow<PagingData<BingWallpaperModel>> =
        repo
            .getWallpapers()
            .cachedIn(viewModelScope)
}
