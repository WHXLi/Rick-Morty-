package com.example.rickmorty.mvp.view

import com.example.rickmorty.framework.entity.Character
import com.example.rickmorty.framework.repository.LocationResponse
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface FragmentCharacterView : MvpView {
    fun loadData()
    fun displayInfo(character: Character, location: LocationResponse)
    fun setBackBtnClickListener()
    fun showLoadingProgress()
    fun hideLoadingProgress()
}