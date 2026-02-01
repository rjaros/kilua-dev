@file:JsModule("marked-highlight")

package website.externals

import kotlin.js.JsAny
import kotlin.js.JsModule

external interface MarkedHighlightOptions : JsAny {
    var emptyLangClass: String
    var langPrefix: String
    var highlight: (code: String, lang: String) -> String
}

external fun markedHighlight(options: MarkedHighlightOptions): JsAny
