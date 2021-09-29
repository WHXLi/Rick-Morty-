package com.example.rickmorty.framework.repository

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface RickAndMortyApi {
    @GET("character")
    fun getCharacters() : Single<CharactersResponse>
}