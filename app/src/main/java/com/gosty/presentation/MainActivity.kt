package com.gosty.presentation

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.gosty.R

class MainActivity : AppCompatActivity(R.layout.main_activity) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleBackPressed()
    }

    private fun handleBackPressed() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (!findNavController(R.id.mainNavHost).popBackStack()) {
                    isEnabled = false
                    onBackPressed()
                } else {
                    isEnabled = true
                }
            }
        }
        onBackPressedDispatcher.addCallback(callback)
    }

}