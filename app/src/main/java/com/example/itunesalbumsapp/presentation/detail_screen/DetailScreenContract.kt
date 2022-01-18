package com.example.itunesalbumsapp.presentation.detail_screen

import com.example.itunesalbumsapp.domain.enteties.Song
import com.example.itunesalbumsapp.presentation.ViewEvent
import com.example.itunesalbumsapp.presentation.ViewSideEffect
import com.example.itunesalbumsapp.presentation.ViewState

class DetailScreenContract {
    class DetailEvent: ViewEvent

    class DetailEffect: ViewSideEffect

    sealed class State : ViewState {
        object Start : State()
        data class SuccessfulReceivingSongs(val songs: List<Song>) : State()
        object Empty : State()
    }
}