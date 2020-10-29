package com.omralcorut.spacedelivery.ui.home.stations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omralcorut.spacedelivery.databinding.ItemStationBinding
import com.omralcorut.spacedelivery.db.entity.Station

class StationPagerAdapter : RecyclerView.Adapter<StationPagerAdapter.ViewHolder>() {

    private val stations: MutableList<Station> = mutableListOf()

    fun addAll(newStations: List<Station>) {
        stations.clear()
        stations.addAll(newStations)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemStationBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.station = stations[position]
    }

    override fun getItemCount(): Int = stations.size

    class ViewHolder(val binding: ItemStationBinding) : RecyclerView.ViewHolder(binding.root)
}