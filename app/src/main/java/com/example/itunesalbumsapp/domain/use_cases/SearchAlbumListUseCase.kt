package com.example.itunesalbumsapp.domain.use_cases

import com.example.itunesalbumsapp.domain.repositories.AlbumRepository

class SearchAlbumListUseCase(
    private val albumRepository: AlbumRepository
) {
    suspend operator fun invoke(parameter: String) = albumRepository.search(parameter)
}