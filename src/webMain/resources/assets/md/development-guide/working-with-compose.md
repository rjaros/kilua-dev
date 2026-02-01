# Working with Compose

## State management

Kilua is based on Compose Multiplatform runtime, and you can easily use all Compose state management features. In short, you can create a mutable state using Compose functions and the UI content which is created based on this state will be automatically updated. There is no need for any manual bindings or optimizations, by default only the parts of the UI which are using the changed state will be updated. If you are not familiar with Compose you should definitely start with the official guide - [https://developer.android.com/develop/ui/compose/state](https://developer.android.com/develop/ui/compose/state)

This would be a standard "counter" example created with Kilua

```kotlin
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import dev.kilua.compose.root
import dev.kilua.html.button
import dev.kilua.html.div

root("root") {
    var counter by remember { mutableStateOf(0) }

    div {
        +"Counter value: $counter"
    }

    div {
        button("Increment counter") {
            onClick {
                counter++
            }
        }
        button("Decrement counter") {
            onClick {
                counter--
            }
        }
    }
}
```

## Lifecycle and side effects

Kilua uses the same architectural model as all others Compose based frameworks. This includes the concepts of a composition, recompositions, lifecycle of composables and side effects. You should get familiar with these concepts with the official Compose guides:

* [https://developer.android.com/develop/ui/compose/lifecycle](https://developer.android.com/develop/ui/compose/lifecycle)
* [https://developer.android.com/develop/ui/compose/side-effects](https://developer.android.com/develop/ui/compose/side-effects)
