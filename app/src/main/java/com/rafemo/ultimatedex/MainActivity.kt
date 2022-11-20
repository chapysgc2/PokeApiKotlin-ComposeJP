package com.rafemo.ultimatedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rafemo.ultimatedex.ui.detail.components.PokemonDetailScreen
import com.rafemo.ultimatedex.ui.pokemonlist.components.PokemonListScreen
import com.rafemo.ultimatedex.ui.pokemonlist.viewmodel.PokemonListViewModel
import com.rafemo.ultimatedex.ui.splash.SplashScreen
import com.rafemo.ultimatedex.ui.theme.JetpackComposePokedexTheme
import com.rafemo.ultimatedex.util.Screens
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<PokemonListViewModel>()
    private val argPokemonName = "pokemonName"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposePokedexTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Screens.SplashScreen) {
                    composable(Screens.SplashScreen) {
                        SplashScreen(navController = navController)
                    }
                    composable(Screens.PokemonListScreen) {
                        PokemonListScreen(navController = navController)
                    }
                    composable(
                        "${Screens.PokemonDetailScreen}/{$argPokemonName}",
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
        }
    }


}