package com.omralcorut.spacedelivery.ui.createship

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.omralcorut.spacedelivery.R
import com.omralcorut.spacedelivery.databinding.FragmentCreateShipBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateShipFragment : Fragment(R.layout.fragment_create_ship) {

    private val createShipViewModel: CreateShipViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCreateShipBinding.bind(view)
        binding.viewModel = createShipViewModel

        createShipViewModel.error.observe(viewLifecycleOwner, { error ->
            if (error) {
                createShipViewModel.error.value = false
                val snackBar = Snackbar
                    .make(view, R.string.create_ship_error, Snackbar.LENGTH_LONG)
                val sbView = snackBar.view
                val sbTextView =
                    sbView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
                sbTextView.setTextColor(ContextCompat.getColor(requireContext(), R.color.red_a200))
                snackBar.show()
            }
        })

        createShipViewModel.hasGame {
            AlertDialog.Builder(requireContext())
                .setTitle(R.string.create_ship_has_game_dialog_title)
                .setMessage(R.string.create_ship_has_game_dialog_description)
                .setPositiveButton(
                    R.string.create_ship_has_game_dialog_continue_button
                ) { dialog, which -> findNavController().navigate(R.id.action_create_ship_to_home) }
                .setNegativeButton(R.string.create_ship_has_game_dialog_cancel_button, null)
                .show()
        }
    }
}