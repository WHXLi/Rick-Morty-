package com.example.rickmorty.ui.fragment

import android.os.Bundle
import com.example.rickmorty.R
import com.example.rickmorty.framework.entity.Character
import com.example.rickmorty.mvp.presenter.FragmentCharacterPresenter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class FragmentCharacter : MvpAppCompatFragment(R.layout.fragment_character) {
    companion object {
        private const val TAG = "character"
        fun instance(character: Character) = FragmentCharacter().apply {
            arguments = Bundle().apply {
                putParcelable(TAG, character)
            }
        }
    }

    private val presenter: FragmentCharacterPresenter by moxyPresenter {
        val character = arguments?.getParcelable<Character>(TAG) as Character
        FragmentCharacterPresenter(character)
    }


}