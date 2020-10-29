package com.omralcorut.spacedelivery.repository.ship

import com.omralcorut.spacedelivery.db.RoomDatabase
import com.omralcorut.spacedelivery.db.entity.Ship
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShipRepositoryImpl @Inject constructor(
    private val roomDatabase: RoomDatabase
) : ShipRepository {
    override fun getShip(): Flow<Ship> = roomDatabase.getShip()
    override suspend fun insertShip(ship: Ship) {
        roomDatabase.insertShip(ship)
    }
}