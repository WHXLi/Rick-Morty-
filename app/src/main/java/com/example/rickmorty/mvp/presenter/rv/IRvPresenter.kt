package com.example.rickmorty.mvp.presenter.rv

interface IRvPresenter<V> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}