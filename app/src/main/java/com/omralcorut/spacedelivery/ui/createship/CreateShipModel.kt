package com.omralcorut.spacedelivery.ui.createship

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.omralcorut.spacedelivery.BR

class CreateShipModel : BaseObservable() {
    private val totalPoint: Int = 15

    @Bindable
    var shipName: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.shipName)
        }
        get() = field

    @Bindable
    var remainingPoint: Int = 15
        set(value) {
            field = value
            notifyPropertyChanged(BR.remainingPoint)
        }
        get() = field

    @Bindable
    var durabilityProgress: Int = 0
        set(value) {
            val newDistributedPoints = distributedPoint(newDurabilityProgress = value)
            field = calculateNewValue(newDistributedPoints, value)
            notifyPropertyChanged(BR.durabilityProgress)
        }
        get() = field

    @Bindable
    var speedProgress: Int = 0
        set(value) {
            val newDistributedPoints = distributedPoint(newSpeedProgress = value)
            field = calculateNewValue(newDistributedPoints, value)
            notifyPropertyChanged(BR.speedProgress)
        }
        get() = field

    @Bindable
    var capacityProgress: Int = 0
        set(value) {
            val newDistributedPoints = distributedPoint(newCapacityProgress = value)
            field = calculateNewValue(newDistributedPoints, value)
            notifyPropertyChanged(BR.capacityProgress)
        }
        get() = field

    private fun calculateNewValue(newDistributedPoints: Int, value: Int): Int {
        return if (newDistributedPoints > totalPoint) {
            val difference = newDistributedPoints - totalPoint
            remainingPoint = 15
            value - difference
        } else {
            remainingPoint = totalPoint - newDistributedPoints
            value
        }
    }

    private fun distributedPoint(
        newDurabilityProgress: Int = durabilityProgress,
        newSpeedProgress: Int = speedProgress,
        newCapacityProgress: Int = capacityProgress
    ) = newDurabilityProgress + newSpeedProgress + newCapacityProgress

    fun validateSpaceShip(): Boolean {
        return shipName.isNotEmpty() && remainingPoint == 0 && durabilityProgress != 0 && speedProgress != 0 && capacityProgress != 0
    }
}