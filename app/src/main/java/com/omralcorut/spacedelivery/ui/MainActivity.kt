package com.omralcorut.spacedelivery.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.omralcorut.spacedelivery.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        findViewById<ViewGroup>(android.R.id.content).also {
            it.addView(LayoutInflater.from(this).inflate(R.layout.activity_main, it, false))
        }
    }

}