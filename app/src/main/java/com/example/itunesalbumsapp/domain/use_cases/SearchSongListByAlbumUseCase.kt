package com.example.itunesalbumsapp.domain.use_cases

import com.example.itunesalbumsapp.domain.repositories.SongRepository

class SearchSongListByAlbumUseCase(
    private val songRepository: SongRepository
) {
    suspend operator fun invoke(albumId: Int, albumName: String) =
        songRepository.searchListByAlbum(albumId, albumName)
}