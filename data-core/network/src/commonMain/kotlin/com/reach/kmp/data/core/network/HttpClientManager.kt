package com.reach.kmp.data.core.network

import androidx.annotation.VisibleForTesting
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

private const val CONNECT_TIMEOUT = 5_000L
private const val SOCKET_TIMEOUT = 2_000L

internal fun ktorClient(): HttpClient = HttpClient(CIO) {
    install(Logging)

    install(HttpTimeout) {
        connectTimeoutMillis = CONNECT_TIMEOUT
        socketTimeoutMillis = SOCKET_TIMEOUT
    }

    install(ContentNegotiation) {
        json(
            Json {
                ignoreUnknownKeys = true
            },
        )
    }
}

@VisibleForTesting
fun provideHttpClient() = ktorClient()