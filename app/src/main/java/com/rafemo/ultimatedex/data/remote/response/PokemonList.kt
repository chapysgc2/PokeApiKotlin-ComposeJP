package com.rafemo.ultimatedex.data.remote.response

data class PokemonList(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<Result>
)