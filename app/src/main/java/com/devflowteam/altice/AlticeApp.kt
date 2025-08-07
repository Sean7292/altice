package com.devflowteam.altice

import android.app.Application
import com.devflowteam.altice.di.appModule
import com.devflowteam.altice.di.dataModule
import com.devflowteam.altice.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class AlticeApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@AlticeApp)

            modules(listOf(appModule, dataModule, domainModule))
        }
    }
}