package com.omralcorut.spacedelivery.repository.station

import com.omralcorut.spacedelivery.db.entity.Station
import com.omralcorut.spacedelivery.model.State
import kotlinx.coroutines.flow.Flow

interface StationRepository {
    fun getStations(): Flow<State<List<Station>>>
    fun getActiveStations(): Flow<List<Station>>
    fun getFavoriteStations(): Flow<List<Station>>
    suspend fun updateStation(locationName: String)
    suspend fun favoriteStation(locationName: String)
}