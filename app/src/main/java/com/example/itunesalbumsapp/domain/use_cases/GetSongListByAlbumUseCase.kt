package com.example.itunesalbumsapp.domain.use_cases

import com.example.itunesalbumsapp.domain.repositories.SongRepository

class GetSongListByAlbumUseCase(
    private val songRepository: SongRepository
) {
    operator fun invoke(id: Int) = songRepository.getListByAlbum(id)
}