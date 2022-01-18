package com.example.itunesalbumsapp.domain.use_cases

import com.example.itunesalbumsapp.domain.repositories.AlbumRepository

class GetAlbumByIdUseCase(
    private val albumRepository: AlbumRepository
) {
    suspend operator fun invoke(id: Int) = albumRepository.get(id)
}