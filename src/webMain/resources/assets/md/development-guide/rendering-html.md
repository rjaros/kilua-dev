# Rendering HTML

## HTML tags

Kilua provides many functions to render all standard HTML tags. Almost all of them have names directly corresponding to tag names. The exceptions are these HTML tags:

* `<input>`, which can be rendered using many different functions, dedicated for different types of inputs (e.g. text, checkbox, radiobutton, upload, color etc.)
* `<map>`,  rendered with a `mapTag()` function (to avoid clash with Kotlin maps)
* `<object>`,  rendered with an  `objectTag()` function (to avoid clash with Kotlin `object` keyword)
* `<var>`, rendered with a `varTag()` function (to avoid clash with Kotlin `var` keyword)

> [!TIP]
> There is a dedicated `kilua-svg` module to render `<svg>` tags containing vector graphics.

You can also render custom HTML tags, by using `tag()` function.

```kotlin
tag("my-custom-tag") {
    id("my-id")
}
```

## HTML attributes

Most HTML attributes are optional, but for some HTML tags they are almost always used (e.g. `<img src="...">` or `<a href="...">`). In Kilua such attributes are usually available directly as composable functions parameters.

```kotlin
div {
    a("https://kotlinlang.org/", target = "_blank") {
        img("img/kotlin.png", alt = "Kotlin")
    }
}
```

Some attributes can only be used with functions inside the component body.

```kotlin
a("/logout", icon = "bi bi-box-arrow-right") {
    title("Logout")
    ariaLabel("Logout")
}
```

Attributes not supported directly can be declared using an `attribute()` function.

```kotlin
button(className = "navbar-toggler") {
    attribute("data-bs-toggle", "collapse")
    attribute("data-bs-target", "#navbarNav")
    attribute("aria-controls", "navbarNav")
    attribute("aria-expanded", "false")
}
```

## Text content

You can declare textual content of HTML tags with a `textNode()` function, which also has a convenient extension operator `+`:

```kotlin
div {
    p {
        textNode("First paragraph")
    }
    p {
        +"Second paragraph"
    }
}
```

For some often used cases Kilua defines dedicated functions (by adding `t` to the name), which take the text content as the first parameter - these are: `bt()`, `divt()`, `emt()`, `h1t()`, `h2t()`, `h3t()`, `h4t()`, `h5t()`, `h6t()`, `it()`, `lit()`, `pt()`, `pret()`, `st()`, `smallt()`, `spant()`, `strongt()`, `subt()`, `supt()`, `ut()`, `tdt()`, `tht()`.

```kotlin
div {
    pt("Paragraph content")
}
```

## Raw HTML code

The text content rendered with `textNode()` function (or `+` operator) is always rendered as plain text (any HTML code is escaped). But you can also render raw HTML code with the help of `rawHtml()` and `rawHtmlBlock()` functions. The first one renders rich content inside an additional `<span style="display: contents;"></span>` tag and the second one inside a `<div style="display: contents;"></div>` tag. You can choose whether you need an inline or a block element based on your HTML structure.

```kotlin
div {
    rawHtml("Some rich text <b>with bold</b> and <i>italic</i>.")
}
```

> [!CAUTION]
> When using raw HTML make sure your code is safe from any XSS vulnerabilities. If you need to display some external data, always sanitize it before rendering with some reliable library. You can use `kilua-sanitize-html` module, which is based on proven [sanitize-html](https://github.com/apostrophecms/sanitize-html) project.
