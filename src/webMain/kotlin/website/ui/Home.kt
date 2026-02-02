package website.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import dev.kilua.core.IComponent
import dev.kilua.html.a
import dev.kilua.html.div
import dev.kilua.html.h2
import dev.kilua.html.li
import dev.kilua.html.p
import dev.kilua.html.rawHtml
import dev.kilua.html.section
import dev.kilua.html.span
import dev.kilua.html.ul
import website.externals.MarketplaceWidget
import website.externals.getMarked

@Composable
fun IComponent.home() {
    section {
        div("container mx-auto flex flex-col md:flex-row md:space-x-4 items-center justify-end pb-4") {
            div("border border-neutral-500 rounded-full overflow-hidden") {
                div(id = "marketplace") {
                    LaunchedEffect(Unit) {
                        if (renderConfig.isDom) {
                            MarketplaceWidget.setupMarketplaceWidget("install", 27530, "#marketplace")
                        }
                    }
                }
            }
        }
    }
    section {
        div("container mx-auto flex flex-col xl:flex-row xl:space-x-4 items-center justify-between") {
            div("xl:w-1/2 mb-8 xl:mb-0") {
                h2("text-3xl font-bold mb-4") {
                    +"Kilua - "
                    span("text-primary") { +"@Composable" }
                    +" web framework for Kotlin/Wasm and Kotlin/JS"
                }
                p("my-7") {
                    +"Kilua is an open source web framework for Kotlin, based on Compose Multiplatform runtime. It allows you to create declarative UI components and manage their state. Kilua renders to plain HTML DOM (not canvas) and supports both Kotlin/Wasm and Kotlin/JS targets. It provides a lot of ready to use components. It's the first Kotlin UI framework with full support for true SSR (Server-Side Rendering)."
                }
                div("flex space-x-5 items-center") {
                    a(
                        "https://github.com/rjaros/kilua",
                        "GitHub",
                        "bi bi-github",
                        className = "bg-primary hover:bg-secondary text-white font-semibold px-4 py-2 rounded-full inline-flex items-center gap-x-2"
                    )
                    p {
                        +"Current version: 0.0.32"
                    }
                }
            }
            div("xl:w-1/2 hidden md:block") {
                div("text-xs border-2 rounded-xs") {
                    rawHtml(renderedCodeSample)
                }
            }
        }
    }
    section("mt-16") {
        div("container mx-auto") {
            h2("text-3xl font-bold mb-4") {
                +"Features"
            }
            ul("list-disc list-outside ml-4") {
                li {
                    +"Use powerful Compose programming model and state management to develop web applications."
                }
                li {
                    +"Work with super fast hot reload and full HMR using "
                    a(
                        "https://gitlab.com/opensavvy/automation/kotlin-vite",
                        "Vite for Kotlin",
                        className = "font-bold text-primary hover:text-secondary"
                    )
                    +" plugin."
                }
                li {
                    +"Choose from the wide range of ready to use components and form inputs."
                }
                li {
                    +"Easily style your application using "
                    a(
                        "https://tailwindcss.com",
                        "Tailwindcss",
                        className = "font-bold text-primary hover:text-secondary"
                    )
                    +" or "
                    a(
                        "https://getbootstrap.com",
                        "Bootstrap",
                        className = "font-bold text-primary hover:text-secondary"
                    )
                    +", with built-in support for dark mode."
                }
                li {
                    +"Enhance user experience with lazy layouts, svg graphics and masked inputs."
                }
                li {
                    +"Use built-in router for navigation, HTTP client for API calls and markdown parser to display your data."
                }
                li {
                    +"Compile the same application code for Kotlin/Wasm or Kotlin/JS targets."
                }
                li {
                    +"Create fullstack applications with a companion "
                    a(
                        "https://github.com/rjaros/kilua-rpc",
                        "Kilua RPC",
                        className = "font-bold text-primary hover:text-secondary"
                    )
                    +" library supporting Ktor, Spring Boot, Micronaut, Javalin, Jooby and Vert.x servers."
                }
                li {
                    +"Translate your application to other languages with "
                    a(
                        "https://www.gnu.org/software/gettext/",
                        "Gettext",
                        className = "font-bold text-primary hover:text-secondary"
                    )
                    +" - one of the most widely used tool for i18n."
                }
                li {
                    +"Deploy your application with full SSR for better SEO performance and user experience."
                }
                li {
                    +"Export your application as a set of static HTML files for more affordable hosting solutions."
                }
            }
        }
    }
}

private val codeSample = """
```kotlin    
class App : Application() {
    override fun start() {
        root("root") {
            var state by remember { mutableStateOf("Hello, world!") }

            div {
                +state
            }
            button("Add an exclamation mark") {
                onClick {
                    state += "!"
                }
            }
        }
    }
}

fun main() {
    startApplication(::App)
}
```
"""

private val renderedCodeSample = getMarked().parse(codeSample)
