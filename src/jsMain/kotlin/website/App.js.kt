package website

import dev.kilua.Hot

actual fun bundlerHot(): Hot? {
    return js("import.meta.webpackHot").unsafeCast<Hot?>() ?: js("import.meta.hot").unsafeCast<Hot?>()
}
