package com.example.itunesalbumsapp.presentation.home_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import com.example.itunesalbumsapp.R
import com.example.itunesalbumsapp.presentation.view_items.AlbumItemView
import com.example.itunesalbumsapp.presentation.utils.LayoutsHelper.DEFAULT_MARGIN
import com.example.itunesalbumsapp.presentation.utils.LayoutsHelper.LITTLE_MARGIN
import com.example.itunesalbumsapp.presentation.home_screen.HomeScreenContract.State.*
import com.example.itunesalbumsapp.presentation.navigation.Screen
import kotlinx.coroutines.flow.collect

@ExperimentalMaterialApi
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeScreenViewModel
) {
    LaunchedEffect(LAUNCH_KEY) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is HomeScreenContract.Effect.Navigation.ToAlbumDetails -> {
                    navController.navigate(
                        route = Screen.Detail.passData(
                            effect.albumId,
                            effect.albumName
                        )
                    )
                }
            }
        }
    }
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (textField, button, bottomPart) = createRefs()
        var searchParameter by remember { mutableStateOf("") }

        OutlinedTextField(
            value = searchParameter,
            onValueChange = { searchParameter = it },
            modifier = Modifier
                .wrapContentHeight()
                .constrainAs(textField) {
                    top.linkTo(parent.top, margin = DEFAULT_MARGIN)
                    start.linkTo(parent.start, margin = DEFAULT_MARGIN)
                    end.linkTo(button.start, margin = LITTLE_MARGIN)
                    bottom.linkTo(bottomPart.top, margin = DEFAULT_MARGIN)
                    width = Dimension.fillToConstraints
                },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                cursorColor = Color.DarkGray,
                focusedBorderColor = Color.DarkGray,
            ),
            maxLines = 1
        )
        Button(
            onClick = {
                viewModel.setEvent(HomeScreenContract.Event.SearchAlbum(searchParameter))
            },
            modifier = Modifier
                .wrapContentSize()
                .constrainAs(button) {
                    top.linkTo(textField.top)
                    end.linkTo(parent.end, margin = DEFAULT_MARGIN)
                    bottom.linkTo(textField.bottom)
                    start.linkTo(textField.end, margin = LITTLE_MARGIN)
                },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)
        )
        {
            Text(text = stringResource(R.string.search), maxLines = 1)
        }
        val viewState = viewModel.viewState.collectAsState().value

        Box(
            modifier = Modifier
                .constrainAs(bottomPart) {
                    top.linkTo(textField.bottom)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(textField.start)
                    end.linkTo(button.end)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                },
            contentAlignment = Alignment.Center
        ) {
            when (viewState) {
                is Start -> {
                    Text(text = stringResource(R.string.start_text))
                }
                is Empty -> {
                    Text(text = stringResource(R.string.no_albums))
                }
                is Loading -> {
                    CircularProgressIndicator(color = Color.LightGray)
                }
                is Successful -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(DEFAULT_MARGIN)
                    ) {
                        items(
                            items = viewState.albums
                        ) { album ->
                            AlbumItemView(album = album) {
                                viewModel.setEvent(
                                    HomeScreenContract.Event.AlbumSelection(
                                        album.id,
                                        album.name
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

const val LAUNCH_KEY = "key_for_home_screen"