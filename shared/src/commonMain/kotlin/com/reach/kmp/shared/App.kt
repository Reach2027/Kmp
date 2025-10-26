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

package com.reach.kmp.shared

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.reach.kmp.feature.bingwallpaper.bingWallpaperRoute
import com.reach.kmp.feature.compose.screenSampleRoute
import com.reach.kmp.feature.learn.learnRoute
import com.reach.kmp.ui.core.common.animation.enterScreenTransition
import com.reach.kmp.ui.core.common.animation.exitScreenTransition
import com.reach.kmp.ui.core.common.animation.popEnterScreenTransition
import com.reach.kmp.ui.core.common.animation.popExitScreenTransition

@Composable
fun App(
    darkCallback: (Boolean) -> Unit = { },
    nativeUI: @Composable () -> Unit = { },
) {
    KoinApp.init()

    val IsDark = isSystemInDarkTheme()
    val colorScheme =
        if (IsDark) {
            darkColorScheme()
        } else {
            lightColorScheme()
        }

    LaunchedEffect(IsDark) {
        darkCallback(IsDark)
    }

    MaterialTheme(
        colorScheme = colorScheme,
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            AppNavHost()
        }
    }

//    Box(
//        contentAlignment = Alignment.BottomCenter,
//        modifier = Modifier
//            .fillMaxSize()
//            .safeContentPadding(),
//    ) {
//        nativeUI()
//    }
}

@Composable
private fun AppNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = RouteStart,
        modifier = Modifier.fillMaxSize(),
        enterTransition = { enterScreenTransition() },
        exitTransition = { exitScreenTransition() },
        popEnterTransition = { popEnterScreenTransition() },
        popExitTransition = { popExitScreenTransition() },
    ) {
        startRoute(navController)

        bingWallpaperRoute(navController)

        screenSampleRoute(navController)

        learnRoute(navController)
    }
}
