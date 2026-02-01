# Using Tabulator

Kilua Tabulator component is based on awesome [Tabulator](http://tabulator.info) library. It allows you to create interactive and reactive tables, with advanced sorting, filtering and editing capabilities. This component is contained in `kilua-tabulator` module (you need to use `TabulatorModule` initializer and also one of CSS module initializers - see [modules](development-guide/modules)). Kilua adds Kotlin type-safe bindings for most of Tabulator API, but you should get familiar with [Tabulator documentation](https://tabulator.info/docs/6.3) to achieve best results.

> [!NOTE]
> At the moment these functionalities are not supported: grouping, column calculations, download. Please fill a [feature request](https://github.com/rjaros/kilua/issues/new) if you require any of these.

## Creating a table

To create a table use `tabulator()` composable function. Although all parameters have default values, you will usually want to specify some options with `TabulatorOptions` class. The table data can be specified in a few different ways - with local Kotlin collections or remote AJAX URL.

### Local Kotlin list

Serializable data class is a preferred representation for a single row of data.

```kotlin
@Serializable
data class Person(val name: String, val age: Int, val city: String)

val model = remember {
    mutableStateListOf(
        Person("John", 30, "New York"),
        Person("Alice", 25, "Los Angeles"),
        Person("Mary", 45, "Chicago")
    )
}

tabulator<Person>(
    model,
    options = TabulatorOptions(
        layout = Layout.FitColumns,
        columns = listOf(
            ColumnDefinition("Name", "name"),
            ColumnDefinition("Age", "age"),
            ColumnDefinition("City", "city")
        )
    )
)
```

### Remote AJAX URL

Tabulator tables can get the data from the remote endpoint. You can find more information about available options in the [Tabulator documentation](https://tabulator.info/docs/6.3/data#ajax). All these options, including ajax sorting, pagination and filtering can be used with Kilua component.

```kotlin
tabulator(
    options = TabulatorOptions(
        ajaxURL = "/remote/tableData",
        ajaxConfig = "POST".toJsString(),
        ajaxContentType = "json".toJsString(),
        filterMode = FilterMode.Remote,
        sortMode = SortMode.Remote,
        pagination = true,
        paginationMode = PaginationMode.Remote,
        layout = Layout.FitColumns,
        columns = listOf(
            ColumnDefinition("Name", "name"),
            ColumnDefinition("Age", "age"),
            ColumnDefinition("City", "city")
        )
    )
)
```

## Formatting the data

### Built-in formatters

You can use many different formatters to present the data in the tabulator cells. Just pass the desired `Formatter` as a parameter for the `ColumnDefinition` class.

```kotlin
val model = remember {
    mutableStateListOf(
        Book("Fairy tales", "Hans Christian Andersen", 1836, 4),
        Book("Don Quijote De La Mancha", "Miguel de Cervantes", 1610, 4),
        Book("Crime and Punishment", "Fyodor Dostoevsky", 1866, 3),
        Book("In Search of Lost Time", "Marcel Proust", 1920, 5)
    )
}

val tabulator = tabulator<Book>(
    model, options = TabulatorOptions(
        layout = Layout.FitColumns,
        columns = listOf(
            ColumnDefinition("Title", "title"),
            ColumnDefinition("Author", "author"),
            ColumnDefinition("Year", "year"),
            ColumnDefinition("Rating", "rating", formatter = Formatter.Star)
        )
    )
)
```

You can find more information about formatters configuration in the [Tabulator docs](https://tabulator.info/docs/6.3/format).

### Custom formatters

You can use Kilua composables to display data in the Tabulator cells. You define the component with the `formatterComponentFunction` property of the `ColumnDefinition` class. When the Tabulator component is bound to the Kotlin data source, this function gives you also direct and type-safe access to the Kotlin data model for the current row.

```kotlin
@Serializable
data class Employee(
    val name: String?,
    val position: String?,
    val office: String?,
    val active: Boolean = false,
    val startDate: LocalDate?,
    val salary: Int?
)
// ...
val model: List<Employee> = // ...
// ...
tabulator<Employee>(model, options = TabulatorOptions(layout = Layout.FitColumns,
    columns = listOf(
    // ...
      ColumnDefinition(
        "Start date",
        "startDate", formatterComponentFunction = { _, _, data: Employee ->
            span {
                +data.startDate?.toString()
            }
        }
      )
    )
))
```

