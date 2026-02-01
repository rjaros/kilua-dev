# Working with CSS

Every Kilua component has a full range of properties based on CSS specification. Most of them are 100% type-safe - based on enumeration values, dedicated classes and functions. You can of course use custom CSS stylesheets and assign predefined classes to your components, but Kilua gives you a choice. With CSS properties you can style any component's size, position, margins, paddings, borders, colors, backgrounds, text and fonts with pure Kotlin code.

```kotlin
div {
    margin(10.px)
    padding(20.px)
    fontFamily("Times New Roman")
    fontSize(32.px)
    fontStyle(FontStyle.Oblique)
    fontWeight(FontWeight.Bolder)
    fontVariant(FontVariant.SmallCaps)
    textDecoration(TextDecoration(TextDecorationLine.Underline, TextDecorationStyle.Dotted, Color.Red))
    +"A label with custom CSS styling"
}
```

## Custom properties

Not all CSS properties are supported with dedicated functions. Some, less frequently used, newly added to the specification or those whose values cannot be easily described in a type-safe manner, can be accessed using the `style()` function with raw `String` parameters:

```kotlin
div {
    width(100.px)
    height(100.px)
    style("scale", "1.25 0.75")
}
```

## CSS units

Kilua supports all CSS units as an extension properties on `Number` type. So you can specify dimensions, sizes, position and thickness with such example notations: `50.px`, `12.pt`, `2.em`, `90.perc`, `100.vh`, `1.49.em` etc. In addition `auto`, `normal`, `initial` and `inherit` values are also supported.

## Colors

Kilua offers a few convenient ways to specify colors. You can use methods defined in the `Color` class to create values with  hexadecimal `Int` literals or direct RGB(A) values. You can also use predefined constants for all CSS color names.

```kotlin
div {
    color(Color.hex(0x0000ff))
    color(Color.rgb(0, 0, 255))
    color(Color.rgba(0, 0, 255, 128))
    color(Color.Blue)
    color(Color("blue"))
}
```

## CSS helper classes

To specify borders, backgrounds, text decorations, text shadows, box shadows, transitions and list styles you can use the dedicated classes.

```kotlin
div {
    border(Border(1.px, BorderStyle.Solid, Color.Black))
    background(
        Background(
            Color.hex(0xcccccc),
            "img/kotlin.png",
            50.perc,
            50.perc,
            size = BgSize.Contain,
            repeat = BgRepeat.NoRepeat,
            attachment = BgAttach.Fixed
        )
    )
    textDecoration(
        TextDecoration(TextDecorationLine.Underline, TextDecorationStyle.Dotted, Color.Red)
    )
    textShadow(TextShadow(2.px, 2.px, blurRadius = 1.px, color = Color.Black))
    boxShadow(BoxShadow(2.px, 2.px, blurRadius = 1.px, spreadRadius = 1.px, color = Color.Black))
}
```

There are also some extension functions declared, that can be imported to write the same code easier:

```kotlin
import dev.kilua.html.helpers.TagStyleFun.Companion.background
import dev.kilua.html.helpers.TagStyleFun.Companion.border
import dev.kilua.html.helpers.TagStyleFun.Companion.textDecoration
import dev.kilua.html.helpers.TagStyleFun.Companion.textShadow
import dev.kilua.html.helpers.TagStyleFun.Companion.boxShadow

div {
    border(1.px, BorderStyle.Solid, Color.Black)
    background(
            Color.hex(0xcccccc),
            "img/kotlin.png",
            50.perc,
            50.perc,
            size = BgSize.Contain,
            repeat = BgRepeat.NoRepeat,
            attachment = BgAttach.Fixed
    )
    textDecoration(TextDecorationLine.Underline, TextDecorationStyle.Dotted, Color.Red)
    textShadow(2.px, 2.px, blurRadius = 1.px, color = Color.Black)
    boxShadow(2.px, 2.px, blurRadius = 1.px, spreadRadius = 1.px, color = Color.Black)
}

```

## Global style functions

When the CSS properties are set directly inside the component, the corresponding style attributes are inlined in the generated HTML code. Sometimes it's better to define a CSS class, which can be reused by other parts of the UI. In Kilua you can do this by using the `globalStyle()` function. It creates a CSS class which can be used by other components.

```kotlin
val myStyle = globalStyle {
    border(Border(1.px, BorderStyle.Solid, Color.Gray))
    width(200.px)
    height(200.px)
    margin(10.px)
}
div(myStyle) {
    +"A div with custom CSS styling"
}
div(myStyle) {
    +"Another div with custom CSS styling"
}
```

By default, the class name is automatically generated, but you can use your own with optional parameters.

```kotlin
val rectangleClass = globalStyle(".rectangle") {
    width(200.px)
    height(100.px)
}

div(rectangleClass) {
    +"A rectangle"
}
```

Styles can also be nested with a `style()` calls to create CSS subclasses.

```kotlin
val boxStyle = globalStyle {
    border(Border(1.px, BorderStyle.Solid, Color.Gray))
    width(200.px)
    height(200.px)

    style("h1") {
        color(Color.Blue)
    }
}
div(boxStyle) {
    h1 {
        +"Header"
    }
}
```

You can also easily use CSS pseudo-classes or media queries.

```kotlin
val hover = globalStyle(pClass = PClass.Hover) {
    color(Color.Yellowgreen)
}
div(hover) {
    +"Hover me"
}
```

## Local style functions

Sometimes you want to create a style dedicated for a single component, but can't use inline styles because you need to use features only available in the CSS stylesheet (like pseudo-classes, pseudo-elements or media queries). You can generate a CSS rule and apply that rule to your component by using `style()`function.

```kotlin
range {
    cursor(Cursor.Pointer)
    style {
        background(Color.hex(0xdddddd))
        pElement("-webkit-slider-thumb") {
            width(20.px)
            height(20.px)
            background(Color.hex(0x8758ff))
        }
    }
}
```
