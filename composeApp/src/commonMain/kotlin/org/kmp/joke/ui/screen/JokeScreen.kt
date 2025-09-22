package org.kmp.joke.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kmp.joke.ui.JokeUiState
import org.kmp.joke.ui.JokeViewModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JokeScreen(viewModel: JokeViewModel = koinViewModel<JokeViewModel>()) {
    val jokeState by viewModel.uiState.collectAsStateWithLifecycle()
    JokeScreenContent(jokeState = jokeState, onReload = { viewModel.reloadJoke() })
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun JokeScreenContent(jokeState: JokeUiState, onReload: () -> Unit = {}) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("KMP Joke App") }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val (jokeText, textColor, isLoading) = when (jokeState) {
                is JokeUiState.Error -> Triple(jokeState.message, Color.Red, false)
                JokeUiState.Loading -> Triple("Loading...", Color.Unspecified, true)
                is JokeUiState.Success -> Triple(jokeState.joke, Color.Unspecified, false)
            }

            if (isLoading) {
                CircularProgressIndicator()
            } else {
                Text(
                    text = jokeText,
                    color = textColor
                )
                Button(
                    onClick = onReload,
                    modifier = Modifier.padding(top = 16.dp),
                    enabled = !isLoading
                ) {
                    Text("Reload")
                }
            }
        }
    }
}

@Preview
@Composable
private fun JokeScreenPreview() {
    JokeScreenContent(
        jokeState = JokeUiState.Success(
            "Why don't scientists trust atoms?",
        ),
        onReload = {}
    )
}

@Preview
@Composable
private fun JokeScreenErrorPreview() {
    JokeScreenContent(
        jokeState = JokeUiState.Error(
            "Network etc error",
        ),
        onReload = {}
    )
}