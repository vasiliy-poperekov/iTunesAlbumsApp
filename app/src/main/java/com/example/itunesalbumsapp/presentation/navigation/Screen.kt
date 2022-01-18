package com.example.itunesalbumsapp.presentation.navigation

sealed class Screen(val route: String) {
    object Home : Screen(route = HOME_ROUTE)
    object Detail : Screen(route = "detail_screen/{$ALBUM_ID}/{$ALBUM_NAME}") {
        fun passData(albumId: Int, albumName: String): String {
            return "detail_screen/$albumId/${albumName.replace("/", "*")}"
        }
    }
}

const val ALBUM_ID = "id"
const val ALBUM_NAME = "album_name"
const val HOME_ROUTE = "home_screen"