package com.base.app.intent

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.base.app.presentation.film_detail.FilmDetailScreen
import com.base.app.presentation.film_favorites.FilmFavoriteListScreen
import com.base.app.presentation.home.FilmSearchTypeUiModel
import com.base.app.presentation.home.HomeScreen

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination
    ) {
        /** HomeScreen */
        composable<HomeDestination> {
            val navigator = remember(navController) {
                object : HomeNavigator {
                    override fun navigateToDetail(
                        query: String,
                        searchType: FilmSearchTypeUiModel
                    ) {
                        navController.navigate(FilmDetailDestination(query, searchType))
                    }
                    override fun navigateToFavorites() {
                        navController.navigate(FilmFavoritesDestination)
                    }
                }
            }
            HomeScreen(navigator = navigator)
        }

        /** FilmDetailScreen */
        composable<FilmDetailDestination> {
            val args = it.toRoute<FilmDetailDestination>()
            FilmDetailScreen(
                parameter = args.parameter,
                searchType = args.searchType,
                onBack = { navController.popBackStack() }
            )
        }

        /** FilmFavoritesScreen */
        composable<FilmFavoritesDestination> {
            FilmFavoriteListScreen(
                onNavigateToDetail = { id, searchType ->
                    navController.navigate(FilmDetailDestination(id, searchType))
                },
                onBack = { navController.popBackStack() }
            )
        }
    }
}