package org.kmp.joke.domain

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Failure(val errorMessage: String) : Result<Nothing>()
    object Loading : Result<Nothing>()
}