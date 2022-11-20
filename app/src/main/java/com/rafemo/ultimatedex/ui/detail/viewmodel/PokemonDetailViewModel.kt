package com.rafemo.ultimatedex.ui.detail.viewmodel

import androidx.lifecycle.ViewModel
import com.rafemo.ultimatedex.data.remote.response.Pokemon
import com.rafemo.ultimatedex.repository.PokemonRepositoryImpl
import com.rafemo.ultimatedex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val repository: PokemonRepositoryImpl
): ViewModel() {

    suspend fun getPokemonDetail(pokemonName: String): Resource<Pokemon> {
        return repository.getPokemonDetail(pokemonName)
    }

}