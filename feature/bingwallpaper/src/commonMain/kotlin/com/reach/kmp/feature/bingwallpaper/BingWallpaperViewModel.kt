package com.reach.kmp.feature.bingwallpaper

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reach.kmp.data.base.common.Result
import com.reach.kmp.feature.data.bingwallpaper.BingWallpaperRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class BingWallpaperViewModel(
    private val repo: BingWallpaperRepo
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
                _uiState.emit(state)
            }
        }
    }

    fun loadNextPage() {
        val currentState = _uiState.value
        if (currentState !is UiState.Items) {
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
                        itemsState = ItemState.LoadedAll
                    )
                }
                _uiState.emit(state)
            }
        }
    }
}