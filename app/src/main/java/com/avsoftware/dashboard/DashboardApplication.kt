package com.avsoftware.dashboard

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber.DebugTree
import timber.log.Timber.Forest.plant


@HiltAndroidApp
class DashboardApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        plant(DebugTree())

    }


}