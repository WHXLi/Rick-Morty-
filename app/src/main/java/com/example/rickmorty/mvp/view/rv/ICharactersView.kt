package com.example.rickmorty.mvp.view.rv

import com.example.rickmorty.framework.entity.Character

interface ICharactersView: IRvView {
    fun setCharacter(character: Character)
}