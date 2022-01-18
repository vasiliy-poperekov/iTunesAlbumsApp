package com.example.itunesalbumsapp.data.enteties

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AlbumResult(
    val collectionId: Int,
    val artistName: String,
    val collectionName: String,
    val artworkUrl100: String,
    val trackCount: Int,
    val releaseDate: String,
    val primaryGenreName: String
)