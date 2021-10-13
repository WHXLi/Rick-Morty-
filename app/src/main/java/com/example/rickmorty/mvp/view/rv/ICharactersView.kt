package com.example.rickmorty.mvp.view.rv

import com.example.rickmorty.mvp.model.Character

interface ICharactersView: IRvView {
    fun setCharacter(character: Character)
}