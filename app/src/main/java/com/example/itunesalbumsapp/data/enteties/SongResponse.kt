package com.example.itunesalbumsapp.data.enteties

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SongResponse(
    val results: List<SongResult>
)