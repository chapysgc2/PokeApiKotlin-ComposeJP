package com.rafemo.ultimatedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.rafemo.ultimatedex.ui.navigation.Navigation
import com.rafemo.ultimatedex.ui.theme.JetpackComposePokedexTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Compose/MIUI Bug found!
        // https://issuetracker.google.com/issues/227926002
        // Testing on 2 Xiaomi devices:
        // - Xiaomi Mi 8 Lite (Android 10)
        // - Xiaomi Mi 11T Pro (Android 12)
        // Compose doesn't start and doesn't set content on launch -> Pokemon list won't load.
        // Also, when the screen orientation changes, do the same problem. Doesn't load any content.
        // It seems adding a little delay make it work :)

        lifecycleScope.launch {
            delay(100)
            setContent {
                JetpackComposePokedexTheme {
                    Navigation()
                }
            }
        }
    }


}