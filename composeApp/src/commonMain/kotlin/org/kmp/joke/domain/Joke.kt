package org.kmp.joke.domain

data class Joke(
    val error: Boolean,
    val id: Int,
    val joke: String,
)