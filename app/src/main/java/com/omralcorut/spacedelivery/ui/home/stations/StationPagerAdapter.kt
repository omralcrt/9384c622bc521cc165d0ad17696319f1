package com.omralcorut.spacedelivery.ui.home.stations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.omralcorut.spacedelivery.R
import com.omralcorut.spacedelivery.databinding.ItemStationBinding
import com.omralcorut.spacedelivery.db.entity.Ship
import com.omralcorut.spacedelivery.db.entity.Station
import com.omralcorut.spacedelivery.model.TargetStation
import com.omralcorut.spacedelivery.utils.calculateEus
import kotlin.math.pow
import kotlin.math.sqrt

class StationPagerAdapter(
    val travelStation: (station: Station, eus: Int) -> Unit
) : RecyclerView.Adapter<StationPagerAdapter.ViewHolder>() {

    private val stations: MutableList<Station> = mutableListOf()
    private lateinit var currentStation: Station
    private lateinit var ship: Ship

    fun addAll(newStations: List<Station>, newCurrentStation: Station, newShip: Ship) {
        currentStation = newCurrentStation
        ship = newShip
        stations.clear()
        stations.addAll(newStations)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemStationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val targetStation = stations[position]
        holder.binding.station =
            TargetStation(targetStation = targetStation, eus = calculateEus(currentStation, targetStation))
        holder.binding.travelVisibility = travelVisibility(targetStation)

        holder.binding.travelButton.setOnClickListener {
            if (hasEnoughTime(targetStation)) {
                travelStation(targetStation, calculateEus(currentStation, targetStation))
            } else {
                AlertDialog.Builder(holder.itemView.context)
                    .setTitle(R.string.stations_enough_time_dialog_title)
                    .setMessage(R.string.stations_enough_time_dialog_description)
                    .setPositiveButton(R.string.stations_enough_time_dialog_ok_button, null)
                    .show()
            }
        }
    }

    private fun travelVisibility(station: Station): Boolean = station.need != 0

    private fun hasEnoughTime(station: Station): Boolean =
        ship.universalSpaceTime!! > calculateEus(currentStation, station)

    override fun getItemCount(): Int = stations.size

    class ViewHolder(val binding: ItemStationBinding) : RecyclerView.ViewHolder(binding.root)
}