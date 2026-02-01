# Using Jetpack Compose API

The `kilua-jetpack` module provides a set of composable functions and utilities that mimic some parts of the Jetpack Compose UI toolkit API. It is designed to offer a familiar API for developers who have experience with Jetpack Compose on Android or Compose Multiplatform UI.

> [!NOTE]
> Composable functions provided by this module are following Jetpack Compose naming conventions, so they are using PascalCase instead of Kilua's usual camelCase style.

## Layouts

Kilua allows you to create layouts using `Column`, `Row` and `Box` composable functions, similar to Jetpack Compose. These functions help you arrange child composables in vertical, horizontal or overlapping manner. You can use various `Allignment` and `Arrangement` options to control the positioning and spacing of child elements.

```kotlin
Row(
    horizontalArrangement = Arrangement.spacedBy(20.px, Alignment.CenterHorizontally),
    verticalAlignment = Alignment.CenterVertically
) {
    Column {
        +"First column"
    }
    Column {
        +"Second column"
    }
    Column {
        +"Third column"
    }
}
```

## Modifiers

The module provides a `Modifier` class, which allows you to chain multiple modifier functions to customize the appearance and behavior of composables. You can use modifiers for setting all HTML attributes, CSS properties and events supported by Kilua. Jetpack specific modifiers like `align()`, `size()`, `heightIn()`, `fillMaxWidth()` etc. are also available.

```kotlin
Box(Modifier.size(500.px).border(1.px, BorderStyle.Solid, Color.Black)) {
    Box(Modifier.size(100.px).align(Alignment.TopCenter).background(Color.Red)) {
        +"TopCenter"
    }
    Box(Modifier.size(80.px).align(Alignment.CenterEnd).background(Color.Green)) {
        +"CenterEnd"
    }
    Box(Modifier.size(100.px).align(Alignment.BottomStart).background(Color.Yellow)) {
        +"BottomStart"
    }
    Box(Modifier.size(60.px).align(Alignment.Center).background(Color.Silver)) {
        +"Center"
    }
}
```

## Responsive layouts

You can also use a few useful functions inspired by Jetpack Compose, designed to help you create responsive layouts. These include `rememberBootstrapBreakpoint()`, `rememberTailwindcssBreakpoint()`, `rememberOrientation()`, `rememberMediaQueryAsState()` and `currentWindowSizeClass()`.

```kotlin
@Composable
fun IComponent.ResponsiveLayout() {
    val windowAdaptiveInfo = currentWindowSizeClass()
    val breakpoint by rememberBreakpoint()
    val tailwindcssBreakpoint by rememberTailwindcssBreakpoint()
    val orientation by rememberOrientation()

    div {
        +Modifier
            .width(
                when (windowAdaptiveInfo.widthSizeClass) {
                    WindowWidthSizeClass.Compact -> 50.px
                    WindowWidthSizeClass.Medium -> 100.px
                    WindowWidthSizeClass.Expanded -> 200.px
                }
            )
            .height(
                when (windowAdaptiveInfo.widthSizeClass) {
                    WindowWidthSizeClass.Compact -> 50.px
                    WindowWidthSizeClass.Medium -> 100.px
                    WindowWidthSizeClass.Expanded -> 200.px
                }
            )
            .background(Color.Red)
    }
    div {
        +Modifier
            .width(
                when (breakpoint) {
                    Breakpoint.Mobile -> 50.px
                    Breakpoint.SmallTablet -> 100.px
                    Breakpoint.Tablet -> 150.px
                    Breakpoint.Laptop -> 200.px
                    Breakpoint.Desktop -> 300.px
                }
            )
            .height(
                when (breakpoint) {
                    Breakpoint.Mobile -> 50.px
                    Breakpoint.SmallTablet -> 100.px
                    Breakpoint.Tablet -> 150.px
                    Breakpoint.Laptop -> 200.px
                    Breakpoint.Desktop -> 300.px
                }
            )
            .background(Color.Green)
    }

    div {
        +Modifier
            .width(
                when (tailwindcssBreakpoint) {
                    TailwindcssBreakpoint.DEFAULT -> 50.px
                    TailwindcssBreakpoint.SM -> 100.px
                    TailwindcssBreakpoint.MD -> 150.px
                    TailwindcssBreakpoint.LG -> 200.px
                    TailwindcssBreakpoint.XL -> 250.px
                    TailwindcssBreakpoint.XL2 -> 300.px
                }
            )
            .height(
                when (tailwindcssBreakpoint) {
                    TailwindcssBreakpoint.DEFAULT -> 50.px
                    TailwindcssBreakpoint.SM -> 100.px
                    TailwindcssBreakpoint.MD -> 150.px
                    TailwindcssBreakpoint.LG -> 200.px
                    TailwindcssBreakpoint.XL -> 250.px
                    TailwindcssBreakpoint.XL2 -> 300.px
                }
            )
            .background(Color.Blue)
    }
    div {
        +orientation.toString()
    }
}
```
