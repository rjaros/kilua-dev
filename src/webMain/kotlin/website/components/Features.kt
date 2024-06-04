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
import dev.kilua.html.li
import dev.kilua.html.link
import dev.kilua.html.section
import dev.kilua.html.ul

@Composable
fun IComponent.features() {
    link {
        id("features")
        tabindex(-1)
        outline(Outline(style = OutlineStyle.None))
    }
    br()
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
                    +"Choose from the wide range of ready to use components and form inputs."
                }
                li {
                    +"Easily style your application using "
                    link(
                        "https://tailwindcss.com",
                        "Tailwindcss",
                        className = "font-bold text-primary dark:text-primarylink hover:text-secondary"
                    )
                    +" or "
                    link(
                        "https://getbootstrap.com",
                        "Bootstrap",
                        className = "font-bold text-primary dark:text-primarylink hover:text-secondary"
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
                    link(
                        "https://github.com/rjaros/kilua-rpc",
                        "Kilua RPC",
                        className = "font-bold text-primary dark:text-primarylink hover:text-secondary"
                    )
                    +" library supporting Ktor, Spring Boot, Micronaut, Javalin, Jooby and Vert.x servers."
                }
                li {
                    +"Translate your application to other languages with "
                    link(
                        "https://www.gnu.org/software/gettext/",
                        "Gettext",
                        className = "font-bold text-primary dark:text-primarylink hover:text-secondary"
                    )
                    +" - one of the most widely used tool for i18n."
                }
                li {
                    +"Deploy your application with full SSR for better SEO performance and user experience."
                }
            }
        }
    }
}