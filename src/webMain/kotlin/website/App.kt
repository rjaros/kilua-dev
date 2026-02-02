package website

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import app.softwork.routingcompose.Router
import dev.kilua.Application
import dev.kilua.CoreModule
import dev.kilua.CssRegister
import dev.kilua.Hot
import dev.kilua.TailwindcssModule
import dev.kilua.compose.root
import website.ui.layout
import website.ui.mainContent
import dev.kilua.html.div
import dev.kilua.routing.global
import dev.kilua.ssr.ssrRouter
import dev.kilua.startApplication
import dev.kilua.theme.Theme
import dev.kilua.theme.ThemeManager
import dev.kilua.useModule
import dev.kilua.utils.isDom
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import web.dom.document
import web.events.CustomEvent
import web.events.EventType
import web.events.addEventHandler
import kotlin.js.JsAny
import kotlin.js.JsModule

@JsModule("./modules/css/custom.css")
external object CustomCss

class App : Application(), KoinComponent {
    init {
        CssRegister.register("modules/css/custom.css")
        useModule(CustomCss)
    }

    var unsubscribeThemeChanged: (() -> Unit)? = null

    override fun start() {
        ThemeManager.init()
        if (isDom) initDaisyUiTheme()
        startKoin {
            modules(appModule, ballastModule)
        }
        val viewModel: ViewModel by inject()
        val json: Json by inject()
        root("root") {
            val state by viewModel.observeStates().collectAsState()
            ssrRouter(
                stateSerializer = {
                    json.encodeToString(viewModel.observeStates().value)
                }
            ) {
                defaultContent {
                    div(id = "main-content") {
                        layout(state.page) {
                            mainContent(state.page, state.renderedPage, state.previousPage, state.nextPage)
                        }
                    }
                }
                defaultMeta {
                    titleTemplate = "%s - Kilua"
                }
                route("/") {
                    action {
                        viewModel.sendAndAwaitCompletion(Contract.Inputs.Home)
                    }
                    meta {
                        titleAbsolute = "Kilua"
                    }
                }
                Page.entries.filter { it.route != null && it.parent == null }.forEach { page ->
                    if (page.isSection) {
                        route(page.route!!) {
                            Page.entries.filter { it.parent == page && it.route != null }.forEach { subPage ->
                                route(subPage.route!!) {
                                    action {
                                        viewModel.sendAndAwaitCompletion(Contract.Inputs.SitePage(subPage))
                                    }
                                    meta {
                                        title = subPage.title
                                    }
                                }
                            }
                            action {
                                if (page.redirect != null) {
                                    Router.global.navigate(page.redirect!!.path!!)
                                } else {
                                    viewModel.sendAndAwaitCompletion(Contract.Inputs.SitePage(page))
                                }
                            }
                            meta {
                                title = page.title
                            }
                        }
                    } else {
                        route(page.route!!) {
                            action {
                                viewModel.sendAndAwaitCompletion(Contract.Inputs.SitePage(page))
                            }
                            meta {
                                title = page.title
                            }
                        }
                    }
                }
                action {
                    viewModel.sendAndAwaitCompletion(Contract.Inputs.NotFound)
                }
                meta {
                    title = "404 - Page not found"
                }
            }
        }
    }

    private fun initDaisyUiTheme() {
        setTheme(ThemeManager.theme)
        unsubscribeThemeChanged = document.addEventHandler(EventType<CustomEvent<JsAny>>("kilua.theme.changed")) {
            setTheme(ThemeManager.theme)
        }
    }

    private fun setTheme(theme: Theme) {
        if (theme == Theme.Dark) {
            document.documentElement.setAttribute("data-theme", "dark")
        } else {
            document.documentElement.setAttribute("data-theme", "winter")
        }
    }

    override fun dispose(): String? {
        stopKoin()
        unsubscribeThemeChanged?.invoke()
        return null
    }
}

fun main() {
    startApplication(
        ::App,
        bundlerHot(),
        TailwindcssModule,
        CoreModule
    )
}

expect fun bundlerHot(): Hot?
