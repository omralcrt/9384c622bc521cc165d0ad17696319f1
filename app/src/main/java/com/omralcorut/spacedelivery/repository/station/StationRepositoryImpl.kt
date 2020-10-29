package com.omralcorut.spacedelivery.repository.station

import com.omralcorut.spacedelivery.api.StationApi
import com.omralcorut.spacedelivery.api.response.StationResponse
import com.omralcorut.spacedelivery.db.RoomDatabase
import com.omralcorut.spacedelivery.db.entity.Station
import com.omralcorut.spacedelivery.model.State
import com.omralcorut.spacedelivery.repository.NetworkBoundRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Singleton
class StationRepositoryImpl @Inject constructor(
    private val roomDatabase: RoomDatabase,
    private val stationApi: StationApi
) : StationRepository {
    override fun getStations(): Flow<State<List<Station>>> {
        return object : NetworkBoundRepository<List<Station>, List<StationResponse>>() {

            override suspend fun saveRemoteData(response: List<StationResponse>) =
                roomDatabase.insertStations(response.map {
                    Station(
                        name = it.name,
                        coordinateX = it.coordinateX,
                        coordinateY = it.coordinateY,
                        capacity = it.capacity,
                        stock = it.stock,
                        need = it.need,
                    )
                })

            override fun fetchFromLocal(): Flow<List<Station>> = roomDatabase.getStations()

            override suspend fun isLocalEmpty(): Boolean = roomDatabase.getStations().first().isEmpty()

            override suspend fun fetchFromRemote(): Response<List<StationResponse>> =
                stationApi.getStations()
        }.asFlow()
    }
}