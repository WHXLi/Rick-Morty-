package com.example.rickmorty.mvp.presenter

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import moxy.MvpView

open class BaseMvpPresenter<V : MvpView> : MvpPresenter<V>() {
    private val compositeDisposable = CompositeDisposable()
    protected fun Disposable.disposeOnDestroy() {
        compositeDisposable.add(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}