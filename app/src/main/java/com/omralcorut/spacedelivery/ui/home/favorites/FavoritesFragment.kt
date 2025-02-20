package com.omralcorut.spacedelivery.ui.home.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.omralcorut.spacedelivery.R
import com.omralcorut.spacedelivery.databinding.FragmentFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private val favoritesViewModel: FavoritesViewModel by viewModels()

    private val favoriteAdapter = FavoriteListAdapter { station ->
        favoritesViewModel.favoriteStation(station)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentFavoritesBinding.bind(view)

        binding.favoritesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.favoritesRecyclerView.adapter = favoriteAdapter

        favoritesViewModel.stations.observe(viewLifecycleOwner, { list ->
            favoriteAdapter.addAll(list)
        })
    }
}