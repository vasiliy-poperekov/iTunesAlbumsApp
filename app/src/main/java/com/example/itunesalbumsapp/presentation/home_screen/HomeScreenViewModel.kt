package com.example.itunesalbumsapp.presentation.home_screen

import androidx.lifecycle.viewModelScope
import com.example.itunesalbumsapp.domain.use_cases.GetAlbumListUseCase
import com.example.itunesalbumsapp.domain.use_cases.SearchAlbumListUseCase
import com.example.itunesalbumsapp.presentation.BasicViewModel
import com.example.itunesalbumsapp.presentation.utils.NetworkingChecker
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val searchAlbumListUseCase: SearchAlbumListUseCase,
    private val getAlbumListUseCase: GetAlbumListUseCase,
    private val networkingChecker: NetworkingChecker
) : BasicViewModel<HomeScreenContract.Event, HomeScreenContract.State, HomeScreenContract.Effect>() {

    override fun setInitialState() = HomeScreenContract.State.Start

    override fun handleEvents(event: HomeScreenContract.Event) {
        when (event) {
            is HomeScreenContract.Event.AlbumSelection -> {
                setEffect {
                    HomeScreenContract.Effect.Navigation.ToAlbumDetails(
                        event.albumId,
                        event.albumName
                    )
                }
            }
            is HomeScreenContract.Event.SearchAlbum -> {
                setState { HomeScreenContract.State.Loading }
                viewModelScope.launch {
                    launch {
                        networkingChecker.observeNetworkChange().collect { isConnected ->
                            if (isConnected) {
                                searchAlbumListUseCase(event.parameter)
                                getAlbumsList()
                            }
                        }
                    }
                }
            }
        }
    }

    private suspend fun getAlbumsList() {
        getAlbumListUseCase()
            .collect { albumsList ->
                when {
                    albumsList?.isEmpty() ?: false -> setState { HomeScreenContract.State.Empty }
                    !albumsList.isNullOrEmpty() -> setState {
                        HomeScreenContract.State.Successful(
                            albumsList.toMutableList().sortedBy { album -> album.name })
                    }
                }
            }
    }
}