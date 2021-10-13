package com.example.rickmorty.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

@AddToEndSingle
interface FragmentCharactersView: MvpView {
    @OneExecution
    fun loadCharacters()
    fun initRv()
    fun updateRv()
    fun setItemClickListener()
    fun showLoadingProgress()
    fun hideLoadingProgress()
}