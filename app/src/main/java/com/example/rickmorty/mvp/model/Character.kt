package com.example.rickmorty.mvp.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class Character(
    @Expose val id: Int,
    @Expose val name: String,
    @Expose val status: String,
    @Expose val species: String,
    @Expose val type: String,
    @Expose val gender: String,
    @Expose val origin: Link,
    @Expose val location: Link,
    @Expose val image: String,
    @Expose val episode: List<String>,
    @Expose val url: String,
    @Expose val created: String,
) : Parcelable