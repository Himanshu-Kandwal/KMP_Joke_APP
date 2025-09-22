# KMP Joke App

**KMP Joke App** is a Kotlin Multiplatform project designed to fetch and display jokes, showcasing
the power of KMP for building applications across Android, iOS, and Desktop (JVM) with a shared
codebase.

## Features

* Displays random jokes fetched from a remote API.
* Cross-platform: Runs on Android, iOS.
* Shared UI built with Compose Multiplatform.

## Architecture

This project aims to follow Clean Architecture principles, separating concerns into distinct layers:

* **UI Layer (`composeApp/src/commonMain/kotlin/org/kmp/joke/ui`):**
    * Built with **Compose Multiplatform** for a shared user interface across all target platforms.
    * Uses a **ViewModel (`JokeViewModel`)** to hold and manage UI-related state, exposing it via
      Kotlin Flow (`StateFlow`).
* **Domain Layer (`composeApp/src/commonMain/kotlin/org/kmp/joke/domain`):**
    * Contains business logic and use cases (e.g., `GetJokesUseCase`).
    * Defines repository interfaces (`JokeRepository`) that abstract data sources.
    * Uses a `Result` wrapper to handle success, loading, and error states for data operations.
* **Data Layer (`composeApp/src/commonMain/kotlin/org/kmp/joke/data`):**
    * Implements repository interfaces (`JokeRepositoryImpl`).
    * Includes a `JokeRemoteDataSource` to fetch data from the [JokeAPI](https://jokeapi.dev/).
    * Manages data retrieval and mapping between DTOs and domain models.

Kotlin Coroutines and Flow are used extensively for managing asynchronous operations and reactive
data streams throughout the application.

## Technical Stack

* **KMP - Kotlin Multiplatform:** For sharing code across platforms.
* **Kotlin Coroutines & Flow:** For asynchronous programming and reactive data streams.
* **Ktor Client:** For making HTTP requests to the joke API (with platform-specific engine
  configurations).
* **Koin:** For dependency injection.

## Project Structure

* `./composeApp/src`: Contains the shared Kotlin Multiplatform code.
    * `commonMain`: Code common to all targets (Android, iOS, JVM). This includes the core business
      logic, data handling, and shared UI with Compose Multiplatform.
    * `androidMain`: Android-specific implementations (e.g., platform-specific HTTP client engine).
    * `iosMain`: iOS-specific implementations (e.g., platform-specific HTTP client engine).
* `./iosApp`: The Xcode project for the iOS application.