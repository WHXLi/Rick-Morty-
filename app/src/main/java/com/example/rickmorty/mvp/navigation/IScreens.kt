package com.example.rickmorty.mvp.navigation

import com.example.rickmorty.framework.entity.Character
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun charactersListScreen() : Screen
    fun characterInfoScreen(character: Character): Screen
}