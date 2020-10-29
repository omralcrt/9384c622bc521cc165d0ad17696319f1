package com.omralcorut.spacedelivery.repository.ship

import com.omralcorut.spacedelivery.db.entity.Ship
import kotlinx.coroutines.flow.Flow

interface ShipRepository {
    fun getShip(): Flow<Ship>
    suspend fun insertShip(ship: Ship)
}