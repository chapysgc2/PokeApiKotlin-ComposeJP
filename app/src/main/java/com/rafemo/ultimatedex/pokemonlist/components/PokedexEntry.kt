package com.rafemo.ultimatedex.pokemonlist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.rafemo.ultimatedex.data.models.PokedexListEntry
import com.rafemo.ultimatedex.pokemonlist.viewmodel.PokemonListViewModel
import com.rafemo.ultimatedex.ui.theme.Roboto

@Composable
fun PokedexEntry(
    entry: PokedexListEntry,
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: PokemonListViewModel = hiltViewModel()
) {

    val roundedCornerSize = 16.dp

//    Card(
//        modifier = Modifier
//            .border(
//                1.dp,
//                color = MaterialTheme.colors.primary,
//                shape = RoundedCornerShape(roundedCornerSize)
//            )
//            .shadow(5.dp, RoundedCornerShape(roundedCornerSize)),
//        shape = RoundedCornerShape(roundedCornerSize),
//    ) {
//        Column(
//            modifier = Modifier.padding(16.dp, 8.dp, 16.dp, 8.dp)
//        ) {
//            Spacer(modifier = Modifier.width(16.dp))
//            AsyncImage(
//                model = ImageRequest.Builder(LocalContext.current)
//                    .data(entry.imageUrl)
//                    .crossfade(true)
//                    .build(),
//                contentDescription = entry.pokemonName,
//                modifier = Modifier
//                    .size(100.dp)
//            )
//            Text(
//                text = "#${entry.number} ${entry.pokemonName}",
//                fontFamily = Roboto,
//                fontWeight = FontWeight.Bold,
//                fontSize = 16.sp,
//                modifier = Modifier.fillMaxWidth()
//            )
//            Spacer(modifier = Modifier.width(16.dp))
//        }
//    }

    Card (
        modifier = modifier
            .background(
                color = MaterialTheme.colors.background,
                shape = RoundedCornerShape(roundedCornerSize)
            )
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(roundedCornerSize))
            .clickable {
                navController.navigate(
                    "pokemon_detail_screen/${entry.pokemonName}"
                )
            }
    ) {
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colors.background)
        ) {
            val topAndBottomMargin = 12.dp
            Spacer(modifier = Modifier.height(topAndBottomMargin))
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(entry.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = entry.pokemonName,
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = "#${entry.number} ${entry.pokemonName}",
                fontFamily = Roboto,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp, 0.dp, 8.dp, 0.dp)
            )
            Spacer(modifier = Modifier.height(topAndBottomMargin))
        }
    }
}