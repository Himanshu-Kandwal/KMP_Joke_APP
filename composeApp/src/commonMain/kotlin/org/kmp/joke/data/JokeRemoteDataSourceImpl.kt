package org.kmp.joke.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.kmp.joke.Constants

class JokeRemoteDataSourceImpl(
    private val baseUrl: String = Constants.URL, //default base url
    private val httpClient: HttpClient
) : JokeRemoteDataSource {
    override suspend fun getJoke(): JokeDto {
        return httpClient.get(baseUrl).body<JokeDto>()
    }
}