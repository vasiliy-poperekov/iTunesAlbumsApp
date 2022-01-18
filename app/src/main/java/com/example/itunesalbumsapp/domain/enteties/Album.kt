package com.example.itunesalbumsapp.domain.enteties

data class Album(
    val id: Int,
    val name: String,
    val artist: String,
    val coverUrl: String,
    val releaseDate: String,
    val genre: String,
    val trackCount: Int
)
