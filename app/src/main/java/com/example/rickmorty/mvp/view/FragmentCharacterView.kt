package com.example.rickmorty.mvp.view

import com.example.rickmorty.mvp.model.Character
import com.example.rickmorty.network.LocationResponse
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

@AddToEndSingle
interface FragmentCharacterView : MvpView {
    @OneExecution
    fun loadData()
    fun displayInfo(character: Character, location: LocationResponse)
    fun setBackBtnClickListener()
    fun showLoadingProgress()
    fun hideLoadingProgress()
}