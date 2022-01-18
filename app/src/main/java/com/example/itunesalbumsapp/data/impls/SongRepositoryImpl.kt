package com.example.itunesalbumsapp.data.impls

import com.example.itunesalbumsapp.data.ItunesApi
import com.example.itunesalbumsapp.data.daos.SongDao
import com.example.itunesalbumsapp.data.enteties.SongEntity
import com.example.itunesalbumsapp.domain.enteties.Song
import com.example.itunesalbumsapp.domain.repositories.SongRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SongRepositoryImpl(
    private val itunesApi: ItunesApi,
    private val songDao: SongDao
) : SongRepository {
    override fun getListByAlbum(albumId: Int): Flow<List<Song>?> =
        songDao.getSongsList(albumId).map { songList ->
            songList?.map { songEntity ->
                Song(
                    collectionId = songEntity.collectionId,
                    id = songEntity.trackId,
                    name = songEntity.trackName
                )
            }
        }

    override suspend fun searchListByAlbum(albumId: Int, albumName: String) {
        songDao.addSongsList(itunesApi.searchSongs(
            albumName.trim().replace(" ", "+")
        ).results
            .filter { it.collectionId == albumId }
            .map { songResult ->
                SongEntity(
                    collectionId = songResult.collectionId,
                    trackId = songResult.trackId,
                    trackName = songResult.trackName
                )
            })
    }
}