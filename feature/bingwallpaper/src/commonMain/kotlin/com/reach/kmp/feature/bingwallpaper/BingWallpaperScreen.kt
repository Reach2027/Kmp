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

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import coil3.compose.AsyncImage
import com.reach.kmp.feature.data.bingwallpaper.model.BingWallpaperModel
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinNavViewModel

@Serializable
object RouteBingWallpaper

fun NavGraphBuilder.bingWallpaperRoute() {
    composable<RouteBingWallpaper> {
        BingWallpaperRoute()
    }
}

@Composable
private fun BingWallpaperRoute(
    viewModel: BingWallpaperViewModel = koinNavViewModel(),
) {
    val uiState: UiState by viewModel.uiState.collectAsStateWithLifecycle()

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
    Crossfade(
        targetState = uiState,
        modifier = Modifier.fillMaxSize(),
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
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun Error(
    uiState: UiState.Error,
    onRetryClick: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column {
            Text(uiState.message)
            Button(onClick = onRetryClick) {
                Text("Retry")
            }
        }
    }
}

@Composable
private fun Items(uiState: UiState.Items) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 390.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        item(
            key = "Top",
            span = { GridItemSpan(maxLineSpan) },
            contentType = "Spacer",
        ) {
            Spacer(Modifier.height(1.dp))
        }

        items(
            count = uiState.items.size,
            key = { index -> uiState.items[index].imageUrl },
            contentType = { "Items" },
        ) { index ->
            Item(model = uiState.items[index])
        }

        item(
            key = "Bottom",
            span = { GridItemSpan(maxLineSpan) },
            contentType = "Spacer",
        ) {
            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
private fun LazyGridItemScope.Item(model: BingWallpaperModel) {
    AsyncImage(
        model = model.imageUrl,
        contentDescription = "",
        modifier = Modifier.fillMaxWidth()
            .wrapContentHeight()
            .clip(MaterialTheme.shapes.large)
            .animateItem(),
    )
}
