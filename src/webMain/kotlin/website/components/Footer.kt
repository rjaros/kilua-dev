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
import dev.kilua.html.br
import dev.kilua.html.footer
import dev.kilua.html.img
import dev.kilua.html.li
import dev.kilua.html.link
import dev.kilua.html.span
import dev.kilua.html.ul

@Composable
fun IComponent.footer() {
    footer("mt-16") {
        img(
            "assets/images/logo-color.png",
            alt = "Logo",
            className = "h-14 w-auto mx-auto mb-5 hidden dark:block"
        )
        img(
            "assets/images/logo-black.png",
            alt = "Logo",
            className = "h-14 w-auto mx-auto mb-5 block dark:hidden"
        )
        span("block text-center font-bold") {
            +"Created by "
            link(
                "https://github.com/rjaros",
                "Robert Jaros",
                className = "font-bold text-primary dark:text-primarylink hover:text-secondary"
            )
            br()
            link(
                "mailto:rjaros@treksoft.pl",
                "rjaros@treksoft.pl",
                className = "font-bold text-primary dark:text-primarylink hover:text-secondary"
            )
            br()
            +"Website created with "
            link(
                "https://github.com/spacemadev/elevate-free-tailwind-business-template/tree/main",
                "Elevate",
                className = "font-bold text-primary dark:text-primarylink hover:text-secondary"
            )
            +" and "
            link(
                "https://tailwindcss.com",
                "Tailwindcss",
                className = "font-bold text-primary dark:text-primarylink hover:text-secondary"
            )
        }
        ul("flex justify-center mt-10 space-x-8 mb-10") {
            li {
                link("https://github.com/rjaros/kilua", icon = "fab fa-github fa-2x")
            }
            li {
                link("https://kotlinlang.slack.com/?redir=%2Fmessages%2FC06UAH52PA7", icon = "fab fa-slack fa-2x")
            }
            li {
                link("https://dev.to/t/kilua/latest", icon = "fab fa-dev fa-2x")
            }
        }
    }
}