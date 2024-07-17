package com.reach.kmp.feature.data.bingwallpaper.di

import com.reach.kmp.data.base.common.di.QualifierDispatchers
import com.reach.kmp.data.base.common.di.dispatcherModule
import com.reach.kmp.data.core.network.di.httpClientModule
import com.reach.kmp.feature.data.bingwallpaper.BingWallpaperRepo
import com.reach.kmp.feature.data.bingwallpaper.DefaultBingWallpaperRepo
import com.reach.kmp.feature.data.bingwallpaper.source.BingWallpaperApi
import com.reach.kmp.feature.data.bingwallpaper.source.DefaultBingWallpaperApi
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

val bingWallpaperDataModule = module {
    includes(httpClientModule, dispatcherModule)

    factoryOf(::DefaultBingWallpaperApi) {
        bind<BingWallpaperApi>()
    }

    factory<BingWallpaperRepo> {
        DefaultBingWallpaperRepo(
            get(),
            get(qualifier(QualifierDispatchers.IO)),
        )
    }
}
