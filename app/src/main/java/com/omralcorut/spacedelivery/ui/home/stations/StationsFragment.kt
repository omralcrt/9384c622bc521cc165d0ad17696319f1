package com.omralcorut.spacedelivery.ui.home.stations

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.omralcorut.spacedelivery.R
import com.omralcorut.spacedelivery.databinding.FragmentStationsBinding
import com.omralcorut.spacedelivery.model.State
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StationsFragment : Fragment(R.layout.fragment_stations) {

    private val stationsViewModel: StationsViewModel by viewModels()

    private val stationAdapter = StationPagerAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentStationsBinding.bind(view)
        binding.viewModel = stationsViewModel
        binding.viewPager.adapter = stationAdapter

        stationsViewModel.stations.observe(viewLifecycleOwner, { state ->
            if (state is State.Success) {
                val currentStation = state.data.first { it.id == stationsViewModel.ship.value!!.stationId }
                stationAdapter.addAll(state.data, currentStation)
                stationsViewModel.updateCurrentStation(currentStation)
            }
        })
    }
}