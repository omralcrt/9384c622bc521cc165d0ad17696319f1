package com.omralcorut.spacedelivery.ui.home.stations

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.omralcorut.spacedelivery.R
import com.omralcorut.spacedelivery.databinding.FragmentStationsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StationsFragment : Fragment(R.layout.fragment_stations) {

    private val stationsViewModel: StationsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentStationsBinding.bind(view)
        binding.viewModel = stationsViewModel
    }
}