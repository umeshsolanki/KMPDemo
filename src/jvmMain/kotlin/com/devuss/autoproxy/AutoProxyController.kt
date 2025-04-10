package com.devuss.autoproxy

import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

class AutoProxyController(routing: Routing) {

    init {
        init(routing)
    }

    private fun init(routing: Routing) {
        routing.apply {
            get("/proxy") {
                call.request.headers.forEach { s, strings ->
                    println("$s: ${strings.joinToString(", ")}")
                }
                println()
                call.respondText(
                    """
                    function FindProxyForURL(url, host) {
                        return "PROXY 172.16.44.110:4318";
                    }
                    """.trimIndent(), ContentType.Application.JavaScript
                )
            }
        }
    }
}
