package com.rafemo.ultimatedex.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rafemo.ultimatedex.ui.detail.components.PokemonDetailScreen
import com.rafemo.ultimatedex.ui.pokemonlist.components.PokemonListScreen
import com.rafemo.ultimatedex.ui.splash.SplashScreen
import com.rafemo.ultimatedex.util.Screens
import java.util.*

@Composable
fun Navigation() {

    val argPokemonName = "pokemonName"
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.SplashScreen) {
        composable(
            route = Screens.SplashScreen
        ) {
            SplashScreen(navController = navController)
        }
        composable(
            route = Screens.PokemonListScreen) {
            PokemonListScreen(navController = navController)
        }
        composable(
            route = "${Screens.PokemonDetailScreen}/{$argPokemonName}",
            arguments = listOf(
                navArgument(argPokemonName) {
                    type = NavType.StringType
                }
            )
        ) {
            val pokemonName = remember {
                it.arguments?.getString(argPokemonName)
            }
            PokemonDetailScreen(
                pokemonName = pokemonName?.lowercase(Locale.ROOT) ?: "",
                navController = navController
            )
        }
    }

}