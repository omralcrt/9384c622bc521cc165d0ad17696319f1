package com.omralcorut.spacedelivery.repository.ship

import androidx.lifecycle.MutableLiveData
import com.omralcorut.spacedelivery.db.entity.Ship
import kotlinx.coroutines.flow.Flow

interface ShipRepository {
    var ship: MutableLiveData<Ship>
    suspend fun getShip(): Flow<Ship>
    suspend fun insertShip(ship: Ship)
    suspend fun updateShip(eus: Int, needStock: Int, locationName: String)
}