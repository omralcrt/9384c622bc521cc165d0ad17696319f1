package com.omralcorut.spacedelivery.model

import com.omralcorut.spacedelivery.db.entity.Station

data class TargetStation (
    val targetStation: Station,
    val eus: Int
)