package com.example.rickmorty.mvp.presenter

import com.example.rickmorty.mvp.navigation.IScreens
import com.example.rickmorty.mvp.view.ActivityMainView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class ActivityMainPresenter: MvpPresenter<ActivityMainView>() {
    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showFragmentCharacters()
    }

    fun displayFragmentCharacters(){
        router.replaceScreen(screens.charactersListScreen())
    }

    fun backClick(){
        router.exit()
    }
}