package com.example.itunesalbumsapp.domain.repositories

import com.example.itunesalbumsapp.domain.enteties.Song
import kotlinx.coroutines.flow.Flow

interface SongRepository {
    fun getListByAlbum(albumId: Int): Flow<List<Song>?>
    suspend fun searchListByAlbum(albumId: Int, albumName: String)
}