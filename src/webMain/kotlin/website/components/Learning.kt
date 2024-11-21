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
import dev.kilua.html.Outline
import dev.kilua.html.OutlineStyle
import dev.kilua.html.br
import dev.kilua.html.code
import dev.kilua.html.div
import dev.kilua.html.h2
import dev.kilua.html.li
import dev.kilua.html.link
import dev.kilua.html.p
import dev.kilua.html.perc
import dev.kilua.html.section
import dev.kilua.html.span
import dev.kilua.html.ul
import website.KotlinPlayground

@Composable
fun IComponent.learning() {
    link {
        id("learning")
        tabindex(-1)
        outline(Outline(style = OutlineStyle.None))
    }
    br()
    section("mt-16") {
        div("container mx-auto") {
            h2("text-3xl font-bold mb-4") {
                +"Learning"
            }
            ul("list-disc list-outside ml-4") {
                li {
                    +"The official guide is published at "
                    link(
                        "https://kilua.gitbook.io/kilua-guide",
                        "https://kilua.gitbook.io/kilua-guide",
                        className = "font-bold text-primary dark:text-primarylink hover:text-secondary"
                    )
                    +". It's still incomplete but already contains a lot of useful information."
                }
                li {
                    +"Current API documentation is published at "
                    link(
                        "https://rjaros.github.io/kilua/api/",
                        "https://rjaros.github.io/kilua/api/",
                        className = "font-bold text-primary dark:text-primarylink hover:text-secondary"
                    )
                    +"."
                }
                li {
                    +"Different example applications can be found in the "
                    link(
                        "https://github.com/rjaros/kilua/tree/main/examples",
                        "examples directory",
                        className = "font-bold text-primary dark:text-primarylink hover:text-secondary"
                    )
                    +"."
                }
                li {
                    +"Check SSR examples to see how to initialize Server-Side Rendering in different web servers."
                }
                li {
                    +"The "
                    link(
                        "https://github.com/rjaros/kilua/tree/main/examples/fullstack-ktor-koin",
                        "fullstack example",
                        className = "font-bold text-primary dark:text-primarylink hover:text-secondary"
                    )
                    +" shows how to create a fullstack application with Ktor web server and how to use different fullstack components."
                }
                li {
                    +"Be sure to check out the "
                    link(
                        "https://github.com/rjaros/kilua/tree/main/examples/realworld",
                        "realworld example",
                        className = "font-bold text-primary dark:text-primarylink hover:text-secondary"
                    )
                    +" - a fully fledged fullstack application based on the "
                    link(
                        "https://realworld.io/",
                        "RealWorld project",
                        className = "font-bold text-primary dark:text-primarylink hover:text-secondary"
                    )
                    +" specification, supporting CRUD operations, authentication, routing, pagination, SSR and more."
                }
                li {
                    +"This website is also a Kilua application fully designed with Tailwindcss. The primary version is published as exported static site on GitHub Pages. "
                    +"The secondary version is powered by a Ktor server with full SSR, running as GraalVM native image in a Docker container published to GitHub packages and hosted on "
                    link(
                        "https://render.com/",
                        "Render",
                        className = "font-bold text-primary dark:text-primarylink hover:text-secondary"
                    )
                    +" with a free plan. You can check this version at "
                    link(
                        "https://ssr.kilua.dev/",
                        "https://ssr.kilua.dev/",
                        className = "font-bold text-primary dark:text-primarylink hover:text-secondary"
                    )
                    +" (it may require some time to activate). The full source code, including GitHub Actions workflow is available at "
                    link(
                        "https://github.com/rjaros/kilua-dev",
                        "https://github.com/rjaros/kilua-dev",
                        className = "font-bold text-primary dark:text-primarylink hover:text-secondary"
                    )
                    +"."
                }
            }
        }
    }
}