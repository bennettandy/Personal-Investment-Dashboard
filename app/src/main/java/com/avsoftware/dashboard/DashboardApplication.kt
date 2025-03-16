package com.avsoftware.dashboard

import android.app.Application
import timber.log.Timber.DebugTree
import timber.log.Timber.Forest.plant


class DashboardApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        plant(DebugTree())

    }


}