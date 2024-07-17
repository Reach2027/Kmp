package com.reach.kmp.data.base.common.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

val dispatcherModule = module {
    single<CoroutineDispatcher>(qualifier(QualifierDispatchers.IO)) { Dispatchers.IO }

    single<CoroutineDispatcher>(qualifier(QualifierDispatchers.Default)) { Dispatchers.Default }
}
