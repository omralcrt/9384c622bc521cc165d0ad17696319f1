package com.omralcorut.spacedelivery.ui.createship

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CreateShipViewModel @ViewModelInject constructor() : ViewModel() {
    val model = CreateShipModel()
    val error = MutableLiveData(false)

    fun onContinueButtonClicked() {
        if (model.validateSpaceShip()) {
            Log.d("TAG", "onContinueButtonClicked: Success")
        } else {
            error.value = true
        }
    }
}