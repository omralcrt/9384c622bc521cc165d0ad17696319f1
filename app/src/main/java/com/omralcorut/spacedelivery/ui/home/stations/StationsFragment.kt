package com.omralcorut.spacedelivery.ui.home.stations

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.omralcorut.spacedelivery.R
import com.omralcorut.spacedelivery.databinding.FragmentStationsBinding
import com.omralcorut.spacedelivery.enums.GameOverType
import com.omralcorut.spacedelivery.model.State
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StationsFragment : Fragment(R.layout.fragment_stations) {

    private val stationsViewModel: StationsViewModel by viewModels()

    private val stationAdapter = StationPagerAdapter { station, eus ->
        stationsViewModel.travelStation(station, eus)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentStationsBinding.bind(view)
        binding.viewModel = stationsViewModel
        binding.lifecycleOwner = this
        binding.viewPager.adapter = stationAdapter

        stationsViewModel.stations.observe(viewLifecycleOwner, { state ->
            if (state is State.Success) {
                val currentStation =
                    state.data.first { it.name == stationsViewModel.ship.value!!.stationName }
                stationAdapter.addAll(state.data, currentStation, stationsViewModel.ship.value!!)
                stationsViewModel.updateCurrentStation(currentStation)
            }
        })

        stationsViewModel.gameOver.observe(viewLifecycleOwner, { gameOver ->
            if (gameOver != GameOverType.NOT_OVER) {
                AlertDialog.Builder(requireContext())
                    .setTitle(R.string.stations_game_over_dialog_title)
                    .setMessage(gameOver.description)
                    .setCancelable(false)
                    .setPositiveButton(
                        R.string.stations_game_over_dialog_ok_button
                    ) { dialog, which ->
                        stationsViewModel.clearData {
                            parentFragment?.parentFragment?.findNavController()?.navigate(R.id.action_home_to_create_ship)
                        }
                    }
                    .show()
            }
        })
    }
}