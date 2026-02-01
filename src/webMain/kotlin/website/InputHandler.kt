package website

import com.copperleaf.ballast.InputHandler
import com.copperleaf.ballast.InputHandlerScope
import dev.kilua.marked.Marked
import dev.kilua.rest.ResponseBodyType
import dev.kilua.rest.RestClient
import dev.kilua.rest.call
import dev.kilua.utils.isDom

class InputHandler(private val restClient: RestClient, private val marked: Marked) :
    InputHandler<Contract.Inputs, Contract.Events, Contract.State> {

    override suspend fun InputHandlerScope<Contract.Inputs, Contract.Events, Contract.State>.handleInput(
        input: Contract.Inputs
    ) = when (input) {

        Contract.Inputs.Home -> updateState {
            it.copy(page = Page.Home, renderedPage = null)
        }

        is Contract.Inputs.SitePage -> {
            val url = "assets/md${input.page.path}.md"
            val resolvedUrl = if (isDom) url else "http://localhost:8080/$url"
            val markdown = restClient.call<String>(resolvedUrl) {
                responseBodyType = ResponseBodyType.Text
            }
            val renderedPage = marked.parse(markdown)
            val allPages = Page.entries.filter { it.route != null && !it.isSection }
            val previousPage = allPages.indexOf(input.page).let { currentIndex ->
                allPages.getOrNull(currentIndex - 1)
            }
            val nextPage = allPages.indexOf(input.page).let { currentIndex ->
                allPages.getOrNull(currentIndex + 1)
            }
            updateState {
                it.copy(
                    page = input.page,
                    renderedPage = renderedPage,
                    previousPage = previousPage,
                    nextPage = nextPage
                )
            }
        }

        Contract.Inputs.NotFound -> updateState {
            it.copy(page = Page.NotFound, renderedPage = null)
        }
    }
}
