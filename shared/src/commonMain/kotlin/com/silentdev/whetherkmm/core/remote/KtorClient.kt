package com.silentdev.whetherkmm.core.remote

import io.ktor.client.HttpClient

class KtorApi {
    val client: HttpClient by lazy { createHttpClient() }
}

// Expect function for creating the HTTP client
expect fun createHttpClient(): HttpClient