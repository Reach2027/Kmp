package com.reach.kmp.feature.bingwallpaper

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reach.kmp.data.base.common.Result
import com.reach.kmp.feature.data.bingwallpaper.BingWallpaperRepo
import com.reach.kmp.feature.data.bingwallpaper.model.BingWallpaperModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class BingWallpaperViewModel(
    private val repo: BingWallpaperRepo
) : ViewModel() {

    private val _uiState: MutableStateFlow<Result<BingWallpaperModel>> =
        MutableStateFlow(Result.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repo.getTodayWallpaper().collect {
                _uiState.emit(it)
            }
        }
    }

}