package com.example.rickmorty.di.module

import com.example.rickmorty.mvp.navigation.IScreens
import com.example.rickmorty.ui.navigation.AndroidScreens
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CiceroneModule {
    var cicerone: Cicerone<Router> = Cicerone.create()

    @Provides
    fun cicerone() = cicerone

    @Singleton
    @Provides
    fun navigatorHolder() = cicerone.getNavigatorHolder()

    @Singleton
    @Provides
    fun router() = cicerone.router

    @Singleton
    @Provides
    fun screens(): IScreens = AndroidScreens()

}