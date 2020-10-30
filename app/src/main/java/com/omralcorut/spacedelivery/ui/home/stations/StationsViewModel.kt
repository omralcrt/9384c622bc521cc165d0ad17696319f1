package com.omralcorut.spacedelivery.ui.home.stations

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.omralcorut.spacedelivery.db.entity.Ship
import com.omralcorut.spacedelivery.db.entity.Station
import com.omralcorut.spacedelivery.model.State
import com.omralcorut.spacedelivery.repository.ship.ShipRepository
import com.omralcorut.spacedelivery.repository.station.StationRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class StationsViewModel @ViewModelInject constructor(
    private val shipRepository: ShipRepository,
    private val stationRepository: StationRepository,
) : ViewModel() {
    val ship: MutableLiveData<Ship> = shipRepository.ship
    var currentStation = MutableLiveData<Station>()
    val stations = MutableLiveData<State<List<Station>>>()

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
        }
    }
}