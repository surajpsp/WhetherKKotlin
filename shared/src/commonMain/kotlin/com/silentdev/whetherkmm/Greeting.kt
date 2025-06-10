package com.silentdev.whetherkmm

class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}


fun getMissingNumber(array: IntArray): IntArray {
    val missedNumber: ArrayList<Int> = arrayListOf()
    var lastIndex = 0

    for (i in array) {
        missedNumber.add(array[i])
        lastIndex = i
        val sum = array[lastIndex] + array[i]
        if (sum != array[i + 1]) missedNumber
    }

    return missedNumber.toIntArray()
}