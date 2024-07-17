package com.reach.kmp.data.base.common.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

val coroutineScopeModule = module {
    includes(dispatcherModule)

    single<CoroutineScope>(qualifier(QualifierCoroutineScope.AppDefault)) {
        val dispatcher: CoroutineDispatcher = get(qualifier(QualifierDispatchers.Default))
        CoroutineScope(SupervisorJob() + dispatcher)
    }

    single<CoroutineScope>(qualifier(QualifierCoroutineScope.AppIo)) {
        val dispatcher: CoroutineDispatcher = get(qualifier(QualifierDispatchers.IO))
        CoroutineScope(SupervisorJob() + dispatcher)
    }

    factory<CoroutineScope>(qualifier(QualifierCoroutineScope.OneParallelismDefault)) {
        val dispatcher: CoroutineDispatcher = get(qualifier(QualifierDispatchers.Default))
        CoroutineScope(SupervisorJob() + dispatcher.limitedParallelism(1))
    }

    factory<CoroutineScope>(qualifier(QualifierCoroutineScope.OneParallelismIo)) {
        val dispatcher: CoroutineDispatcher = get(qualifier(QualifierDispatchers.IO))
        CoroutineScope(SupervisorJob() + dispatcher.limitedParallelism(1))
    }
}
