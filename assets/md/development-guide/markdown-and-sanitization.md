# Markdown and sanitization

## Markdown rendering

Kilua support for Markdown code is based on the lightweight and fast [Marked](https://marked.js.org/) library. You need to add `kilua-marked` module to your dependencies to use it. The module provides a `parseMarkdown()` function, which takes Markdown code and returns HTML code. Optionally you can pass a `MarkedOptions` instance to customize rendering (check[ Marked options documentation](https://marked.js.org/using_advanced#options) for details). To actually render the HTML you can use `rawHtml` or `rawHtmlBlock` composables.

```kotlin
@JsModule("./help.md")
external object HelpMd : LocalResource

div {
    rawHtml(parseMarkdown(HelpMd.content, MarkedOptions(pedantic = true)))
}
```

## Using Marked extensions

The Marked library supports a number of [extensions](https://marked.js.org/using_advanced#extensions), which can also be used with Kilua.

Firs, you need to add appropriate NPM libraries to your dependencies:

```kotlin
dependencies {
    implementation(npm("marked-alert", "*"))
    implementation(npm("marked-custom-heading-id", "*"))
}
```

Next, declare external functions in your code and use `useExtension` function from the `kilua-marked` module to initialize extensions.

```kotlin
@JsModule("marked-alert")
external fun markedAlert(options: JsAny): JsAny

@JsModule("marked-custom-heading-id")
external fun markedCustomHeadingId(): JsAny

class App : Application() {
    init {
        useExtension(markedAlert(jsObjectOf("className" to "alert")))
        useExtension(markedCustomHeadingId())
    }
}
```

After that, these extensions will be automatically used by the `parseMarkdown()` function.

## HTML sanitization

Markdown is often used to render content from external sources (e.g. user comments). You need to be aware that Marked library doesn't sanitize the output HTML. If you are processing potentially unsafe strings, it's important to filter for possible XSS attacks. The framework provides `kilua-sanitize-html` module based on the [sanitize-html](https://github.com/apostrophecms/sanitize-html) library, which can be easily used together with Markdown rendering. The module provides a `sanitizeHtml()` function, and you can customize sanitization process by passing an optional `SanitizeHtmlOptions` instance (check the [project documentation](https://github.com/apostrophecms/sanitize-html?tab=readme-ov-file#default-options) for details).

```kotlin
div {
    rawHtml(sanitizeHtml(parseMarkdown(userComment)))
}
```
