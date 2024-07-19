package com.reach.kmp.data.base.common.multiplatform

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

internal actual fun getIoDispatcher(): CoroutineDispatcher = Dispatchers.IO