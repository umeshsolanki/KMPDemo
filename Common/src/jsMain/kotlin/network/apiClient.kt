package network

import io.ktor.client.*
import io.ktor.client.engine.js.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*

val apiClient = HttpClient(Js) {
    install(ContentNegotiation) {
        json() // Example: Register JSON content transformation
        // Add more transformations as needed for other content types
    }
}
    