package com.reach.kmp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.reach.kmp.feature.bingwallpaper.ROUTE_BING_WALLPAPER
import com.reach.kmp.feature.bingwallpaper.bingWallpaperRoute
import com.reach.kmp.feature.bingwallpaper.di.bingWallpaperModule
import com.reach.kmp.ui.core.common.animation.enterScreenTransition
import com.reach.kmp.ui.core.common.animation.exitScreenTransition
import com.reach.kmp.ui.core.common.animation.popEnterScreenTransition
import com.reach.kmp.ui.core.common.animation.popExitScreenTransition
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication
import org.koin.dsl.module

@Composable
fun App(
    navController: NavHostController = rememberNavController()
) {
    KoinApplication(application = { modules(appModule) }) {
        MaterialTheme {
            Surface(modifier = Modifier.fillMaxSize()) {
                AppNavHost(navController)
            }
        }
    }
}

private val appModule = module {
    includes(bingWallpaperModule)
}

@Composable
private fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = ROUTE_BING_WALLPAPER,
        enterTransition = { enterScreenTransition() },
        exitTransition = { exitScreenTransition() },
        popEnterTransition = { popEnterScreenTransition() },
        popExitTransition = { popExitScreenTransition() },
    ) {
        bingWallpaperRoute()
    }
}