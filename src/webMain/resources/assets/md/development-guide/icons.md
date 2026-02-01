# Icons

Kilua has built-in support for free font icons from the [Font Awesome](https://fontawesome.com) project (you have to include `kilua-fontawesome` module) and from the [Bootstrap Icons](https://icons.getbootstrap.com/) project (with `kilua-bootstrap-icons` module). Composable functions for buttons, links, drop-downs, tabs and accordion have an `icon` parameter, which can be set to one of many available icon names. Kilua supports all free Font Awesome style prefixes - Solid (`fas`), Regular (`far`) and Brands (`fab`). You can check icon availability at [Font Awesome gallery page](https://fontawesome.com/search?ic=free).

```kotlin
button("A button with an icon", "fas fa-asterisk")
a("https://google.com", "A link with an icon", "fab fa-google")
```

There is also a dedicated `atom()`composable function, which can be used to just render an icon with some optional text label.

```kotlin
atom("Some label with an icon", "fas fa-plus", separator = " ", iconFirst = false)
```
