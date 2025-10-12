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

import com.reach.kmp.data.base.common.BaseCommonModule
import com.reach.kmp.data.core.network.NetworkModule
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Module

@Module(includes = [BaseCommonModule::class, NetworkModule::class])
@Configuration
@ComponentScan("com.reach.kmp.feature.data.bingwallpaper")
class BingWallpaperDataModule
