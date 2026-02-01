# Interoperability with JavaScript

Kilua and kotlin-wrappers bindings give you a unified way to work with JavaScript modules from the `common` code using external declarations. They give you access to `kotlin.js.JsAny` interface from Kotlin/WasmJS standard library. When using Kilua you will notice a lot of use of `JsAny` interface, because it's the only way to build Kotlin/WasmJS compatible code. The same applies to types such as `JsArray`, `JsString`, `JsNumber` and `JsBoolean`, which are just aliases for standard types in JS target but are totally different in WasmJS.

When declaring your own external wrapper types, always extend `JsAny` to make sure your externals are compatible with WasmJS target.

## Dynamic typing

Sometimes, especially when migrating code written for Kotlin/JS, you might want to use some `dynamic` types. Unfortunately `dynamic` is not supported in Kotlin/Wasm and also not available in Kilua. Instead, you should use `JsAny`, and use extension functions `jsGet` and `jsSet` defined in Kilua. For instance instead of writing this Kotlin/JS code:

```kotlin
val someObject: dynamic = someFunction()
console.log(someObject.property1.property2)
someObject.property3 = "Some value"
```

you can write this in Kilua:

```kotlin
val someObject: JsAny = someFunction()
console.log(someObject.jsGet("property1")!!.jsGet("property2"))
someObject.jsSet("property3", "Some value".toJsString())
```

## Native JavaScript objects

Kilua also offers some helper functions to automatically generate a native JS objects from external declarations or string-based mappings of different types.

```kotlin
val emptyObject: JsAny = obj()

external interface Test: JsAny {
    var field1: String
    var field2: Int
}

val someObject: Test = obj<Test> {
    field1 = "test"
    field2 = 1
}

val map = mapOf("field1" to "test", "field2" to 1)

val jsObject1: JsAny = map.toJsAny()

val jsObject2: JsAny = jsObjectOf(
    "a" to mapOf(
        "b" to mapOf(
            "c" to "c",
            "d" to "d"
        ),
        "array" to listOf(1, 2)
    ),
    "isCorrect" to true,
    "pi" to 3.14
)
```

The `toJsAny()` extension and `jsObjectOf()` function support all basic Kotlin types - `String`, `Int`, `Double`, `Boolean`, `Array<*>`, `List<*>` and `Map<*,*>` and also support complex types.

## Importing modules and resources

Kilua applications are developed with `useEsModules()` configuration option to support ECMAScript modules. If you have worked with some other Kotlin/JS frameworks before, you might be familiar with the `require()` function, but Kilua configuration doesn't allow you to use it for importing modules. Instead, you should use `@JsModule` annotation. This annotation is used both for external NPM modules and local resources (like CSS files, images or translation files).

This is how you can use an example NPM module (JQuery):

```kotlin
import kotlin.js.JsModule

external interface JQueryStatic: JsAny {
    fun ajax(settings: JsAny)
}

external class JQuery : JsAny {
    fun html(html: String)
}

@JsModule("jquery")
external val jQuery: JQueryStatic

@JsModule("jquery")
external fun jQuery(selector: String): JQuery
```
