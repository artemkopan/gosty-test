package com.gosty

import android.app.Application
import com.gosty.di.ServiceLocator

class GostyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        ServiceLocator.initialize(this)
    }
}