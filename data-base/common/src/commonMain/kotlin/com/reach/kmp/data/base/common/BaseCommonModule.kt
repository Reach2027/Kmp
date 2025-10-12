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

package com.reach.kmp.data.base.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.Module
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single

@Module
@Configuration
@ComponentScan("com.reach.kmp.data.base.common")
class BaseCommonModule {
    @Single(binds = [CoroutineScope::class])
    @Named(DispatcherNamed.MAIN)
    fun dispatcherMain(): CoroutineDispatcher = Dispatchers.Main

    @Single(binds = [CoroutineScope::class])
    @Named(DispatcherNamed.IO)
    fun dispatcherIO(): CoroutineDispatcher = Dispatchers.IO

    @Single(binds = [CoroutineScope::class])
    @Named(DispatcherNamed.DEFAULT)
    fun dispatcherDefault(): CoroutineDispatcher = Dispatchers.Default

    @Single(binds = [CoroutineScope::class])
    @Named(DispatcherNamed.UNCONFINED)
    fun dispatcherUnconfined(): CoroutineDispatcher = Dispatchers.Unconfined
}

object DispatcherNamed {
    const val MAIN = "DATA_BASE_DISPATCHER_MAIN"
    const val IO = "DATA_BASE_DISPATCHER_IO"
    const val DEFAULT = "DATA_BASE_DISPATCHER_DEFAULT"
    const val UNCONFINED = "DATA_BASE_DISPATCHER_UNCONFINED"
}
