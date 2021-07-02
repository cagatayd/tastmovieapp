package com.cagatay.movieapp.model

data class Movies(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)