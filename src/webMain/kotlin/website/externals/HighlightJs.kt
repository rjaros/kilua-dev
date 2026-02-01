@file:JsModule("highlight.js/lib/core")

package website.externals

import kotlin.js.JsAny
import kotlin.js.JsModule
import kotlin.js.JsName

external interface HighlightOptions: JsAny {
    var language: String
}

external interface HighlightResult: JsAny {
    var value: String
}

@JsName("default")
external object HighlightJs: JsAny {
    fun registerLanguage(name: String, language: JsAny)
    fun getLanguage(name: String): String?
    fun highlight(code: String, options: HighlightOptions): HighlightResult
}
