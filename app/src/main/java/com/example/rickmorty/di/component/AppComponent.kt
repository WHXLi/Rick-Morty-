package com.example.rickmorty.di.component

import com.example.rickmorty.di.module.ApiModule
import com.example.rickmorty.di.module.AppModule
import com.example.rickmorty.di.module.CiceroneModule
import com.example.rickmorty.mvp.presenter.ActivityMainPresenter
import com.example.rickmorty.mvp.presenter.FragmentCharacterPresenter
import com.example.rickmorty.mvp.presenter.FragmentCharactersPresenter
import com.example.rickmorty.ui.activity.ActivityMain
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        ApiModule::class
    ]
)
interface AppComponent {
    fun inject(activityMain: ActivityMain)
    fun inject(activityMainPresenter: ActivityMainPresenter)
    fun inject(fragmentCharactersPresenter: FragmentCharactersPresenter)
    fun inject(fragmentCharacterPresenter: FragmentCharacterPresenter)
}