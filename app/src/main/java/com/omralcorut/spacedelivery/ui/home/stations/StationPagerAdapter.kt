package com.omralcorut.spacedelivery.ui.home.stations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omralcorut.spacedelivery.databinding.ItemStationBinding
import com.omralcorut.spacedelivery.db.entity.Station
import com.omralcorut.spacedelivery.model.TargetStation
import kotlin.math.pow
import kotlin.math.sqrt

class StationPagerAdapter : RecyclerView.Adapter<StationPagerAdapter.ViewHolder>() {

    private val stations: MutableList<Station> = mutableListOf()
    private lateinit var currentStation: Station

    fun addAll(newStations: List<Station>, newCurrentStation: Station) {
        currentStation = newCurrentStation
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
        holder.binding.station = TargetStation(targetStation = stations[position], eus = calculateEus(stations[position]))
    }

    private fun calculateEus(targetStation: Station): Int {
        return sqrt(
            (targetStation.coordinateX.toDouble() - currentStation.coordinateX).pow(2) + (targetStation.coordinateY.toDouble() - currentStation.coordinateY).pow(
                2
            )
        ).toInt()
    }

    override fun getItemCount(): Int = stations.size

    class ViewHolder(val binding: ItemStationBinding) : RecyclerView.ViewHolder(binding.root)
}