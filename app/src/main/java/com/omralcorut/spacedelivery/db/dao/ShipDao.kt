package com.omralcorut.spacedelivery.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.omralcorut.spacedelivery.db.entity.Ship
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ShipDao: EntityDao<Ship> {
    @Query("select * from ship limit 1")
    abstract fun getShip(): Flow<Ship>

    @Query("delete from ship")
    abstract suspend fun deleteAll()
}