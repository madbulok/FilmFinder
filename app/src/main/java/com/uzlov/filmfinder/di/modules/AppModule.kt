package com.uzlov.filmfinder.di.modules

import com.uzlov.filmfinder.app.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App = app

}