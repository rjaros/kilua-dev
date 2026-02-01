# Layout containers

Layout containers allow you to position your components in a different ways to create both simple and complex views. Different container functions can be stacked together to achieve even the most sophisticated layouts. Built-in containers are available in the `dev.kilua.panel` package.

## flexPanel

The `flexPanel` composable function allows you to display children components with all the power of [CSS Flexible Box Layout Module](https://www.w3.org/TR/css-flexbox/) W3C recommendation. In the flex layout model, the children components can be laid out horizontally or vertically, left to right, right to left, top to bottom or bottom to top. Children can change their sizes, either growing or shrinking. The specification is quite complex and most of the available CSS properties are supported with Kotlin enum values.

```kotlin
flexPanel(FlexDirection.Row, FlexWrap.Wrap, JustifyContent.FlexStart, AlignItems.Center, columnGap = 10.px) {
    div {
        order(3)
        +"Component 1"
    }
    div {
        order(1)
        +"Component 2"
    }
    div {
        order(2)
        +"Component 3"
    }
}
```

## hPanel, vPanel

Both`hPanel` and `vPanel` functions are convenient shortcuts for frequently used horizontal (left to right) and vertical (top to bottom) flexbox layouts.

```kotlin
hPanel(gap = 5.px) {
    div {
        +"Component 1"
    }
    div {
        +"Component 2"
    }
    div {
        +"Component 3"
    }
}
vPanel(gap = 5.px) {
    div {
        +"Component 1"
    }
    div {
        +"Component 2"
    }
    div {
        +"Component 3"
    }
}
```

## gridPanel

The `gridPanel` composable function allows you to display children components with the use of [CSS Grid Layout Module](https://www.w3.org/TR/css-grid/) W3C recommendation - a two-dimensional layout system, which allows the children to be positioned into arbitrary slots in a predefined flexible or fixed-size grid. This specification is even more complex than Flexbox, but is still supported mostly with Kotlin enum values and type-safe parameters.

```kotlin
gridPanel(columnGap = 5.px, rowGap = 5.px, justifyItems = JustifyItems.Center) {
    div {
        gridRow("1")
        gridColumn("1")
        +"Component 1, 1"
    }
    div {
        gridArea("1 / 2")
        +"Component 1, 2"
    }
    div {
        gridArea("2 / 1")
        +"Component 2, 1"
    }
    div {
        gridArea("auto")
        +"Component 2, 2"
    }
}
```

## splitPanel

> [!NOTE]
> The `splitPanel` function is defined in the `kilua-splitjs` module.

The `splitPanel` composable function divides the available space into two areas and provides a possibility to resize the panes by the user. Both directions (vertical and horizontal) are supported.

```kotlin
splitPanel {
    width(300.px)
    height(300.px)
    left {
        width(50.perc)
        pt("First panel")
    }
    right {
        width(50.perc)
        pt("Second panel")
    }
}
splitPanel(dir = Dir.Horizontal) {
    width(300.px)
    height(300.px)
    top {
        height(50.perc)
        pt("First panel")
    }
    bottom {
        height(50.perc)
        pt("Second panel")
    }
}
```

## lazyRow, lazyColumn

> [!NOTE]
> The `lazyRow` and `lazyColumn` functions are defined in the `kilua-lazy-layouts` module.

The `lazyRow` and `lazyColumn` composable functions are ported from the [Lazy layouts for Compose HTML](https://gitlab.com/opensavvy/ui/compose-lazy-html) project. The functions allow displaying large amounts of information by only loading the items that are necessary.

```kotlin
div {
    border(1.px, style = BorderStyle.Solid, color = Color.Red)
    height(100.px)
    width(600.px)
    overflowY(Overflow.Hidden)
    overflowX(Overflow.Auto)
    lazyRow({
        height(100.px)
        columnGap(2.px)
        alignItems(AlignItems.Center)
    }) {
        items(200) {
            div {
                width(30.px)
                height(30.px)
                border(1.px, BorderStyle.Solid, Color.Black)
                +"$it"
            }
        }
    }
}
```

## Bootstrap containers

Additional, Bootstrap based composables, including `accordeon`, `carousel`, `offcanvas` and `tabPanel` are available in the [kilua-bootstrap](development-guide/using-bootstrap) module.

## Jetpack containers

Additional Jetpack-like composables, including `Box`, `Row` and `Column` are available in the [kilua-jetpack](development-guide/using-jetpack-compose-api) module.
