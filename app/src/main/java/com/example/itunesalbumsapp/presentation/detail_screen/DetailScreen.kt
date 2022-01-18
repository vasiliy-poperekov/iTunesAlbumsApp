package com.example.itunesalbumsapp.presentation.detail_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.itunesalbumsapp.R
import com.example.itunesalbumsapp.presentation.utils.LayoutsHelper.DEFAULT_MARGIN
import com.example.itunesalbumsapp.presentation.utils.LayoutsHelper.LITTLE_MARGIN
import com.example.itunesalbumsapp.presentation.view_items.SongItemView
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun DetailScreen(
    albumId: Int,
    albumName: String,
    viewModel: DetailScreenViewModel
) {
    LaunchedEffect(LAUNCH_KEY) {
        viewModel.getAlbumInfo(albumId, albumName)
    }
    val album = viewModel.albumFlow.collectAsState().value
    val state = viewModel.viewState.collectAsState().value
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GlideImage(
            imageModel = album?.coverUrl,
            modifier = Modifier.size(160.dp)
        )

        Spacer(modifier = Modifier.size(DEFAULT_MARGIN))

        Text(
            text = album?.name ?: "",
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.size(DEFAULT_MARGIN))

        Text(
            text = album?.artist ?: "",
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.size(DEFAULT_MARGIN))

        Text(text = stringResource(R.string.genre) + album?.genre)

        Spacer(modifier = Modifier.size(DEFAULT_MARGIN))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = stringResource(R.string.release_date) + "\n" + album?.releaseDate,
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(R.string.track_count) + "\n" + album?.trackCount.toString(),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.size(DEFAULT_MARGIN))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp)
                .padding(LITTLE_MARGIN),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (state) {
                is DetailScreenContract.State.Start -> {
                    CircularProgressIndicator(color = Color.LightGray)
                }
                is DetailScreenContract.State.Empty -> {
                    Text(text = stringResource(R.string.no_songs))
                }
                is DetailScreenContract.State.SuccessfulReceivingSongs -> {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(LITTLE_MARGIN),
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        items(state.songs) { song ->
                            SongItemView(song)
                        }
                    }
                }
            }
        }

    }
}

const val LAUNCH_KEY = "key_for_detail_screen"