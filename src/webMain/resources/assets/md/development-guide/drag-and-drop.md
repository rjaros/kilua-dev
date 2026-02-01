# Drag and drop

All Kilua components have built-in support for HTML5 drag and drop. Any component can be draggable and any other component can play a role of a drop target. To make a component draggable just call `setDragDropData` method. You can set data format (it can be "text/plain" or other mime-type) and the data itself.

```kotlin
div {
    border(1.px, BorderStyle.Solid, Color.Black)
    width(100.px)
    height(100.px)
    setDragDropData("text/plain", "Element")
}
```

To create a drop target call `setDropTargetData` method. Use the format parameter to allow dropping only selected types of draggable. You can access the data transferred from the source element in the callback function.

```kotlin
div {
    width(200.px)
    height(200.px)
    setDropTargetData("text/plain") { data ->
        println("Dropped data: $data")
    }
}
```
