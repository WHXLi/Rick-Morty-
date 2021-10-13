package com.example.rickmorty.network

import com.example.rickmorty.mvp.model.Info
import com.example.rickmorty.mvp.model.Character
import com.google.gson.annotations.Expose

data class CharactersResponse(
    @Expose val info: Info,
    @Expose val results: List<Character>
)