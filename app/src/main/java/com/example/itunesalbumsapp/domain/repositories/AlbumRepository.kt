package com.example.itunesalbumsapp.domain.repositories

import com.example.itunesalbumsapp.domain.enteties.Album
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {
    suspend fun search(parameter: String)
    suspend fun get(id: Int): Album?
    fun getList(): Flow<List<Album>?>
}