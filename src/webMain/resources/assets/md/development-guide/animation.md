# Animation

The `kilua-animation` module provides a set of composable functions and utilities to create smooth and performant animations. It is built on top of [Motion](https://motion.dev/) library, which combines the performance of the browser with the potential of a JavaScript engine. The design of the module is inspired by the [Compose Animation API](https://developer.android.com/develop/ui/compose/animation/introduction), making it familiar to Compose developers.

## Animated visibility

To animate the appearance and disappearance of a composable, you can use `animatedVisibility()` or `motionAnimatedVisibility()` composable functions. This functions allow you to specify enter and exit animations using animation specs. Both functions support typical transition types - fade, scale and translate (in all four directions).

The `animatedVisibility()` function is based on CSS transitions. The `CssTransition` class allows you to customize the duration and timing function of the transition.

```kotlin
var itemVisible by remember { mutableStateOf(false) }

animatedVisibility(
    itemVisible,
    CssTransition(type = TransitionType.TranslateLeft, duration = 2.seconds, timingFunction = CssTimingFunction.EaseInOut),
) {
    div {
        width(300.px)
        height(300.px)
        border(1.px, BorderStyle.Solid, Color.Red)
        +"Animated with CSS"
    }
}
```

The `motionAnimatedVisibility()` function uses Motion library for animations. The `MotionTransition` class allows you to specify different animation specs, including tween and two kind of spring animations (time based and physics based).

```kotlin
motionAnimatedVisibility(
    itemVisible,
    MotionTransition(type = TransitionType.Fade, animation = MotionAnimation.SpringTime(duration = 2.seconds)),
) {
    div {
        width(300.px)
        height(300.px)
        border(1.px, BorderStyle.Solid, Color.Red)
        +"Animated with Motion"
    }
}
```

## Value-based animations

The animation module provides a number of `animate*AsState` functions for animating a single value. You provide only the target value (or end value), and the API starts animation from the current value to the specified value. There are functions for animating `Float`, `Double`, `Int`, `Long`, `CssSize` and `Color` values. You can also specify the animation spec using `MotionAnimation` to customize the animation behavior.

```kotlin
var enabled by remember { mutableStateOf(true) }

val width: CssSize by animateCssSizeAsState(if (enabled) 100.px else 900.px)
val color: Color by animateColorAsState(if (enabled) Color.hex(0xff0000) else Color.hex(0x0000ff))

div {
    background(color)
    width(width)
    height(300.px)
    border(1.px, BorderStyle.Solid, Color.Blue)
    +"Animated block"
    onMouseEnter {
        enabled = false
    }
    onMouseLeave {
        enabled = true
    }
}
button("Toggle") {
    onClick {
        enabled = !enabled
    }
}
```

## Direct Motion usage

Kilua provides direct bindings for Motion API, including `animate()`, `animateMini()` and `scroll()` functions. You can use them to create complex animations of HTML styles, CSS variables, SVG paths, JavaScript objects, WebGL and more. Please refer to [Motion documentation](https://motion.dev/docs/animate) for more details and examples.

```kotlin
button("Rotate me") {
    onClick {
        animateMini("button", jsObjectOf("rotate" to "360deg"), jsObjectOf("duration" to "2"))
    }
}
```
