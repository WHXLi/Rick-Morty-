package com.example.rickmorty.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface ActivityMainView: MvpView {
    fun showFragmentCharacters()
}