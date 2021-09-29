package com.example.rickmorty.mvp.presenter

import com.example.rickmorty.framework.entity.Character
import com.example.rickmorty.framework.entity.Info
import com.example.rickmorty.framework.repository.RickAndMortyApi
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
    lateinit var uiScheduler: Scheduler

    val charactersPresenter = CharactersPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.initRv()
        viewState.loadCharacters()
        charactersPresenter.itemClickListener = {
            // Переход на экран персонажа
        }
    }

    fun loadData() {
        viewState.showLoadingProgress()
        api.getCharacters()
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


}