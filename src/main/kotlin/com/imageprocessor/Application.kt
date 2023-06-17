package com.imageprocessor

import com.imageprocessor.plugins.configureRouting
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

/**
 * Server class for managing service calls
 */
fun main() {
    embeddedServer(Netty, port = ConstantAPI.PORT, host = ConstantAPI.HOST) {
        configureRouting()
    }.start(wait = true)
}
