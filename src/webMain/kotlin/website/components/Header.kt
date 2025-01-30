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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import dev.kilua.core.IComponent
import dev.kilua.html.Outline
import dev.kilua.html.OutlineStyle
import dev.kilua.html.button
import dev.kilua.html.div
import dev.kilua.html.header
import dev.kilua.html.img
import dev.kilua.html.li
import dev.kilua.html.link
import dev.kilua.html.nav
import dev.kilua.html.ul
import dev.kilua.svg.path
import dev.kilua.svg.svg
import dev.kilua.theme.themeSwitcher

@Composable
fun IComponent.header() {
    var mobileMenuVisible by remember { mutableStateOf(false) }

    link {
        id("home")
        tabindex(-1)
        outline(Outline(style = OutlineStyle.None))
    }
    header("text-lg bg-white dark:bg-gray-dark text-black dark:text-white sticky top-0 z-50 border-b-2 border-b-black dark:border-b-white") {
        div("container mx-auto flex justify-between items-center py-4") {
            div("flex items-center") {
                link("#home") {
                    img(
                        "assets/images/logo-color.png",
                        alt = "Logo",
                        className = "h-14 w-auto mr-4 hidden dark:block"
                    )
                    img(
                        "assets/images/logo-black.png",
                        alt = "Logo",
                        className = "h-14 w-auto mr-4 block dark:hidden"
                    )
                }
            }
            div("flex md:hidden") {
                button(className = "focus:outline-hidden") {
                    id("hamburger")
                    svg(viewBox = "0 0 24 24", className = "w-6 h-6") {
                        fill("none")
                        attribute("stroke", "currentColor")
                        path("M4 6h16M4 12h16m-7 6h7") {
                            attribute("stroke-linecap", "round")
                            attribute("stroke-linejoin", "round")
                            attribute("stroke-width", "2")
                        }
                    }
                    onClick {
                        mobileMenuVisible = !mobileMenuVisible
                    }
                }
            }
            nav("hidden md:flex md:flex-grow justify-center") {
                ul("flex justify-center space-x-4") {
                    li {
                        link("#features", "Features", className = "hover:text-secondary font-bold")
                    }
                    li {
                        link("#learning", "Learning", className = "hover:text-secondary font-bold")
                    }
                    li {
                        link("#ssr", "SSR", className = "hover:text-secondary font-bold")
                    }
                }
            }
            div("hidden md:flex items-center space-x-4") {
                link(
                    "https://github.com/rjaros/kilua?tab=readme-ov-file#building-and-running-the-examples",
                    "Get started",
                    className = "hidden lg:block bg-primary hover:bg-secondary text-white font-semibold px-4 py-2 rounded-full inline-block"
                )
                themeSwitcher(round = true, className = "bg-primary hover:bg-secondary w-10 h-10 px-3")
            }
        }
    }
    val mobileMenuClass = if (mobileMenuVisible)
        "mobile-menu flex flex-col items-center space-y-8 md:hidden"
    else
        "mobile-menu hidden flex flex-col items-center space-y-8 md:hidden"
    mobileMenu(mobileMenuClass)
}