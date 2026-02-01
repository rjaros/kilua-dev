# SVG images

The `kilua-svg` module allows you to render SVG images with Kotlin code. It provides an `svg` composable, which is an entry point for any SVG drawing and a bunch of different composable extension functions, which can be used to render different shapes (circles, rectangles, ellipses, lines, polylines, symbols, polygons, texts etc.) and effects (links, gradients, animations, filters etc.).

```kotlin
svg(viewBox = "0 0 20 20") {
    width(100.px)
    height(100.px)
    fill("currentColor")
    path("M10 5a1 1 0 0 1 1 1v3h3a1 1 0 1 1 0 2h-3v3a1 1 0 1 1-2 0v-3H6a1 1 0 1 1 0-2h3V6a1 1 0 0 1 1-1Z")
}
```

> [!TIP]
> It's also possible to use `*.svg` files directly as [static resources](development-guide/resources) using `img` components.
