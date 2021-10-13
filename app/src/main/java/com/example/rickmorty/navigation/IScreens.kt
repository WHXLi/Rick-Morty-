package com.example.rickmorty.navigation

import com.example.rickmorty.mvp.model.Character
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun charactersListScreen() : Screen
    fun characterInfoScreen(character: Character): Screen
}