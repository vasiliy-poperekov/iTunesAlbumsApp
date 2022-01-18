package com.example.itunesalbumsapp.presentation.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.itunesalbumsapp.presentation.detail_screen.DetailScreen
import com.example.itunesalbumsapp.presentation.home_screen.HomeScreen
import org.koin.androidx.compose.getViewModel

@ExperimentalMaterialApi
@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(navController = navController, viewModel = getViewModel())
        }

        composable(
            route = Screen.Detail.route,
            arguments = listOf(
                navArgument(ALBUM_ID) { type = NavType.IntType },
                navArgument(ALBUM_NAME) { type = NavType.StringType }
            )
        ) {
            DetailScreen(
                albumId = it.arguments?.getInt(ALBUM_ID) ?: 0,
                albumName = it.arguments?.getString(ALBUM_NAME)?.replace("*", "/") ?: "",
                viewModel = getViewModel()
            )
        }
    }
}