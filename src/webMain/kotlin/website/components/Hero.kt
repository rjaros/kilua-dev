/*
 * Copyright (c) 2024 Robert Jaros
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package website.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import dev.kilua.core.IComponent
import dev.kilua.html.code
import dev.kilua.html.div
import dev.kilua.html.h2
import dev.kilua.html.link
import dev.kilua.html.p
import dev.kilua.html.perc
import dev.kilua.html.section
import dev.kilua.html.span
import dev.kilua.utils.isDom
import website.KotlinPlayground

@Composable
fun IComponent.hero() {
    section("mt-16") {
        div("container mx-auto flex flex-col xl:flex-row xl:space-x-4 items-center justify-between") {
            div("xl:w-1/2 mb-8 xl:mb-0") {
                h2("text-3xl font-bold mb-4") {
                    +"Kilua - "
                    span("text-primary") { +"@Composable" }
                    +" web framework for Kotlin/Wasm and Kotlin/JS"
                }
                p("my-7") {
                    +"Kilua is an open source web framework for Kotlin, based on Compose Multiplatform runtime. It allows you to create declarative UI components and manage their state. Kilua renders to plain HTML DOM (not canvas) and supports both Kotlin/Wasm and Kotlin/JS targets. It provides a lot of ready to use components. It's the only Kotlin UI framework with full support for true SSR (Server-Side Rendering)."
                }
                div("flex space-x-5 items-center") {
                    link(
                        "https://github.com/rjaros/kilua",
                        "GitHub",
                        "fa-brands fa-github",
                        className = "bg-primary hover:bg-secondary text-white font-semibold px-4 py-2 rounded-full inline-flex items-center gap-x-2"
                    )
                    p {
                        +"Current version: 0.0.10"
                    }
                }
            }
            div("xl:w-1/2") {
                code(className = "code-blocks-selector") {
                    width(100.perc)
                    attribute("data-highlight-only", "nocursor")
                    attribute("theme", "darcula")
                    attribute("auto-indent", "true")
                    attribute("mode", "kotlin")
                    attribute("lines", "false")
                    +"""
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
                                """.trimIndent()
                    LaunchedEffect(Unit) {
                        if (isDom) {
                            KotlinPlayground(".code-blocks-selector")
                        }
                    }
                }
            }
        }
    }
}