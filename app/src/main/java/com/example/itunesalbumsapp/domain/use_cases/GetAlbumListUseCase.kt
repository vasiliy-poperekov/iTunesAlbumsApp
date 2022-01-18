package com.example.itunesalbumsapp.domain.use_cases

import com.example.itunesalbumsapp.domain.repositories.AlbumRepository

class GetAlbumListUseCase(
    private val albumRepository: AlbumRepository
) {
    operator fun invoke() = albumRepository.getList()
}