package com.omralcorut.spacedelivery.utils

import com.omralcorut.spacedelivery.db.entity.Station
import kotlin.math.pow
import kotlin.math.sqrt

fun calculateEus(currentStation: Station, targetStation: Station): Int {
    return sqrt(
        (targetStation.coordinateX.toDouble() - currentStation.coordinateX).pow(2) + (targetStation.coordinateY.toDouble() - currentStation.coordinateY).pow(
            2
        )
    ).toInt()
}