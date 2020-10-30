package com.omralcorut.spacedelivery.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.omralcorut.spacedelivery.db.entity.Station
import kotlinx.coroutines.flow.Flow

@Dao
abstract class StationDao: EntityDao<Station> {
    @Query("select * from station where name = :name")
    abstract fun getStation(name: String): Flow<Station>

    @Query("select * from station")
    abstract fun getStations(): Flow<List<Station>>

    @Query("delete from station")
    abstract suspend fun deleteAll()
}