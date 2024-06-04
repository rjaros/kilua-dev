package website

import dev.kilua.ssr.initSsr
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.plugins.compression.*

fun main(args: Array<String>) {
    val env = commandLineEnvironment(args) {
        module {
            install(Compression)
            initSsr()
        }
    }
    embeddedServer(CIO, env).start(wait = true)
}
