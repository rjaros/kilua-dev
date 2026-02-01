# Events

## DOM events

Kilua allows you to listen to all the standard DOM events and also some custom events from different components. You can use dedicated functions for the most popular events:

* `onClick`
* `onContextmenu`
* `onDblclick`
* `onChange`
* `onInput`
* `onFocus`
* `onBlur`
* `onKeydown`
* `onKeyup`
* `onKeypress`
* `onTouchStart`
* `onTouchEnd`
* `onTouchCancel`
* `onMouseDown`
* `onMouseUp`
* `onMouseEnter`
* `onMouseLeave`
* `onMouseOver`
* `onMouseOut`
* `onMouseMove`
* `onPointerDown`
* `onPointerUp`

For other events you can use `onEvent<Event>(name: String)` function. All these functions are declared as `@Composable`, and they automatically add and remove event listeners when entering or leaving the composition.

```kotlin
button("Click me") {
    onClick {
        console.log("clicked!")
    }
}

div {
    +"Mouse zone"
    onMouseEnter {
        console.log("Mouse enter")
    }
    onMouseLeave {
        console.log("Mouse leave")
    }
}

div {
    width(50.px)
    height(50.px)
    overflow(Overflow.Scroll)
    +"Some scrollable content"
    onEvent<Event>("scroll") {
        console.log("Scroll top: ${element.scrollTop}")
        console.log("Scroll left: ${element.scrollLeft}")
    }
}
```

> [!TIP]
> You can also use `onEvent()` to create listeners for custom events, dispatched by some components like `Tabulator`, `TabPanel` and `SplitPanel` or by your own code.

## Non-composable context

If you want to add event listeners without compose context you can use a non-composable variants of these functions with names ending with `Direct` (e.g. `onClickDirect`, `onEventDirect`). In such cases you are also responsible for removing event listeners by using `removeEventListener` method.

```kotlin
val buttonRef = buttonRef("My button")

button("Add listener") {
    onClick {
        buttonRef.onClickDirect {
            console.log("My button clicked")
        }
    }
}
button("Remove listener") {
    onClick {
        buttonRef.removeEventListener("click")
    }
}
```

When using `removeEventListener` method, you can give only the event name to remove all listeners for that event, but you can also pass the listener ID, returned by the `onClickDirect` method.

## Suspending listeners

You can easily use suspending functions inside most often used event handlers by using `onClickLaunch`, `onChangeLaunch` and `onInputLaunch` functions. There is also `onEventLaunch` function, which can be used for all other event types.

```kotlin
button("Sleep") {
    onClickLaunch { 
        delay(1.seconds) // suspending function
    }
}
```
