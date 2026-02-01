package website.ui

import androidx.compose.runtime.Composable
import dev.kilua.core.IComponent
import dev.kilua.html.img
import dev.kilua.html.navLink
import dev.kilua.utils.rem

@Composable
fun IComponent.homeLink(className: String? = null) {
    navLink("/", className = "flex w-35 shrink-0 items-center gap-2" % className) {
        img(
            "assets/images/logo-color.png",
            alt = "Kilua Logo",
            className = "h-10 w-auto mr-4 hidden dark:block"
        )
        img(
            "assets/images/logo-black.png",
            alt = "Kilua Logo",
            className = "h-10 w-auto mr-4 block dark:hidden"
        )
    }
}
