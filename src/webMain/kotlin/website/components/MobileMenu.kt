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
import dev.kilua.html.div
import dev.kilua.html.li
import dev.kilua.html.link
import dev.kilua.html.nav
import dev.kilua.html.ul

@Composable
fun IComponent.mobileMenu(className: String) {
    nav(className) {
        id("mobile-menu-placeholder")
        ul {
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
        div("flex flex-col mt-6 space-y-2 items-center") {
            link(
                "https://github.com/rjaros/kilua?tab=readme-ov-file#building-and-running-the-examples",
                "Get started",
                className = "bg-primary hover:bg-secondary text-white font-semibold px-4 py-2 rounded-full inline-block flex items-center justify-center min-w-[110px]"
            )
        }
    }
}