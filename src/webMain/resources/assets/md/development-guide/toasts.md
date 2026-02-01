# Toasts

The `kilua-toastify` module contains ready to use functions to display toast messages - small, non-disruptive notification windows, which disappear automatically. To display a toast message just call `toast()` function with one or more parameters.

```kotlin
import dev.kilua.toastify.toast
import dev.kilua.toastify.ToastType

toast("This is a simple toast message")
toast("This is a success message", type = ToastType.Success)
toast("This is an error message", type = ToastType.Error)
```

Additional parameters allow you to customize the toast message further. You can set a custom duration (for how long the toast should be visible), position on the screen, whether to show a close button and some other options.

```kotlin
import dev.kilua.toastify.toast
import dev.kilua.toastify.ToastType
import kotlin.time.Duration.Companion.seconds

toast("This is an error message", type = ToastType.Error, duration = 10.seconds, 
    position = ToastPosition.TopLeft, close = true)
```

> [!TIP]
> When using Bootstrap module, you can use [Bootstrap toasts](development-guide/using-bootstrap) instead.