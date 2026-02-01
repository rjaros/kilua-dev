package website

internal external val kilua_build_mode: String

val isDevelopmentBuild = kilua_build_mode == "development"
