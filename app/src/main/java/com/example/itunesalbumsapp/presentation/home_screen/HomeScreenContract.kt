package com.example.itunesalbumsapp.presentation.home_screen

import com.example.itunesalbumsapp.domain.enteties.Album
import com.example.itunesalbumsapp.presentation.ViewEvent
import com.example.itunesalbumsapp.presentation.ViewSideEffect
import com.example.itunesalbumsapp.presentation.ViewState

class HomeScreenContract {
    sealed class Event : ViewEvent {
        data class SearchAlbum(val parameter: String) : Event()
        data class AlbumSelection(val albumId: Int, val albumName: String) : Event()
    }

    sealed class State : ViewState {
        object Start : State()
        object Loading : State()
        object Empty : State()
        data class Successful(val albums: List<Album>) : State()
    }

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            data class ToAlbumDetails(val albumId: Int, val albumName: String) : Navigation()
        }
    }
}