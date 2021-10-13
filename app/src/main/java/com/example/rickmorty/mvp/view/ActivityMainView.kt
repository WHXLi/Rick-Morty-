package com.example.rickmorty.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

@OneExecution
interface ActivityMainView: MvpView {
    fun showFragmentCharacters()
}