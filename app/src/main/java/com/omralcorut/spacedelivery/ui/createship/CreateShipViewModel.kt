package com.omralcorut.spacedelivery.ui.createship

import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.omralcorut.spacedelivery.R
import com.omralcorut.spacedelivery.db.entity.Ship
import com.omralcorut.spacedelivery.repository.ship.ShipRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateShipViewModel @ViewModelInject constructor(
    private val shipRepository: ShipRepository
) : ViewModel() {
    val model = CreateShipModel()
    val error = MutableLiveData(false)

    fun onContinueButtonClicked(view: View) {
        if (model.validateSpaceShip()) {
            viewModelScope.launch(Dispatchers.IO) {
                val ship = Ship(
                    name = model.shipName,
                    durability = model.durabilityProgress,
                    speed = model.speedProgress,
                    capacity = model.capacityProgress,
                    spacesuitCount = getSpacesuitCount(),
                    universalSpaceTime = getUniversalSpaceTime(),
                    durabilityPeriod = getDurabilityPeriod()
                )
                shipRepository.insertShip(ship)
                view.findNavController().navigate(R.id.action_create_ship_to_home)
            }
        } else {
            error.value = true
        }
    }

    private fun getSpacesuitCount() = model.capacityProgress * 10000
    private fun getUniversalSpaceTime() = model.speedProgress * 20
    private fun getDurabilityPeriod() = model.durabilityProgress * 10000
}