package com.example.rickmorty.mvp.presenter

import com.example.rickmorty.mvp.model.Character
import com.example.rickmorty.mvp.model.Info
import com.example.rickmorty.network.RickAndMortyApi
import com.example.rickmorty.navigation.IScreens
import com.example.rickmorty.mvp.presenter.rv.ICharactersPresenter
import com.example.rickmorty.mvp.view.FragmentCharactersView
import com.example.rickmorty.mvp.view.rv.ICharactersView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class FragmentCharactersPresenter : BaseMvpPresenter<FragmentCharactersView>() {

    @Inject
    lateinit var api: RickAndMortyApi

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    @Inject
    lateinit var uiScheduler: Scheduler

    val charactersPresenter = CharactersPresenter()

    inner class CharactersPresenter : ICharactersPresenter {
        var info: Info? = null
        var startPosition: Int = 0
        var currentPosition: Int = 0
        var lastPosition: Int = 0
        val characters = mutableListOf<Character>()
        override var
                itemClickListener: ((ICharactersView) -> Unit)? = null

        override fun bindView(view: ICharactersView) {
            val character = characters[view.pos]
            currentPosition = view.pos
            view.setCharacter(character)
        }

        override fun getCount(): Int = characters.size

        fun loadData(page: String?) {
            viewState.showLoadingProgress()
            api.getCharacters(page)
                .observeOn(uiScheduler)
                .subscribeOn(Schedulers.io())
                .subscribe({ call ->
                    info = call.info
                    characters.addAll(call.results)
                    lastPosition = characters.lastIndex
                    viewState.updateRv()
                    viewState.hideLoadingProgress()
                }, {
                    it.printStackTrace()
                }).disposeOnDestroy()
        }

        fun loadData() {
            viewState.showLoadingProgress()
            api.getCharacters(null)
                .observeOn(uiScheduler)
                .subscribeOn(Schedulers.io())
                .subscribe({ call ->
                    info = call.info
                    characters.addAll(call.results)
                    lastPosition = characters.lastIndex
                    viewState.updateRv()
                    viewState.hideLoadingProgress()
                }, {
                    it.printStackTrace()
                })
        }
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initRv()
        viewState.loadCharacters()
        viewState.setItemClickListener()
    }

    fun characterClick(){
        charactersPresenter.itemClickListener = {
            router.navigateTo(screens.characterInfoScreen(charactersPresenter.characters[it.pos]))
        }
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }

}