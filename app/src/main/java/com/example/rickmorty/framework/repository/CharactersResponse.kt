package com.example.rickmorty.framework.repository

import com.example.rickmorty.framework.entity.Info
import com.example.rickmorty.framework.entity.Character
import com.google.gson.annotations.Expose

data class CharactersResponse(
    @Expose val info: Info,
    @Expose val results: List<Character>
)