package com.omralcorut.spacedelivery.ui.home.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.omralcorut.spacedelivery.databinding.ItemFavoriteBinding
import com.omralcorut.spacedelivery.db.entity.Station
import com.omralcorut.spacedelivery.model.TargetStation
import com.omralcorut.spacedelivery.utils.calculateEus

class FavoriteListAdapter(
    val favoriteStation: (station: Station) -> Unit
) : RecyclerView.Adapter<FavoriteListAdapter.ViewHolder>() {

    private val stations: MutableList<Station> = mutableListOf()

    fun addAll(newStations: List<Station>) {
        stations.clear()
        stations.addAll(newStations)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemFavoriteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val targetStation = stations[position]
        holder.binding.station =
            TargetStation(targetStation = targetStation, eus = calculateEus(
                Station(coordinateX = 0, coordinateY = 0),
                targetStation))

        holder.binding.favoriteButton.setOnClickListener {
            favoriteStation(stations[position])
        }
    }

    override fun getItemCount(): Int = stations.size

    class ViewHolder(val binding: ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root)
}