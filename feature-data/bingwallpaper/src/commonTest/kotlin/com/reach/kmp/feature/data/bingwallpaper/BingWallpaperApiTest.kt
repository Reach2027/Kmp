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

package com.reach.kmp.feature.data.bingwallpaper

import com.reach.kmp.data.core.network.provideHttpClient
import com.reach.kmp.feature.data.bingwallpaper.datasource.DefaultBingWallpaperApi
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertTrue

class BingWallpaperApiTest {
    @Test
    fun apiTest() =
        runTest {
            val api = DefaultBingWallpaperApi(provideHttpClient())
            val result = api.getBingWallpapers(7, 1)
            assertTrue { result.images.isNotEmpty() }
        }
}
