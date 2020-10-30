package com.omralcorut.spacedelivery.ui.home.favorites

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omralcorut.spacedelivery.db.entity.Station
import com.omralcorut.spacedelivery.model.State
import com.omralcorut.spacedelivery.repository.station.StationRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FavoritesViewModel @ViewModelInject constructor(
    private val stationRepository: StationRepository
) : ViewModel() {
    val stations = MutableLiveData<List<Station>>()

    init {
        viewModelScope.launch {
            stationRepository.getFavoriteStations().collect {
                stations.value = it
            }
        }
    }

    fun favoriteStation(station: Station) {
        viewModelScope.launch {
            stationRepository.favoriteStation(station.name!!)
        }
    }
}