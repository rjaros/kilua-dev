package website

import kotlinx.serialization.Serializable

object Contract {

    @Serializable
    data class State(
        val page: Page,
        val renderedPage: String? = null,
        val previousPage: Page? = null,
        val nextPage: Page? = null
    )

    sealed class Inputs {
        data object Home : Inputs()
        data class SitePage(val page: Page) : Inputs()
        data object NotFound : Inputs()
    }

    sealed class Events
}
