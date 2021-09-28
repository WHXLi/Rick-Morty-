package com.example.rickmorty

import android.app.Application
import com.example.rickmorty.di.component.AppComponent
import com.example.rickmorty.di.component.DaggerAppComponent
import com.example.rickmorty.di.module.AppModule

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