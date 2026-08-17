package website

import dev.kilua.utils.isDom

private fun importMetaEnvMode(): JsString? = js("import.meta.env.MODE")

val isDevelopmentBuild = isDom && importMetaEnvMode()?.toString() == "development"
