package com.omralcorut.spacedelivery.ui.createship

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.omralcorut.spacedelivery.R
import com.omralcorut.spacedelivery.databinding.FragmentCreateShipBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateShipFragment : Fragment(R.layout.fragment_create_ship) {

    private val createShipViewModel: CreateShipViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCreateShipBinding.bind(view)

    }
}