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
import com.reach.kmp.data.core.common.RTAG
import com.reach.kmp.feature.data.bingwallpaper.BingWallpaperRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

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
            _uiState.emit(UiState.Loading)
            repo.getWallpapers(0).collect { res ->
                val state = res.fold(
                    onSuccess = {
                        UiState.Items(
                            items = it,
                            itemsState = ItemState.Normal,
                        )
                    },
                    onFailure = { UiState.Error(it.toString()) },
                )
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
            _uiState.emit(currentState.copy(itemsState = ItemState.LoadingMore))
            repo.getWallpapers(1).collect { res ->
                val state = res.fold(
                    onSuccess = { data ->
                        currentState.copy(
                            items = buildList {
                                addAll(currentState.items)
                                addAll(data)
                            },
                            itemsState = ItemState.LoadedAll,
                        )
                    },
                    onFailure = { currentState.copy(itemsState = ItemState.LoadMoreError(it.toString())) },
                )
                _uiState.emit(state)
                Logger.e(RTAG) { "loadNextPage: ${state.itemsState}" }
            }
        }
    }
}
