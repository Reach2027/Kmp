package com.reach.kmp.data.base.common.di

import com.reach.kmp.data.base.common.multiplatform.getIoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

val dispatcherModule = module {
    single<CoroutineDispatcher>(qualifier(QualifierDispatchers.IO)) { getIoDispatcher() }

    single<CoroutineDispatcher>(qualifier(QualifierDispatchers.Default)) { Dispatchers.Default }

    single<CoroutineDispatcher>(qualifier(QualifierDispatchers.Main)) { Dispatchers.Main }
}
