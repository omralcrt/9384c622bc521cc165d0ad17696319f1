package com.omralcorut.spacedelivery.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.omralcorut.spacedelivery.R
import com.omralcorut.spacedelivery.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)
        val navHostFragment = childFragmentManager
            .findFragmentById(R.id.nav_host_fragment_home) as NavHostFragment
        binding.bottomNavigationBar.setupWithNavController(navHostFragment.navController)
        binding.bottomNavigationBar.setOnNavigationItemReselectedListener { }
    }
}