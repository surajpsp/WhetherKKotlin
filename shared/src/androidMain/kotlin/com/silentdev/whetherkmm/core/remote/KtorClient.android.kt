package com.silentdev.whetherkmm.core.remote

import com.silentdev.whetherkmm.core.BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

actual fun createHttpClient(): HttpClient {
    return HttpClient(OkHttp) {
        //Timeout plugin to set up timeout milliseconds for client
        install(WebSockets)

        install(HttpTimeout) {
            socketTimeoutMillis = 60_000
            requestTimeoutMillis = 60_000
        }
        //Logging plugin combined with kermit(KMP Logger library)
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {

                }
            }
        }
        //We can configure the BASE_URL and also
        //the deafult headers by defaultRequest builder
        defaultRequest {
            header("Content-Type", "application/json")
//            header(HttpHeaders.Authorization, "Bearer $OPEN_AI_KEY")
            url(BASE_URL)
        }
        //ContentNegotiation plugin for negotiation media types between the client and server
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                explicitNulls = false
            })
        }
        install(Auth) {

//            bearer {
//                loadTokens {
//                    BearerTokens(
//                        accessToken = TokenProvider.getNewToken(),
//                        refreshToken = TokenProvider.getRefreshToken()
//                    )
//                }
//            }
        }
    }
}