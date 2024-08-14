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
import co.touchlab.kermit.Logger
import com.reach.kmp.data.base.common.Result
import com.reach.kmp.data.core.common.RTAG
import com.reach.kmp.feature.data.bingwallpaper.BingWallpaperRepo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val DELAY = 1000L

internal class BingWallpaperViewModel(
    private val repo: BingWallpaperRepo,
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        loadFirstPage()
    }

    fun loadFirstPage() {
        viewModelScope.launch {
            repo.getWallpapers(0).collect { res ->
                val state = when (res) {
                    Result.Loading -> UiState.Loading
                    is Result.Error -> UiState.Error(res.exception.toString())
                    is Result.Success -> UiState.Items(
                        items = res.data,
                        itemsState = ItemState.Normal,
                    )
                }
                if (res is Result.Success) delay(DELAY)
                _uiState.emit(state)
            }
        }
    }

    fun loadNextPage() {
        val currentState = _uiState.value
        if (currentState !is UiState.Items || currentState.itemsState == ItemState.LoadedAll) {
            return
        }
        viewModelScope.launch {
            repo.getWallpapers(1).collect { res ->
                val state = when (res) {
                    Result.Loading -> currentState.copy(itemsState = ItemState.LoadingMore)
                    is Result.Error ->
                        currentState.copy(itemsState = ItemState.LoadMoreError(res.exception.toString()))

                    is Result.Success -> currentState.copy(
                        items = buildList {
                            addAll(currentState.items)
                            addAll(res.data)
                        },
                        itemsState = ItemState.LoadedAll,
                    )
                }
                if (res is Result.Success) delay(DELAY)
                _uiState.emit(state)
                Logger.e(RTAG) { "loadNextPage: ${state.itemsState}" }
            }
        }
    }
}
