package com.silentdev.whetherkmm.core.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

fun <T> StateFlow<T>.observe(
    scope: CoroutineScope,
    onEach: (T) -> Unit
): Closeable {

    val job = scope.launch(Dispatchers.Main) {
        collect {
            onEach(it)
        }
    }

    return object : Closeable {
        override fun close() {
            job.cancel()
        }
    }
}


