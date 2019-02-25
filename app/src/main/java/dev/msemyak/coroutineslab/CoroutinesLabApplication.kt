package dev.msemyak.coroutineslab

import android.app.Application
import dev.msemyak.coroutineslab.di.networkModule
import dev.msemyak.coroutineslab.di.presenterModule
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class CoroutinesLabApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(networkModule, presenterModule))

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

    }
}