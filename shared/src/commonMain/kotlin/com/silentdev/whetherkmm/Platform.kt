package com.silentdev.whetherkmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform