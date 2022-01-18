package com.example.itunesalbumsapp.data.enteties

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AlbumResponse(
    val results: List<AlbumResult>
)