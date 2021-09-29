package com.example.rickmorty.framework.repository

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {
    @GET("character")
    fun getCharacters(
        @Query("page") page: String?
    ): Single<CharactersResponse>

    @GET("location/{id}")
    fun getLocation(
        @Path("id") id: String
    ): Single<LocationResponse>
}