# Browser APIs

Kilua applications are in general designed to run inside the web browser. But unlike with most other Kotlin web frameworks you write Kilua code inside a `common` sources set. What's more, you want your code to be compatible with both JS and Wasm/JS targets and still access various browser APIs and interfaces. Unfortunately the Kotlin compilers and standard libraries for JS and Wasm/JS are a bit different and not compatible with each other when it comes to JavaScript interoperability. As a result, you can't access any of the standard browser APIs from the `common` code.

This problem is solved by the [kotlin-wrappers](https://github.com/JetBrains/kotlin-wrappers) project, which provides excellent bindings for the standard browser and web APIs, compatible with both Kotlin/JS and WasmJS targets. Check the API documentation of the project for full reference.

When creating an SSR application (an application designed to be rendered on the server side as well) you need to realize the code of your application will be also executed in the Node.js environment. Node.js is not a web browser, and you can't access browser APIs. You can use the `isDom` global variable (or `renderConfig.isDom` component property) to check if your code is running in the browser or in Node.js. In typical scenarios with SSR, you should check the DOM availability whenever you interact directly with browser API or external JavaScript components.

```kotlin
code(className = "code-blocks-selector") {
    attribute("theme", "darcula")
    // other attributes...
    LaunchedEffect(Unit) {
        if (renderConfig.isDom) { // Execute external function only when running in the browser
            KotlinPlayground(".code-blocks-selector")
        }
    }
}
```
