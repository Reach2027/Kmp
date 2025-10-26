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

package com.reach.kmp.shared

import com.reach.kmp.feature.bingwallpaper.BingWallpaperModule
import com.reach.kmp.feature.learn.LearnModule
import org.koin.core.annotation.KoinApplication
import org.koin.ksp.generated.startKoin

@KoinApplication(
    modules = [LearnModule::class, BingWallpaperModule::class],
)
object KoinApp {
    var isInit = false
}

fun KoinApp.init() {
    if (isInit) return
    isInit = true
    KoinApp.startKoin {
        printLogger()
    }
}
