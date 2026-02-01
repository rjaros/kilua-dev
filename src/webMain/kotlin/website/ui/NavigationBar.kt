package website.ui

import androidx.compose.runtime.Composable
import dev.kilua.core.IComponent
import website.Page
import dev.kilua.html.div
import dev.kilua.html.i
import dev.kilua.html.navLink
import dev.kilua.html.span

@Composable
fun IComponent.navigationBar(previousPage: Page?, nextPage: Page?) {
    div("flex justify-between flex-wrap gap-2") {
        div("grow text-left") {
            if (previousPage != null) {
                navLink(
                    previousPage.path,
                    icon = "bi bi-chevron-left",
                    className = "btn btn-md md:btn-lg gap-2 lg:gap-3"
                ) {
                    div("flex flex-col items-start gap-0.5 leading-[1.1]") {
                        span("text-base-content/50 hidden text-[0.7rem] font-semibold tracking-wide md:block") {
                            +"Previous"
                        }
                        span {
                            +previousPage.title
                        }
                    }
                }
            }
        }
        div("grow text-right") {
            if (nextPage != null) {
                navLink(
                    nextPage.path,
                    className = "btn btn-md md:btn-lg gap-2 lg:gap-3"
                ) {
                    div("flex flex-col items-end gap-0.5 leading-[1.1]") {
                        span("text-base-content/50 hidden text-[0.7rem] font-semibold tracking-wide md:block") {
                            +"Next"
                        }
                        span {
                            +nextPage.title
                        }
                    }
                    i("bi bi-chevron-right")
                }
            }
        }
    }
}