package com.reach.kmp.feature.data.bingwallpaper

import com.reach.kmp.data.core.network.provideHttpClient
import com.reach.kmp.feature.data.bingwallpaper.source.DefaultBingWallpaperApi
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertTrue

class BingWallpaperApiTest {

    @Test
    fun apiTest() = runTest {
        val api = DefaultBingWallpaperApi(provideHttpClient())
        val result = api.getBingWallpapers(7, 1)
        assertTrue { result.images.isNotEmpty() }
    }

}