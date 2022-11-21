package com.rafemo.ultimatedex.ui.detail.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rafemo.ultimatedex.data.remote.response.Pokemon
import java.util.*


@Composable
fun PokemonDetailSection(
    pokemonDetail: Pokemon,
    modifier: Modifier = Modifier
) {

    val scrollState = rememberScrollState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .offset(y = 100.dp)
            .verticalScroll(scrollState)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "#${pokemonDetail.id} ${
                pokemonDetail.name.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString()
                }
            }",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onSurface
        )

        PokemonTypeSection(types = pokemonDetail.types)

        Spacer(modifier = Modifier.height(16.dp))

        PokemonDetailDataSection(
            pokemonWeight = pokemonDetail.weight,
            pokemonHeight = pokemonDetail.height
        )

        Spacer(modifier = Modifier.height(16.dp))

        // TODO:
        //  BUG (or maybe I'm missing something)??? -> String resource not found
        //  stringResource(id = R.string.detail_base_stats_title)
        //  LocalContext.current.getString(R.string.detail_base_stats_title)
        //  LocalContext.current.resources.getText(R.string.detail_base_stats_title).toString()
        //  android.content.res.Resources$NotFoundException: String resource ID #0x7f0f00a0
        Text(
            text = "Base Stats",
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onSurface,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        PokemonBaseStats(pokemonDetail = pokemonDetail)

        // Set space equal to the column offset, it handles screen orientation
        Spacer(modifier = Modifier.height(100.dp))
    }
}