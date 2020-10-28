package com.omralcorut.spacedelivery.ui.createship

import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.omralcorut.spacedelivery.R

class CreateShipViewModel @ViewModelInject constructor() : ViewModel() {
    val model = CreateShipModel()
    val error = MutableLiveData(false)

    fun onContinueButtonClicked(view: View) {
        if (model.validateSpaceShip()) {
            view.findNavController().navigate(R.id.nav_home)
        } else {
            error.value = true
        }
    }
}