package com.example.rickmorty.mvp.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class Link(
    @Expose val name:String,
    @Expose val url: String
) : Parcelable