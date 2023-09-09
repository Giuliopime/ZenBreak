package dev.giuliopime.shared_core.core

import kotlinx.serialization.json.Json

object JsonSerialization {
    val json = Json { ignoreUnknownKeys = true }
}