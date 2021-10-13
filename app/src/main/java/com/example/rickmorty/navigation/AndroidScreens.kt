package com.example.rickmorty.navigation

import com.example.rickmorty.mvp.model.Character
import com.example.rickmorty.ui.fragment.FragmentCharacter
import com.example.rickmorty.ui.fragment.FragmentCharacters
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun charactersListScreen() = FragmentScreen { FragmentCharacters.instance() }
    override fun characterInfoScreen(character: Character) = FragmentScreen { FragmentCharacter.instance(character)}
}