package com.silentdev.whetherkmm.core.remote

import com.silentdev.whetherkmm.core.util.BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

actual fun createHttpClient(): HttpClient {
    return HttpClient(Darwin) {
        expectSuccess = true
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    println(message)
                }
            }
            level = LogLevel.ALL
        }
        defaultRequest {
            header("Content-Type", "application/json")
//            header(HttpHeaders.Authorization,"Bearer $OPEN_AI_KEY")
            url(BASE_URL)
        }
//        install(Auth) {
//            bearer {
//                loadTokens {
//                    BearerTokens(
//                        accessToken = customAuthorization ?: TokenProvider.getNewToken(),
//                        refreshToken = TokenProvider.getRefreshToken()
//                    )
//                }
//            }
//        }
    }
}