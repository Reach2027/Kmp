package com.reach.kmp.feature.bingwallpaper

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.reach.kmp.data.base.common.Result
import com.reach.kmp.feature.data.bingwallpaper.model.BingWallpaperModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

const val ROUTE_BING_WALLPAPER = "ROUTE_BING_WALLPAPER"

fun NavGraphBuilder.bingWallpaperRoute() {
    composable(ROUTE_BING_WALLPAPER) {
        BingWallpaperRoute()
    }
}

@Composable
private fun BingWallpaperRoute(
    viewModel: BingWallpaperViewModel = koinViewModel()
) {
    val todayWallpaper by viewModel.uiState.collectAsState(Result.Loading)

    BingWallpaperScreen(todayWallpaper)
}

@Composable
private fun BingWallpaperScreen(
    todayWallpaperRepo: Result<BingWallpaperModel>
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(todayWallpaperRepo.toString())
    }
}