package com.example.rickmorty.mvp.model

import com.google.gson.annotations.Expose

data class Info(
    @Expose val count: Int,
    @Expose val pages: Int,
    @Expose val next: String,
    @Expose val prev: String

)