package com.omralcorut.spacedelivery.ui.home.stations

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.omralcorut.spacedelivery.db.entity.Ship
import com.omralcorut.spacedelivery.db.entity.Station
import com.omralcorut.spacedelivery.enums.GameOverType
import com.omralcorut.spacedelivery.model.State
import com.omralcorut.spacedelivery.repository.ship.ShipRepository
import com.omralcorut.spacedelivery.repository.station.StationRepository
import com.omralcorut.spacedelivery.utils.calculateEus
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class StationsViewModel @ViewModelInject constructor(
    private val shipRepository: ShipRepository,
    private val stationRepository: StationRepository,
) : ViewModel() {
    val ship: MutableLiveData<Ship> = shipRepository.ship
    var currentStation = MutableLiveData<Station>()
    val stations = MutableLiveData<State<List<Station>>>()
    val gameOver = MutableLiveData(GameOverType.NOT_OVER)

    init {
        viewModelScope.launch {
            stationRepository.getStations().collect {
                stations.value = it
            }
        }
    }

    fun updateCurrentStation(station: Station) {
        viewModelScope.launch {
            currentStation.value = station
        }
    }

    fun travelStation(station: Station, eus: Int) {
        viewModelScope.launch {
            shipRepository.updateShip(eus, station.need!!, station.name!!)
            stationRepository.updateStation(station.name)
            isGameOver()
        }
    }

    fun favoriteStation(station: Station) {
        viewModelScope.launch {
            stationRepository.favoriteStation(station.name!!)
        }
    }

    private fun isGameOver() {
        viewModelScope.launch {
            gameOver.value = when {
                stationRepository.getActiveStations().first()
                    .none { it.need!! != 0 } -> GameOverType.GAME_OVER
                ship.value!!.spacesuitCount!! <= 0
                        || stationRepository.getActiveStations().first()
                    .none { it.need!! < ship.value!!.spacesuitCount!! } -> GameOverType.SPACESUIT_OVER
                ship.value!!.universalSpaceTime!! <= 0 || stationRepository.getActiveStations().first()
                    .none { calculateEus(currentStation.value!!, it) < ship.value!!.universalSpaceTime!! } -> GameOverType.EUS_OVER
                ship.value!!.damageCapacity <= 0 -> GameOverType.DAMAGE_CAPACITY_OVER
                else -> GameOverType.NOT_OVER
            }
        }
    }

    fun clearData(complete: () -> Unit) {
        viewModelScope.launch {
            shipRepository.clearData()
            complete()
        }
    }
}