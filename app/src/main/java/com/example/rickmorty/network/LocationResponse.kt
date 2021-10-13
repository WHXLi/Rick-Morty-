package com.example.rickmorty.network

import com.google.gson.annotations.Expose

data class LocationResponse(
    @Expose val id: Int,
    @Expose val name: String,
    @Expose val type: String,
    @Expose val dimension: String,
    @Expose val residents: List<String>,
    @Expose val url: String,
    @Expose val created: String
)

