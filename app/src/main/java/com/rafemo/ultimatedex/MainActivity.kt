package com.rafemo.ultimatedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rafemo.ultimatedex.detail.components.PokemonDetailScreen
import com.rafemo.ultimatedex.pokemonlist.components.PokemonListScreen
import com.rafemo.ultimatedex.ui.theme.JetpackComposePokedexTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val argPokemonName = "pokemonName"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposePokedexTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "pokemon_list_screen") {
                    composable("pokemon_list_screen") {
                        PokemonListScreen(navController = navController)
                    }
                    composable(
                        "pokemon_detail_screen/{$argPokemonName}",
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