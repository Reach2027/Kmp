package com.reach.kmp.data.base.common.multiplatform

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal actual fun getIoDispatcher(): CoroutineDispatcher = Dispatchers.Default