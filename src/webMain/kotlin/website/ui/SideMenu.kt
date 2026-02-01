package website.ui

import androidx.compose.runtime.Composable
import dev.kilua.core.IComponent
import dev.kilua.html.details
import dev.kilua.html.li
import dev.kilua.html.navLink
import dev.kilua.html.summary
import dev.kilua.html.ul
import web.dom.ElementId
import web.dom.document
import web.html.HTMLInputElement
import website.Page
import kotlin.js.unsafeCast

@Composable
fun IComponent.sideMenu(currentPage: Page?) {
    ul("menu w-full px-4 py-0 mt-4") {
        Page.entries.filter { it.route != null && it.parent == null }.forEach { page ->
            if (page.isSection) {
                li {
                    details {
                        attribute("open", "true")
                        summary {
                            +page.title
                        }
                        ul {
                            Page.entries.filter { it.parent == page && it.route != null }.forEach { subPage ->
                                val menuActiveClass = if (currentPage == subPage) "menu-active" else null
                                li {
                                    navLink(subPage.path!!, subPage.title, className = menuActiveClass) {
                                        onClick {
                                            val drawer = document.getElementById(ElementId("drawer"))?.unsafeCast<HTMLInputElement>()
                                            if (drawer != null && drawer.checked) {
                                                drawer.click()
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                val menuActiveClass = if (currentPage == page) "menu-active" else null
                li {
                    navLink(page.path!!, page.title, className = menuActiveClass) {
                        onClick {
                            val drawer = document.getElementById(ElementId("drawer"))?.unsafeCast<HTMLInputElement>()
                            if (drawer != null && drawer.checked) {
                                drawer.click()
                            }
                        }
                    }
                }
            }
        }
    }
}
