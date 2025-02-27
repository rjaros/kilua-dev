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
import dev.kilua.core.IComponent
import dev.kilua.html.Outline
import dev.kilua.html.OutlineStyle
import dev.kilua.html.br
import dev.kilua.html.div
import dev.kilua.html.h2
import dev.kilua.html.h3
import dev.kilua.html.li
import dev.kilua.html.link
import dev.kilua.html.p
import dev.kilua.html.section
import dev.kilua.html.ul

@Composable
fun IComponent.ssr() {
    link {
        id("ssr")
        tabindex(-1)
        outline(Outline(style = OutlineStyle.None))
    }
    br()
    section("mt-16") {
        div("container mx-auto") {
            h2("text-3xl font-bold mb-4") {
                +"Server-Side Rendering"
            }
            p {
                +"Kilua is the first Kotlin/Wasm and Kotlin/JS web framework supporting true Server-Side Rendering. SSR is a crucial concept in modern web development that enhances user experience and boosts SEO performance. Kilua SSR support is based on the possibility to run exactly the same application code both in the browser and in NodeJs environment. What's more, you can easily use WASM compilation target for much better performance."
            }
            h3("text-xl font-bold mt-4") {
                +"SSR features"
            }
            ul("list-disc list-outside ml-4 mt-4") {
                li {
                    +"Preparing the application for SSR is as easy as changing the router class."
                }
                li {
                    +"Ability to use external API calls and fullstack RPC services."
                }
                li {
                    +"Automatically extracting CSS styles from JS bundle and injecting them into the HTML document before sending to the browser."
                }
                li {
                    +"Serialization of the application state from the server to the client side."
                }
                li {
                    +"Ready to use modules for Ktor, Spring Boot, Micronaut, Javalin, Jooby and Vert.x servers (Micronaut support is temporarily disabled)."
                }
            }
        }
    }
}