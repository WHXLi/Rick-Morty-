package com.example.rickmorty.ui.activity

import android.os.Bundle
import com.example.rickmorty.App
import com.example.rickmorty.ui.BackBtnClickListener
import com.example.rickmorty.R
import com.example.rickmorty.mvp.presenter.ActivityMainPresenter
import com.example.rickmorty.mvp.view.ActivityMainView
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class ActivityMain : MvpAppCompatActivity(R.layout.activity_main), ActivityMainView {
    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator = AppNavigator(this, R.id.container_fragments)
    private val presenter by moxyPresenter {
        ActivityMainPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.instance.appComponent.inject(this)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun showFragmentCharacters() {
        presenter.displayFragmentCharacters()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackBtnClickListener && it.backPressed()) {
                return
            }
        }
        presenter.backClick()
    }

}