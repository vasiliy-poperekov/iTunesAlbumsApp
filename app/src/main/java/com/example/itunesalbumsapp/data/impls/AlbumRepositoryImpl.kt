package com.example.itunesalbumsapp.data.impls

import com.example.itunesalbumsapp.data.ItunesApi
import com.example.itunesalbumsapp.data.daos.AlbumDao
import com.example.itunesalbumsapp.data.enteties.AlbumEntity
import com.example.itunesalbumsapp.domain.enteties.Album
import com.example.itunesalbumsapp.domain.repositories.AlbumRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AlbumRepositoryImpl(
    private val itunesApi: ItunesApi,
    private val albumDao: AlbumDao
) : AlbumRepository {
    override suspend fun search(parameter: String) {
        albumDao.deleteAlbumsList()
        albumDao.addAlbumsList(
            itunesApi.searchAlbum(
                term = parameter.trim().replace(" ", "+")
            ).results.map { albumResponse ->
                AlbumEntity(
                    id = albumResponse.collectionId,
                    name = albumResponse.collectionName,
                    artist = albumResponse.artistName,
                    coverUrl = albumResponse.artworkUrl100,
                    releaseDate = albumResponse.releaseDate.takeWhile { it != 'T' },
                    genre = albumResponse.primaryGenreName,
                    trackCount = albumResponse.trackCount
                )
            }
        )
    }

    override suspend fun get(id: Int): Album? {
        val albumEntity = albumDao.getAlbumById(id)
        return if (albumEntity == null) null
        else toAlbum(albumEntity)
    }

    override fun getList(): Flow<List<Album>?> = albumDao.getAlbumsList().map { albumsList ->
        albumsList?.map { albumEntity ->
            toAlbum(albumEntity)
        }
    }

    private fun toAlbum(albumEntity: AlbumEntity) =
        Album(
            id = albumEntity.id,
            name = albumEntity.name,
            artist = albumEntity.artist,
            coverUrl = albumEntity.coverUrl,
            releaseDate = albumEntity.releaseDate,
            genre = albumEntity.genre,
            trackCount = albumEntity.trackCount
        )
}