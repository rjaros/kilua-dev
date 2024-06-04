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

package website

import dev.kilua.Application
import dev.kilua.CssRegister
import dev.kilua.FontAwesomeModule
import dev.kilua.TailwindcssModule
import dev.kilua.compose.root
import dev.kilua.ssr.SsrRouter
import dev.kilua.startApplication
import dev.kilua.theme.ThemeManager
import dev.kilua.utils.JsModule
import dev.kilua.utils.useModule
import website.components.features
import website.components.footer
import website.components.header
import website.components.hero
import website.components.learning
import website.components.ssr

@JsModule("./modules/css/custom.css")
external object CustomCss

external fun KotlinPlayground(selector: String)

class App : Application() {

    init {
        CssRegister.register("modules/css/custom.css")
        useModule(CustomCss)
    }

    override fun start() {

        ThemeManager.init()

        root("root") {
            SsrRouter(initPath = "/") { ->
                header()
                hero()
                features()
                learning()
                ssr()
                footer()
            }
        }
    }
}

fun main() {
    startApplication(
        ::App,
        FontAwesomeModule,
        TailwindcssModule
    )
}
