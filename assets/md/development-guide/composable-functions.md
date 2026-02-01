# Composable functions

## Building UI structure

Composable functions (functions annotated with a `@Composable` annotation) are the main blocks of the user interface built with Kilua. The framework gives you functions for all standard HTML tags and also a lot of additional functions for more advanced components. All Kilua composable functions for the UI are extension functions of the `IComponent` interface. You can easily create your own components just by composing Kilua's basic functions.

```kotlin
@Composable
fun IComponent.mobileMenu(className: String) {
    nav(className, "mobile-menu") {
        ul {
            li {
                a("#features", "Features")
            }
            li {
                a("#learning", "Learning")
            }
        }
    }
}
```

When using composable functions provided by Kilua you will notice that most important and most often used attributes and properties are exposed directly as function parameters. For all other HTML properties Kilua uses very simple DSL. Unlike some other Compose-based UI frameworks by default it does not introduce concepts like Modifiers or Attribute builders. Instead, all HTML properties, attributes and CSS styles are managed by type-safe functions available directly in the component body (which is also an extension on the component type interface)

```kotlin
@Composable
fun IComponent.alert(text: String, className: String?, content: @Composable IDiv.() -> Unit = {}) {
    div(className) {
        role("alert")
        ariaLabel(text)
        margin(16.px)
        border(Border(1.px, BorderStyle.Solid, Color.Green))
        attribute("data-alert", "custom-alert")
        +text
        content()
    }
}
```

However, if you are familiar with Jetpack Compose you can use `kilua-jetpack` module, which contains an implementation of Jetpack-like layout composables, including `Box`, `Row` and `Column`. It also lets you use `Modifier` to set HTML properties and CSS styles.

## Entry points

You can use (a non-composable) `root()` function to start a composition tree. The first argument of this function should be an ID attribute of HTML tag, which must be present in the main `index.html` file.

```kotlin
root("root") {
    div {
    }
}
```

```html
<!DOCTYPE html>
<html>
...
<body>
<div id="root"></div>
</body>
</html>
```

You can also use a direct reference to some HTML element instead of an ID attribute, which might be useful when integrating external JavaScript components.

```kotlin
val element = document.createElement("div").also { document.body?.appendChild(it) }
root(element) {
    div {
    }
}
```

It is possible to use multiple `root()` calls and multiple composition trees in one application.

## Components references

The UI is typically built with composable functions using immutable interfaces. But the framework itself is internally based on mutable components that correspond to mutable DOM objects. You can access these components by using special composable functions, which return component references as values. These function have names ending with `Ref` suffix. So for example instead of using `button` you can use `buttonRef` function.

```kotlin
val button = buttonRef("First button") { // get the reference
    onClick {
        println("First button clicked")
    }
}
button("Set focus to the first button") {
    onClick {
        button.focus() // use the reference
    }
}
```

Accessing references to the components allows you to use some simple imperative code to make actions, which are much more complicated to achieve in a strictly declarative code.

> [!IMPORTANT]
> You might be wondering why create a second set of functions and not just make all standard functions return reference values (and just not use them when not required). The reason is Compose runtime optimizations. The functions which return values are never skipped during recompositions, so using them somehow degrades the application performance. Use these functions only when you really need them.
