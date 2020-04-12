package com.chudy.miquidocatfacts.application

import android.app.Application
import com.chudy.miquidocatfacts.model.catFactRepositoryModule
import com.chudy.miquidocatfacts.networking.networkModule
import com.chudy.miquidocatfacts.viewmodel.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * An application class that starts Koin in the context of the whole app,
 * defines modules.
 */
class MiquidoCatFacts : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MiquidoCatFacts)
            modules(listOf(networkModule, catFactRepositoryModule, viewModelModule))
        }
    }
}