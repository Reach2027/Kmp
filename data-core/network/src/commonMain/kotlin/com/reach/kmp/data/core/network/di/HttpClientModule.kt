package com.reach.kmp.data.core.network.di

import com.reach.kmp.data.core.network.ktorClient
import io.ktor.client.HttpClient
import org.koin.dsl.module

val httpClientModule = module {
    single<HttpClient> { ktorClient() }
}
