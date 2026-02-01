package website.ui

import androidx.compose.runtime.Composable
import dev.kilua.core.IComponent
import dev.kilua.form.check.checkBox
import website.Page
import dev.kilua.html.aside
import dev.kilua.html.div
import dev.kilua.html.label
import dev.kilua.html.nav
import dev.kilua.html.span
import dev.kilua.utils.rem

@Composable
fun IComponent.layout(currentPage: Page, content: @Composable IComponent.() -> Unit) {
    val drawerOpenClass = if (currentPage.isDrawerOpen) {
        "lg:drawer-open"
    } else null
    div("bg-base-100 drawer mx-auto max-w-[80rem]" % drawerOpenClass) {
        checkBox(id = "drawer", className = "drawer-toggle")
        div("drawer-content") {
            div("bg-base-100/90 text-base-content sticky top-0 z-30 flex w-full [transform:translate3d(0,0,0)] justify-center backdrop-blur transition-shadow duration-100 print:hidden border-b-2 border-b-black dark:border-b-white") {
                nav("navbar w-full py-4") {
                    div("flex flex-1 items-center md:gap-2 lg:gap-3") {
                        val lgHiddenClass = if (currentPage.isDrawerOpen) {
                            "lg:hidden"
                        } else null
                        span("tooltip tooltip-bottom before:text-xs before:content-[attr(data-tip)]") {
                            attribute("data-tip", "Menu")
                            label(
                                htmlFor = "drawer",
                                className = "btn btn-square btn-ghost drawer-button" % lgHiddenClass
                            ) {
                                ariaLabel("Open menu")
                                bars()
                            }
                        }
                        homeLink(lgHiddenClass)
                    }
                    div("flex gap-1 md:gap-2 lg:gap-3 items-center") {
                        topMenu()
                    }
                }
            }
            div("relative max-w-[100vw] px-4 pt-4 pb-16 xl:pe-2") {
                content()
                footer()
            }
        }
        div("drawer-side z-40") {
            style("scroll-behavior", "smooth")
            style("scroll-padding-top", "5rem")
            label(htmlFor = "drawer", className = "drawer-overlay") {
                ariaLabel("Close menu")
            }
            aside("bg-base-100 min-h-screen w-80") {
                val lgFlexClass = if (currentPage.isDrawerOpen) {
                    "lg:flex"
                } else null
                div("bg-base-100/90 navbar sticky top-0 z-20 hidden items-center gap-2 px-4 py-4 backdrop-blur border-b-2 border-b-black dark:border-b-white" % lgFlexClass) {
                    homeLink()
                }
                sideMenu(currentPage)
                div("bg-base-100 pointer-events-none sticky bottom-0 flex h-40 [mask-image:linear-gradient(transparent,#000000)]")
            }
        }
    }
}