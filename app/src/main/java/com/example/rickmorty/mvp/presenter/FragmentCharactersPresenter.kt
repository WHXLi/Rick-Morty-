package com.example.rickmorty.mvp.presenter

import com.example.rickmorty.framework.entity.Character
import com.example.rickmorty.framework.entity.Info
import com.example.rickmorty.framework.repository.RickAndMortyApi
import com.example.rickmorty.mvp.navigation.IScreens
import com.example.rickmorty.mvp.presenter.rv.ICharactersPresenter
import com.example.rickmorty.mvp.view.FragmentCharactersView
import com.example.rickmorty.mvp.view.rv.ICharactersView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class FragmentCharactersPresenter : BaseMvpPresenter<FragmentCharactersView>() {
    class CharactersPresenter : ICharactersPresenter {
        var info: Info? = null
        var currentPosition: Int = 0
        val characters = mutableListOf<Character>()
        override var itemClickListener: ((ICharactersView) -> Unit)? = null

        override fun bindView(view: ICharactersView) {
            val character = characters[view.pos]
            view.setCharacter(character)
        }

        override fun getCount(): Int = characters.size
    }

    @Inject
    lateinit var api: RickAndMortyApi

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    @Inject
    lateinit var uiScheduler: Scheduler

    val charactersPresenter = CharactersPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initRv()
        viewState.loadCharacters()
        charactersPresenter.itemClickListener = {
            router.navigateTo(screens.characterInfoScreen(charactersPresenter.characters[it.pos]))
            charactersPresenter.currentPosition = it.pos
        }
    }

    fun loadData(page: String?) {
        viewState.showLoadingProgress()
        api.getCharacters(page)
            .observeOn(uiScheduler)
            .subscribeOn(Schedulers.io())
            .subscribe({ call ->
                charactersPresenter.info = call.info
                charactersPresenter.characters.addAll(call.results)
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
                charactersPresenter.info = call.info
                charactersPresenter.characters.addAll(call.results)
                viewState.updateRv()
                viewState.hideLoadingProgress()
            }, {
                it.printStackTrace()
            }).disposeOnDestroy()
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }

}