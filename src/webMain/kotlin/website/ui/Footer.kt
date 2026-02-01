package website.ui

import androidx.compose.runtime.Composable
import dev.kilua.core.IComponent
import dev.kilua.html.a
import dev.kilua.html.br
import dev.kilua.html.footer
import dev.kilua.html.img
import dev.kilua.html.li
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
            a(
                "https://github.com/rjaros",
                "Robert Jaros",
                className = "font-bold text-primary hover:text-secondary"
            )
            br()
            a(
                "mailto:rjaros@treksoft.pl",
                "rjaros@treksoft.pl",
                className = "font-bold text-primary hover:text-secondary"
            )
            br()
            +"Website created with "
            a(
                "https://tailwindcss.com",
                "Tailwindcss",
                className = "font-bold text-primary hover:text-secondary"
            )
            +" and "
            a(
                "https://daisyui.com",
                "DaisyUI",
                className = "font-bold text-primary hover:text-secondary"
            )
        }
        ul("flex justify-center mt-10 space-x-8 mb-10") {
            li {
                a("https://github.com/rjaros/kilua", icon = "bi bi-github text-3xl") {
                    ariaLabel("GitHub")
                }
            }
            li {
                a("https://kotlinlang.slack.com/?redir=%2Fmessages%2FC06UAH52PA7", icon = "bi bi-slack text-3xl") {
                    ariaLabel("Slack")
                }
            }
        }
    }
}