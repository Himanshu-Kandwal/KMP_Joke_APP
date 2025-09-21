package org.kmp.joke.data

import kotlinx.serialization.Serializable
import org.kmp.joke.domain.Joke

@Serializable
data class JokeDto(
    val category: String,
    val error: Boolean,
    val flags: Flags,
    val id: Int,
    val joke: String,
    val lang: String,
    val safe: Boolean,
    val type: String
)

@Serializable
data class Flags(
    val explicit: Boolean,
    val nsfw: Boolean,
    val political: Boolean,
    val racist: Boolean,
    val religious: Boolean,
    val sexist: Boolean
)

fun JokeDto.toJoke(): Joke = Joke(this.error, this.id, this.joke)