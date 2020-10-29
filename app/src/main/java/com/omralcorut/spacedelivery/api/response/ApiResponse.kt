package com.omralcorut.spacedelivery.api.response

data class StationResponse(
    val name: String,
    val coordinateX: Int,
    val coordinateY: Int,
    val capacity: Int,
    val stock: Int,
    val need: Int,
)