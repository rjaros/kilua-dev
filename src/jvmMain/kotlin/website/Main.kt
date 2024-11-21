package website

import dev.kilua.ssr.initSsr
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.plugins.compression.*

fun main(args: Array<String>) {
    val config = CommandLineConfig(args)
    embeddedServer(CIO, config.environment, {
        takeFrom(config.engineConfig)
        loadCommonConfiguration(config.rootConfig.environment.config.config("ktor.deployment"))
    }) {
        install(Compression)
        initSsr()
    }.start(wait = true)
}
