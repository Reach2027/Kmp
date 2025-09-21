/*
 * Copyright 2025 Reach Project
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

package com.reach.kmp.feature.compose

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBackIos
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.window.core.layout.WindowSizeClass
import com.reach.kmp.ui.core.common.animation.enterScreenTransition
import com.reach.kmp.ui.core.common.animation.exitScreenTransition
import com.reach.kmp.ui.core.common.animation.popEnterScreenTransition
import com.reach.kmp.ui.core.common.animation.popExitScreenTransition
import kotlinx.serialization.Serializable

@Serializable
object RouteScreenSample

fun NavGraphBuilder.screenSampleRoute(navController: NavController) {
    composable<RouteScreenSample> {
        ScreenSampleRoute(navController)
    }
}

@Composable
private fun ScreenSampleRoute(
    externalNav: NavController,
    internalNav: NavHostController = rememberNavController(),
) {
    if (currentWindowAdaptiveInfo().windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND)) {
        TitleBarWithBack(onBackClick = { externalNav.navigateUp() }) {
            Row(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier =
                        Modifier
                            .fillMaxWidth(0.35f)
                            .fillMaxHeight()
                            .padding(4.dp)
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.primary,
                            ),
                ) {
                    MenuRoute(true, externalNav, internalNav)
                }
                Box(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(4.dp)
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.primary,
                            ),
                ) {
                    NavHost(
                        navController = internalNav,
                        startDestination = RouteA1,
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        composable<RouteA1> {
                            A1Route(false, internalNav)
                        }
                        composable<RouteA2> {
                            A2Route(false, internalNav)
                        }
                        composable<RouteA3> {
                            A3Route(false, internalNav)
                        }
                    }
                }
            }
        }
    } else {
        NavHost(
            navController = internalNav,
            startDestination = RouteMenu,
            modifier = Modifier.fillMaxSize(),
            enterTransition = { enterScreenTransition() },
            exitTransition = { exitScreenTransition() },
            popEnterTransition = { popEnterScreenTransition() },
            popExitTransition = { popExitScreenTransition() },
        ) {
            composable<RouteMenu> {
                MenuRoute(false, externalNav, internalNav)
            }
            composable<RouteA1> {
                A1Route(true, internalNav)
            }
            composable<RouteA2> {
                A2Route(true, internalNav)
            }
            composable<RouteA3> {
                A3Route(true, internalNav)
            }
        }
    }
}

@Serializable
internal object RouteMenu

@Composable
private fun MenuRoute(
    isExpanded: Boolean,
    externalNav: NavController,
    internalNav: NavController,
) {
    if (isExpanded) {
        MenuScreen(isExpanded, internalNav)
    } else {
        TitleBarWithBack(onBackClick = { externalNav.navigateUp() }) {
            MenuScreen(isExpanded, internalNav)
        }
    }
}

@Composable
private fun MenuScreen(
    isExpanded: Boolean,
    navController: NavController,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "A1",
            fontSize = 24.sp,
            modifier =
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        if (isExpanded) {
                            navController.popBackStack()
                        }
                        navController.navigate(RouteA1)
                    },
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "A2",
            fontSize = 24.sp,
            modifier =
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        if (isExpanded) {
                            navController.popBackStack()
                        }
                        navController.navigate(RouteA2)
                    },
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "A3",
            fontSize = 24.sp,
            modifier =
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        if (isExpanded) {
                            navController.popBackStack()
                        }
                        navController.navigate(RouteA3)
                    },
        )
    }
}

@Serializable
internal object RouteA1

@Composable
private fun A1Route(
    isFullscreen: Boolean,
    navController: NavController,
) {
    if (isFullscreen) {
        TitleBarWithBack(
            onBackClick = { navController.navigateUp() },
        ) {
            ChildScreen("A1")
        }
    } else {
        ChildScreen("A1")
    }
}

@Serializable
internal object RouteA2

@Composable
private fun A2Route(
    isFullscreen: Boolean,
    navController: NavController,
) {
    if (isFullscreen) {
        TitleBarWithBack(
            onBackClick = { navController.navigateUp() },
        ) {
            ChildScreen("A2")
        }
    } else {
        ChildScreen("A2")
    }
}

@Serializable
internal object RouteA3

@Composable
private fun A3Route(
    isFullscreen: Boolean,
    navController: NavController,
) {
    if (isFullscreen) {
        TitleBarWithBack(
            onBackClick = { navController.navigateUp() },
        ) {
            ChildScreen("A3")
        }
    } else {
        ChildScreen("A3")
    }
}

@Composable
private fun ChildScreen(text: String) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = text,
            fontSize = 66.sp,
            modifier = Modifier.align(Alignment.Center),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TitleBarWithBack(
    onBackClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    Surface {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                title = {},
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBackIos,
                        contentDescription = "",
                        modifier =
                            Modifier
                                .clickable { onBackClick() }
                                .padding(12.dp),
                    )
                },
            )

            content()
        }
    }
}
