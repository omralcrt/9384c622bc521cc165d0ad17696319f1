package com.omralcorut.spacedelivery.db

import androidx.room.withTransaction
import com.omralcorut.spacedelivery.db.dao.ShipDao
import com.omralcorut.spacedelivery.db.dao.StationDao
import com.omralcorut.spacedelivery.db.entity.Ship
import com.omralcorut.spacedelivery.db.entity.Station
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

@ExperimentalCoroutinesApi
class RoomDatabaseImpl @Inject constructor(
    private val cacheDatabase: CacheDatabase,
    private val shipDao: ShipDao,
    private val stationDao: StationDao
) : RoomDatabase {
    override fun getShip(): Flow<Ship> = shipDao.getShip().distinctUntilChanged()

    override suspend fun insertShip(ship: Ship) {
        cacheDatabase.withTransaction {
            stationDao.deleteAll()
            shipDao.deleteAll()
            shipDao.insert(ship)
        }
    }

    override suspend fun updateShip(ship: Ship) {
        cacheDatabase.withTransaction {
            shipDao.update(ship)
        }
    }

    override fun getStations(): Flow<List<Station>> = stationDao.getStations().distinctUntilChanged()

    override fun getActiveStations(): Flow<List<Station>> = stationDao.getActiveStations().distinctUntilChanged()

    override fun getFavoriteStations(): Flow<List<Station>> = stationDao.getFavoriteStations().distinctUntilChanged()

    override suspend fun insertStations(stations: List<Station>) {
        cacheDatabase.withTransaction {
            stationDao.deleteAll()
            stationDao.insert(stations)
        }
    }

    override fun getStationByName(name: String): Flow<Station> = stationDao.getStation(name).distinctUntilChanged()

    override suspend fun updateStation(station: Station) {
        cacheDatabase.withTransaction {
            stationDao.update(station)
        }
    }

    override suspend fun clearData() {
        cacheDatabase.withTransaction {
            stationDao.deleteAll()
            shipDao.deleteAll()
        }
    }
}