import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.compose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kilua.rpc)
    alias(libs.plugins.kilua)
    alias(libs.plugins.vite.kotlin)
}

extra["mainClassName"] = "website.MainKt"

@OptIn(ExperimentalWasmDsl::class)
kotlin {
    jvmToolchain(21)
    jvm {
        compilerOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
        }
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        mainRun {
            mainClass.set(project.extra["mainClassName"]!!.toString())
        }
    }
    js(IR) {
        useEsModules()
        browser {
            commonWebpackConfig {
                cssSupport {
                    enabled = true
                }
                sourceMaps = false
            }
        }
        binaries.executable()
        compilerOptions {
            target.set("es2015")
        }
    }
    wasmJs {
        useEsModules()
        browser {
            commonWebpackConfig {
                cssSupport {
                    enabled = true
                }
                sourceMaps = false
            }
        }
        binaries.executable()
        compilerOptions {
            target.set("es2015")
        }
    }
    applyDefaultHierarchyTemplate()
    sourceSets {
        val commonMain by getting {
            dependencies {
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(libs.kilua.ssr.server)
                implementation(libs.ktor.server.cio)
                implementation(libs.ktor.server.compression)
            }
        }
        val webMain by getting {
            dependencies {
                implementation(npm("daisyui", libs.versions.daisyui.get()))
                implementation(npm("@tailwindcss/typography", libs.versions.tailwindcss.typography.get()))
                implementation(npm("marked-alert", libs.versions.marked.alert.get()))
                implementation(npm("marked-gfm-heading-id", libs.versions.marked.gfm.heading.id.get()))
                implementation(npm("marked-highlight", libs.versions.marked.highlight.get()))
                implementation(npm("highlight.js", libs.versions.highlightjs.get()))
                implementation(libs.kilua)
                implementation(libs.kilua.tailwindcss)
                implementation(libs.kilua.routing)
                implementation(libs.kilua.marked)
                implementation(libs.kilua.svg)
                implementation(libs.kilua.rest)
                implementation(libs.kilua.ssr)
                implementation(libs.koin.core)
                implementation(libs.ballast.core)
                implementation(libs.ballast.saved.state)
                implementation(libs.ballast.repository)
                implementation(libs.ballast.sync)
                implementation(libs.ballast.undo)
            }
        }
    }
}

composeCompiler {
    targetKotlinPlatforms.set(
        KotlinPlatformType.entries
            .filterNot { it == KotlinPlatformType.jvm }
            .asIterable()
    )
}

vite {
    autoRewriteIndex.set(true)
    plugin("@tailwindcss/vite", "tailwindcss", libs.versions.tailwindcss.asProvider().get())
    build {
        target = "es2020"
    }
    server {
        port = 3000
    }
}
