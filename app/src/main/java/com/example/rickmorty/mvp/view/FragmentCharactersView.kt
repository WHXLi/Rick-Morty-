package com.example.rickmorty.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface FragmentCharactersView: MvpView {
    fun loadCharacters()
    fun initRv()
    fun updateRv()
    fun showLoadingProgress()
    fun hideLoadingProgress()
}