package org.kmp.joke

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kmp.joke.ui.screen.JokeScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        JokeScreen()
    }
}