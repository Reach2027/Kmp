package com.reach.kmp.data.base.common.multiplatform

import kotlinx.coroutines.CoroutineDispatcher

internal expect fun getIoDispatcher(): CoroutineDispatcher
