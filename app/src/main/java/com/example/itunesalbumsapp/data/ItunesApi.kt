package com.example.itunesalbumsapp.data

import com.example.itunesalbumsapp.data.enteties.AlbumResponse
import com.example.itunesalbumsapp.data.enteties.SongResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ItunesApi {
    @GET("search")
    suspend fun searchAlbum(
        @Query("term", encoded = true) term: String,
        @Query("media") media: String = "music",
        @Query("entity") entity: String = "album",
        @Query("attribute") attribute: String = "albumTerm"
    ): AlbumResponse

    @GET("search")
    suspend fun searchSongs(
        @Query("term", encoded = true) term: String,
        @Query("media") media: String = "music",
        @Query("entity") entity: String = "song",
        @Query("attribute") attribute: String = "albumTerm"
    ): SongResponse
}