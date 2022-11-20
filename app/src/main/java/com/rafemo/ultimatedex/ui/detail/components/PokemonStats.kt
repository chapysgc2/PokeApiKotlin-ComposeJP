package com.rafemo.ultimatedex.ui.detail.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rafemo.ultimatedex.data.remote.response.Pokemon
import com.rafemo.ultimatedex.ui.theme.Roboto
import com.rafemo.ultimatedex.util.parseStatToAbbr
import com.rafemo.ultimatedex.util.parseStatToColor

@Composable
fun PokemonStats(
    statName: String,
    statValue: Int,
    statMaxValue: Int,
    statColor: Color,
    height: Dp = 20.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0
) {
    var animationPlayed by remember {
        mutableStateOf(false)
    }
    val currentPercent = animateFloatAsState(
        targetValue = if (animationPlayed) {
            statValue / statMaxValue.toFloat()
        } else {
            0f
        },
        animationSpec = tween(
            animDuration,
            animDelay
        )
    )

    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }

    Row(modifier = Modifier
        .height(height)
    ) {
        Text(
            text = statName,
            fontFamily = Roboto,
            color = MaterialTheme.colors.onSurface,
            modifier = Modifier.fillMaxWidth(0.2f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = (currentPercent.value * statMaxValue).toInt().toString(),
            fontFamily = Roboto,
            color = MaterialTheme.colors.onSurface,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))

        val padding = (height/3.dp).dp

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = padding, bottom = padding)
                .clip(CircleShape)
                .background(
                    Color(0xFF505050)
                )
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(currentPercent.value)
                    .clip(CircleShape)
                    .background(statColor)
                    .padding(horizontal = 8.dp)
            ) {

            }
        }
    }

}

@Composable
fun PokemonBaseStats(
    pokemonDetail: Pokemon,
    animDelayPerItem: Int = 100
) {
    val maxBaseStat = remember {
        pokemonDetail.stats.maxOf { it.base_stat }
    }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(4.dp))

        for (i in pokemonDetail.stats.indices) {
            val stat = pokemonDetail.stats[i]
            PokemonStats(
                statName = parseStatToAbbr(stat),
                statValue = stat.base_stat,
                statMaxValue = maxBaseStat,
                statColor = parseStatToColor(stat),
                animDelay = i * animDelayPerItem
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }

}