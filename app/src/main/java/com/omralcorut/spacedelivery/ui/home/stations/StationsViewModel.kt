package com.omralcorut.spacedelivery.ui.home.stations

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.omralcorut.spacedelivery.db.entity.Ship
import com.omralcorut.spacedelivery.repository.ship.ShipRepository

class StationsViewModel @ViewModelInject constructor(
    private val shipRepository: ShipRepository
) : ViewModel() {
    val ship: MutableLiveData<Ship> = shipRepository.ship
}