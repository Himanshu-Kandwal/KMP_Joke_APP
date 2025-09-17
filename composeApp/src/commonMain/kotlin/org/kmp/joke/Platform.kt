package org.kmp.joke

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform