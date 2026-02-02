package website.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import dev.kilua.core.IComponent
import website.Page
import dev.kilua.html.article
import dev.kilua.html.augmentLinks
import dev.kilua.html.div
import dev.kilua.html.rawHtmlBlock
import web.dom.document

@Composable
fun IComponent.mainContent(page: Page, renderedPage: String?, previousPage: Page?, nextPage: Page?) {
    when (page) {
        Page.Home -> {
            home()
        }

        Page.NotFound -> {
            div {
                +"404 - Page not found"
            }
        }

        else -> {
            if (renderedPage != null) {
                article("prose prose-a:text-primary prose-a:hover:text-secondary prose-pre:p-0 prose-pre:border-2 prose-pre:rounded-xs max-w-none") {
                    rawHtmlBlock(renderedPage) {
                        LaunchedEffect(renderedPage) {
                            if (renderConfig.isDom) {
                                document.documentElement.scrollTo(0.0, 0.0)
                                augmentLinks(element)
                            }
                        }
                    }
                }
                navigationBar(previousPage, nextPage)
            } else {
                div {
                    +"Content not available"
                }
            }
        }
    }
}