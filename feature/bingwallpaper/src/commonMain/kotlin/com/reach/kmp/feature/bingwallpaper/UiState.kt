package com.reach.kmp.feature.bingwallpaper

import com.reach.kmp.feature.data.bingwallpaper.model.BingWallpaperModel

internal sealed interface UiState {

    data object Loading : UiState

    data class Error(val message: String) : UiState

    data class Items(
        val items: List<BingWallpaperModel>,
        val itemsState: ItemState,
    ) : UiState
}

internal sealed interface ItemState {

    data object Normal : ItemState

    data object LoadingMore : ItemState

    data class LoadMoreError(val message: String) : ItemState

    data object LoadedAll : ItemState
}