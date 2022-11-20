package com.rafemo.ultimatedex.ui.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.rafemo.ultimatedex.R
import com.rafemo.ultimatedex.util.Screens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
) {

    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        // Execute once by setting key1 to true
        scale.animateTo(
            targetValue = 1.2f,
            animationSpec = tween(
                durationMillis = 500,
                easing = { fraction ->
                    OvershootInterpolator(2f)
                        .getInterpolation(fraction)
                }
            )
        )
        delay(1200)
        navController.navigate(Screens.PokemonListScreen) {
            popUpTo(Screens.SplashScreen) {
                inclusive = true
            }
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_512x512),
            contentDescription = "Logo",
            modifier = Modifier
                .scale(scale.value)
        )
    }
}