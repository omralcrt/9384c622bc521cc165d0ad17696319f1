package com.omralcorut.spacedelivery.db

import com.omralcorut.spacedelivery.db.entity.Ship
import kotlinx.coroutines.flow.Flow

interface RoomDatabase {
    fun getShip(): Flow<Ship>
    suspend fun insertShip(ship: Ship)
}