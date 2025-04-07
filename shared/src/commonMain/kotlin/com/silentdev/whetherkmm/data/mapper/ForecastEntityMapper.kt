package com.silentdev.whetherkmm.data.mapper

import com.silentdev.whetherkmm.ForecastCache
import com.silentdev.whetherkmm.domain.model.ForecastDay

fun ForecastCache.toDomain(): ForecastDay {
    return ForecastDay(
        date = date,
        tempMax = tempMax ?: 0.0,
        tempMin = tempMin ?: 0.0,
        code = code ?: 0
    )
}
