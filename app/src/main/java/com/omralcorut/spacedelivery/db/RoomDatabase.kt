package com.omralcorut.spacedelivery.db

import com.omralcorut.spacedelivery.db.entity.Ship
import com.omralcorut.spacedelivery.db.entity.Station
import kotlinx.coroutines.flow.Flow

interface RoomDatabase {
    fun getShip(): Flow<Ship>
    suspend fun insertShip(ship: Ship)

    fun getStations(): Flow<List<Station>>
    suspend fun insertStations(stations: List<Station>)
}