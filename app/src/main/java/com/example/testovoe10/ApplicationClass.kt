package com.example.testovoe10

import android.app.Application
import com.onesignal.OneSignal

const val ONESIGNAL_APP_ID = "8f69d862-86f3-449d-9f95-613596e7ed4a"

class ApplicationClass : Application() {
    override fun onCreate() {
        super.onCreate()

        // Logging set to help debug issues, remove before releasing your app.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)

        // OneSignal Initialization
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
    }
}