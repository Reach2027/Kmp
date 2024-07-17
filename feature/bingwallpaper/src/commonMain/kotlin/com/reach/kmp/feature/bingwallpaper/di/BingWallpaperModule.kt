package com.reach.kmp.feature.bingwallpaper.di

import com.reach.kmp.feature.bingwallpaper.BingWallpaperViewModel
import com.reach.kmp.feature.data.bingwallpaper.di.bingWallpaperDataModule
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val bingWallpaperModule = module {
    includes(bingWallpaperDataModule)

    viewModelOf(::BingWallpaperViewModel)
}

