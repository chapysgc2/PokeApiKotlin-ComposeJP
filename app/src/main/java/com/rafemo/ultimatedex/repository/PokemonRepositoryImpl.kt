package com.rafemo.ultimatedex.repository

import com.rafemo.ultimatedex.data.remote.PokeAPI
import com.rafemo.ultimatedex.data.remote.response.Pokemon
import com.rafemo.ultimatedex.data.remote.response.PokemonList
import com.rafemo.ultimatedex.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PokemonRepositoryImpl @Inject constructor(
    private val api: PokeAPI
): PokemonRepository {

    override suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> {
        val response = try {
            api.getPokemonList(limit, offset)
        } catch (e: Exception) {
            return Resource.Error("An error occurred.")
        }
        return Resource.Success(response)
    }

    override suspend fun getPokemonDetail(pokemonName: String): Resource<Pokemon> {
        val response = try {
            api.getPokemonDetail(pokemonName)
        } catch (e: Exception) {
            return Resource.Error("An error occurred.")
        }
        return Resource.Success(response)
    }

}