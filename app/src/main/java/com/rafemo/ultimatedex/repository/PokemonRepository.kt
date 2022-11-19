package com.rafemo.ultimatedex.repository

import com.rafemo.ultimatedex.data.remote.response.Pokemon
import com.rafemo.ultimatedex.data.remote.response.PokemonList
import com.rafemo.ultimatedex.util.Resource

interface PokemonRepository {

    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList>
    suspend fun getPokemonDetail(pokemonName: String): Resource<Pokemon>

}