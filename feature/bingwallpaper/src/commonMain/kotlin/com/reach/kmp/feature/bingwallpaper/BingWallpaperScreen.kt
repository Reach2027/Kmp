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

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBackIos
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import co.touchlab.kermit.Logger
import coil3.compose.AsyncImage
import com.reach.kmp.data.core.common.RTAG
import com.reach.kmp.feature.data.bingwallpaper.model.BingWallpaperModel
import com.reach.kmp.ui.base.common.toDp
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.materials.CupertinoMaterials
import dev.chrisbanes.haze.materials.ExperimentalHazeMaterialsApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
object RouteBingWallpaper

fun NavGraphBuilder.bingWallpaperRoute(navController: NavController) {
    composable<RouteBingWallpaper> {
        BingWallpaperRoute(navController)
    }
}

@Composable
private fun BingWallpaperRoute(
    navController: NavController,
    viewModel: BingWallpaperViewModel = koinViewModel(),
) {
    val uiState: UiState by viewModel.uiState.collectAsStateWithLifecycle()

    BingWallpaperScreen(
        uiState = uiState,
        onBackClick = { navController.navigateUp() },
        onRetryClick = { viewModel.loadFirstPage() },
        loadMore = { viewModel.loadNextPage() },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BingWallpaperScreen(
    uiState: UiState,
    onBackClick: () -> Unit,
    onRetryClick: () -> Unit,
    loadMore: () -> Unit,
) {
    val hazeState = remember { HazeState() }

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    TitleBarWithBack(
        onBackClick = onBackClick,
        hazeState = hazeState,
        scrollBehavior = scrollBehavior,
    ) {
        when (uiState) {
            UiState.Loading -> Loading()
            is UiState.Error -> Error(uiState, onRetryClick)
            is UiState.Items -> Items(uiState, hazeState, scrollBehavior, loadMore)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalHazeMaterialsApi::class)
@Composable
private fun TitleBarWithBack(
    onBackClick: () -> Unit,
    hazeState: HazeState,
    scrollBehavior: TopAppBarScrollBehavior,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        content()

        TopAppBar(
            title = {},
            modifier = Modifier
                .hazeEffect(state = hazeState, style = CupertinoMaterials.thin())
                .fillMaxWidth(),
            navigationIcon = {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBackIos,
                    contentDescription = "",
                    modifier = Modifier
                        .clickable { onBackClick() }
                        .padding(12.dp),
                )
            },
            colors = TopAppBarDefaults.topAppBarColors().copy(
                scrolledContainerColor = Color.Transparent,
            ),
            scrollBehavior = scrollBehavior,
        )
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Items(
    uiState: UiState.Items,
    hazeState: HazeState,
    scrollBehavior: TopAppBarScrollBehavior,
    loadMore: () -> Unit,
) {
    val gridState = rememberLazyGridState()

    LaunchedEffect(gridState) {
        delay(600L)
        snapshotFlow {
            val lastItem = gridState.layoutInfo.visibleItemsInfo.last()
            Logger.e(RTAG) { "lastIndex: ${lastItem.index}" }
            lastItem.index > 5
        }.map { checkLoad ->
            if (checkLoad) uiState.itemsState == ItemState.Normal else false
        }.distinctUntilChanged()
            .collect { needLoad ->
                Logger.e(RTAG) { "needLoad: $needLoad" }
                if (needLoad) loadMore()
            }
    }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 390.dp),
        state = gridState,
        contentPadding = PaddingValues(all = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .hazeSource(state = hazeState),
    ) {
        item(
            key = "PaddingTop",
            span = { GridItemSpan(maxLineSpan) },
            contentType = "PaddingTop",
        ) {
            Spacer(
                Modifier.height(
                    WindowInsets
                        .statusBars
                        .getTop(LocalDensity.current)
                        .toDp() + TopAppBarDefaults.TopAppBarExpandedHeight - 32.dp,
                ),
            )
        }

        items(
            items = uiState.items,
            key = { item -> item.imageUrl },
            contentType = { "Items" },
        ) { item -> Item(model = item) }

        when (uiState.itemsState) {
            ItemState.LoadingMore -> item(
                key = "LoadingMore",
                span = { GridItemSpan(maxLineSpan) },
                contentType = "LoadingMore",
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .height(100.dp)
                        .animateItem(),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator()
                }
            }

            is ItemState.LoadMoreError -> item(
                key = "LoadMoreError",
                span = { GridItemSpan(maxLineSpan) },
                contentType = "LoadMoreError",
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                        .height(100.dp)
                        .animateItem(),
                ) {
                    Text(uiState.itemsState.message)
                    Button(onClick = loadMore) {
                        Text("Retry")
                    }
                }
            }

            ItemState.LoadedAll, ItemState.Normal -> {
                item(
                    key = "Bottom",
                    span = { GridItemSpan(maxLineSpan) },
                    contentType = "Spacer",
                ) {
                    Spacer(Modifier.height(16.dp).animateItem())
                }
            }
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
