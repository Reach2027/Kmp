package com.reach.kmp.feature.bingwallpaper

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
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
    val uiState: UiState by viewModel.uiState.collectAsState()

    BingWallpaperScreen(
        uiState,
        onRetryClick = { viewModel.loadFirstPage() },
    )
}

@Composable
private fun BingWallpaperScreen(
    uiState: UiState,
    onRetryClick: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (uiState) {
            UiState.Loading -> Loading()
            is UiState.Error -> Error(uiState, onRetryClick)
            is UiState.Items -> Items(uiState)
        }
    }
}

@Composable
private fun Loading() {
    CircularProgressIndicator()
}

@Composable
private fun Error(
    uiState: UiState.Error,
    onRetryClick: () -> Unit,
) {
    Column {
        Text(uiState.message)
        Button(onClick = onRetryClick) {
            Text("Retry")
        }
    }
}

@Composable
private fun BoxScope.Items(uiState: UiState.Items) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 390.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth()
            .align(Alignment.TopStart),
    ) {
        items(
            count = uiState.items.size,
            key = { index -> uiState.items[index].imageUrl }
        ) { index ->
            Text(uiState.items[index].toString())
        }
    }
}