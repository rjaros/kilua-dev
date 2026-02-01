package website.externals

import dev.kilua.marked.Marked
import dev.kilua.utils.obj
import kotlin.js.JsAny

external interface MarkedRenderer : JsAny {
    var link: (MarkedLinkParams) -> String
}

external interface MarkedExtension : JsAny {
    var renderer: MarkedRenderer
}

external interface MarkedLinkParams : JsAny {
    var href: String?
    var title: String?
    var text: String?
}

val markedLinks = obj<MarkedExtension> {
    renderer = obj {
        link = { params ->
            val targetAttr =
                if (params.href != null && (params.href!!.startsWith("https://") || params.href!!.startsWith("http://"))) {
                    """ target="_blank" rel="noopener noreferrer""""
                } else {
                    ""
                }
            val titleAttr = if (params.title != null) """ title="${params.title}"""" else ""
            """<a href="${params.href}"$titleAttr$targetAttr>${params.text}</a>"""
        }
    }
}

fun getMarked(): Marked {
    HighlightJs.registerLanguage("kotlin", hljsKotlin)
    HighlightJs.registerLanguage("html", hljsXml)
    HighlightJs.registerLanguage("javascript", hljsJavascript)
    HighlightJs.registerLanguage("plaintext", hljsPlaintext)
    HighlightJs.registerLanguage("toml", hljsToml)
    HighlightJs.registerLanguage("yaml", hljsYaml)
    return Marked(markedAlert(), markedGfmHeadingId(), markedHighlight(obj {
        emptyLangClass = "hljs"
        langPrefix = "hljs language-"
        highlight = { code, lang ->
            val supportedLang = if (HighlightJs.getLanguage(lang) != null) lang else "plaintext"
            HighlightJs.highlight(code, obj {
                language = supportedLang
            }).value
        }
    }), markedLinks)
}
