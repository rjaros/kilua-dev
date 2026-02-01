package website.externals

import kotlin.js.JsAny
import kotlin.js.JsModule

@JsModule("highlight.js/lib/languages/xml")
external val hljsXml : JsAny

@JsModule("highlight.js/lib/languages/ini")
external val hljsToml : JsAny

@JsModule("highlight.js/lib/languages/yaml")
external val hljsYaml : JsAny

@JsModule("highlight.js/lib/languages/kotlin")
external val hljsKotlin : JsAny

@JsModule("highlight.js/lib/languages/javascript")
external val hljsJavascript : JsAny

@JsModule("highlight.js/lib/languages/plaintext")
external val hljsPlaintext : JsAny
