package com.omralcorut.spacedelivery.repository.ship

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.omralcorut.spacedelivery.db.RoomDatabase
import com.omralcorut.spacedelivery.db.entity.Ship
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShipRepositoryImpl @Inject constructor(
    private val roomDatabase: RoomDatabase
) : ShipRepository {
    override lateinit var ship: MutableLiveData<Ship>
    override suspend fun getShip(): Flow<Ship> {
        val shipFlow = roomDatabase.getShip()
        this.ship = shipFlow.asLiveData() as MutableLiveData<Ship>
        return shipFlow
    }

    override suspend fun insertShip(ship: Ship) {
        roomDatabase.insertShip(ship)
        this.ship = MutableLiveData(ship)
    }

    override suspend fun updateShip(eus: Int, needStock: Int, locationName: String) {
        val updatedShip = roomDatabase.getShip().first()
        updatedShip.damageCapacity =
            ship.value!!.universalSpaceTime!! - ((eus * 10000) / ship.value!!.durabilityPeriod!!)
        updatedShip.spacesuitCount = ship.value!!.spacesuitCount!! - needStock
        updatedShip.universalSpaceTime = ship.value!!.universalSpaceTime!! - eus
        updatedShip.stationName = locationName

        this.ship.value = updatedShip
        roomDatabase.updateShip(updatedShip)
    }

    override suspend fun clearData() {
        roomDatabase.clearData()
    }
}