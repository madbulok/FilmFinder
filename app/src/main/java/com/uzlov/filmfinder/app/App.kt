package com.uzlov.filmfinder.app

import android.app.Application
import com.uzlov.filmfinder.di.modules.components.AppComponent
import com.uzlov.filmfinder.di.modules.components.DaggerAppComponent
import com.uzlov.filmfinder.di.modules.AppModule

class App : Application() {
    companion object {
    lateinit var instance: App
}

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}