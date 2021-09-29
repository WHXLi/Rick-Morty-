package com.example.rickmorty.mvp.presenter

import com.example.rickmorty.framework.entity.Character
import com.example.rickmorty.framework.repository.LocationResponse
import com.example.rickmorty.framework.repository.RickAndMortyApi
import com.example.rickmorty.mvp.navigation.IScreens
import com.example.rickmorty.mvp.view.FragmentCharacterView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class FragmentCharacterPresenter(private val character: Character) :
    BaseMvpPresenter<FragmentCharacterView>() {

    @Inject
    lateinit var api: RickAndMortyApi

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    @Inject
    lateinit var uiScheduler: Scheduler

    private var location: LocationResponse? = null

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.loadData()
        viewState.setBackBtnClickListener()
    }

    fun loadLocation() {
        viewState.showLoadingProgress()
        api.getLocation(character.id.toString())
            .observeOn(uiScheduler)
            .subscribeOn(Schedulers.io())
            .subscribe({ call ->
                location = call
                viewState.hideLoadingProgress()
                viewState.displayInfo(character, location!!)
            }, {
                it.printStackTrace()
            }).disposeOnDestroy()
    }

    fun backBtnClick(){
        router.exit()
    }

    fun backClick(): Boolean{
        router.exit()
        return true
    }

}