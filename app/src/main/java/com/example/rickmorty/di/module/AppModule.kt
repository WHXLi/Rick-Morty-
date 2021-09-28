package com.example.rickmorty.di.module

import com.example.rickmorty.App
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler

@Module
class AppModule(private val app: App) {
    @Provides
    fun app() = App()

    @Provides
    fun uiScheduler(): Scheduler = AndroidSchedulers.mainThread()

}