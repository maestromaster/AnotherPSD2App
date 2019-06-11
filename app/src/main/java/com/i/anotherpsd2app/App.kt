package com.i.anotherpsd2app

import android.app.Application
import com.i.anotherpsd2app.di.appModules
import com.i.anotherpsd2app.di.featureModules
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(
            appModules,
            featureModules))
    }
}