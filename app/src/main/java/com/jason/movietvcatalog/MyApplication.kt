package com.jason.movietvcatalog

import android.app.Application
import com.jason.movietvcatalog.core.di.databaseModule
import com.jason.movietvcatalog.core.di.networkModule
import com.jason.movietvcatalog.core.di.repositoryModule
import com.jason.movietvcatalog.di.useCaseModule
import com.jason.movietvcatalog.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}