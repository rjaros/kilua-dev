package website.ui

import androidx.compose.runtime.Composable
import dev.kilua.core.IComponent
import website.Page
import dev.kilua.html.div
import dev.kilua.html.navLink
import dev.kilua.theme.themeSwitcher

@Composable
fun IComponent.topMenu() {
    div("hidden flex-none items-center md:inline-block") {
        navLink(
            Page.Introduction.path,
            "Introduction",
            className = "btn btn-ghost drawer-button font-bold"
        )
    }
    div("hidden flex-none items-center md:inline-block") {
        navLink(
            Page.DevelopmentGuide.path,
            "Learning",
            className = "btn btn-ghost drawer-button font-bold"
        )
    }
    div("hidden flex-none items-center md:inline-block") {
        navLink(
            Page.ServerSideRendering.path,
            "SSR",
            className = "btn btn-ghost drawer-button font-bold"
        )
    }
    navLink(
        "${Page.GettingStarted.path}",
        "Quickstart",
        className = "min-w-26 bg-primary hover:bg-secondary text-white font-semibold px-4 py-2 rounded-full text-center"
    )
    themeSwitcher(
        autoIcon = "bi bi-circle-half",
        lightIcon = "bi bi-moon",
        darkIcon = "bi bi-sun-fill",
        round = true,
        className = "bg-primary hover:bg-secondary w-10 h-10 px-3"
    )
}