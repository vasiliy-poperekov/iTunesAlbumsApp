package com.example.itunesalbumsapp.presentation.detail_screen

import androidx.lifecycle.viewModelScope
import com.example.itunesalbumsapp.domain.enteties.Album
import com.example.itunesalbumsapp.domain.use_cases.GetAlbumByIdUseCase
import com.example.itunesalbumsapp.domain.use_cases.GetSongListByAlbumUseCase
import com.example.itunesalbumsapp.domain.use_cases.SearchSongListByAlbumUseCase
import com.example.itunesalbumsapp.presentation.BasicViewModel
import com.example.itunesalbumsapp.presentation.utils.NetworkingChecker
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailScreenViewModel(
    private val getAlbumByIdUseCase: GetAlbumByIdUseCase,
    private val getSongListByAlbumUseCase: GetSongListByAlbumUseCase,
    private val searchSongListByAlbumUseCase: SearchSongListByAlbumUseCase,
    private val networkingChecker: NetworkingChecker
) : BasicViewModel<DetailScreenContract.DetailEvent, DetailScreenContract.State, DetailScreenContract.DetailEffect>() {
    private val albumMutStateFlow = MutableStateFlow<Album?>(null)

    val albumFlow: StateFlow<Album?>
        get() = albumMutStateFlow

    fun getAlbumInfo(id: Int, albumName: String) {
        viewModelScope.launch {
            launch { albumMutStateFlow.emit(getAlbumByIdUseCase(id)) }
            launch {
                networkingChecker.observeNetworkChange()
                    .collect { isConnected ->
                        if (isConnected) launch { searchSongListByAlbumUseCase(id, albumName) }
                        launch {
                            getSongListByAlbumUseCase(id).collect { songsList ->
                                when {
                                    songsList?.isEmpty()
                                            ?: false && isConnected -> setState { DetailScreenContract.State.Empty }
                                    !songsList.isNullOrEmpty() -> setState {
                                        DetailScreenContract.State.SuccessfulReceivingSongs(
                                            songsList
                                        )
                                    }
                                }
                            }
                        }
                    }
            }
        }
    }

    override fun setInitialState(): DetailScreenContract.State = DetailScreenContract.State.Start

    override fun handleEvents(event: DetailScreenContract.DetailEvent) {}
}