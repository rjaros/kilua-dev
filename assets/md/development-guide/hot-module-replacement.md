# Hot Module Replacement

When developing with JS target you can use the HMR (Hot Module Replacement) feature of [Webpack](https://webpack.js.org/concepts/hot-module-replacement/) or [Vite](https://vite.dev). HMR can significantly speed up development by updating browser content automatically (without page reload) after changes are made in the source code. It also allows you to retain the state of the application. Just override the `start` method with a `state` parameter.

```kotlin
import dev.kilua.Application
import dev.kilua.Hot
import dev.kilua.compose.root
import dev.kilua.startApplication

class App : Application() {

    override fun start(state: String?) {
        root("root") {
            // TODO
        }
    }

    override fun dispose(): String? {
        return null
    }

}

fun main() {
    startApplication(::App, bundlerHot())
}

fun bundlerHot(): Hot? {
    return js("import.meta.webpackHot").unsafeCast<Hot?>() ?: js("import.meta.hot").unsafeCast<Hot?>()
}
```

> [!NOTE]
> The `bundlerHot()` function contains JS specific code and can't be used in the `common` code. Declare a simple expect/actual function `expect fun bundlerHot(): Hot?` if you want to use HMR with shared JS/WasmJS sources sets.

The HMR module calls the `start` method after every change in the source code, and this method is responsible for recreating the user interface.

In case of a need to retain the state of the application, it should be returned as a `String` (e.g. serialized JSON) from the`dispose` method. It will be sent back to the application in the `state` parameter of the `start` method.
