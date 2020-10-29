package com.omralcorut.spacedelivery.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.omralcorut.spacedelivery.db.dao.ShipDao
import com.omralcorut.spacedelivery.db.dao.StationDao
import com.omralcorut.spacedelivery.db.entity.Ship
import com.omralcorut.spacedelivery.db.entity.Station

@Database(
    entities = [
        Ship::class,
        Station::class
    ], version = 1
)
abstract class CacheDatabase : RoomDatabase() {
    abstract fun shipDao(): ShipDao
    abstract fun stationDao(): StationDao
}