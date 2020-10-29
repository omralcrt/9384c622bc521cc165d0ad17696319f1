package com.omralcorut.spacedelivery.db

import androidx.room.withTransaction
import com.omralcorut.spacedelivery.db.dao.ShipDao
import com.omralcorut.spacedelivery.db.entity.Ship
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

class RoomDatabaseImpl @Inject constructor(
    private val cacheDatabase: CacheDatabase,
    private val shipDao: ShipDao
) : RoomDatabase {
    override fun getShip(): Flow<Ship> = shipDao.getShip().distinctUntilChanged()

    override suspend fun insertShip(ship: Ship) {
        cacheDatabase.withTransaction {
            shipDao.insert(ship)
        }
    }
}