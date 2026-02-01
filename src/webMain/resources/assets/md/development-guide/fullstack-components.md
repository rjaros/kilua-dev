# Fullstack components

This section focuses on developing fullstack applications with [Kilua RPC](https://github.com/rjaros/kilua-rpc) companion library for [Ktor](https://ktor.io), [Jooby](https://jooby.io), [Spring Boot](https://spring.io/projects/spring-boot), [Javalin](https://javalin.io), [Vert.x](https://vertx.io) and [Micronaut](https://micronaut.io) server side web frameworks. There is a comprehensive [Kilua RPC guide](https://kilua.gitbook.io/kilua-rpc-guide) available, which contains all the information about the library and its usage.

This chapter focuses on fullstack components provided by the Kilua framework, which can be used to easily access remote data sources.

## Select Remote

The `selectRemote()` composable, provided by the `kilua-select-remote` module, can be used to render a select box with options loaded directly from the server. The component is bound directly to the method of the remote service. The method signature looks like this:

```kotlin
import dev.kilua.rpc.annotations.RpcService
import dev.kilua.rpc.SimpleRemoteOption

@RpcService
interface IDictionaryService {
    suspend fun dictionary(state: String?): List<SimpleRemoteOption>
}
```

The `state` parameter allows you to send optional, additional data to the backend service, with the help of the `stateFunction` parameter of the `selectRemote()` function.

The `SimpleRemoteOption` class is defined as:

```kotlin
@Serializable
data class SimpleRemoteOption(
    val value: String,
    val text: String? = null
)
```

and allows to send values and labels for every option.

To use `selectRemote()` form control, you initialize it with the `ServiceManager` instance and a callable reference to the right method.

```kotlin
selectRemote(
    serviceManager = getServiceManager(), 
    function = IDictionaryService::dictionary,
    stateFunction = { someState.toString() }
)
```

## Tom Select Remote

The `tomSelectRemote()` composable, provided by the `kilua-tom-select-remote` module, can be used to render an advanced select box with options loaded directly from the server. Unlike standard `tomSelect()` component (which can also load options from an AJAX source but needs a defined endpoint) `tomSelectRemote()` is bound directly to the method of the remote service. The method signature looks like this:

```kotlin
import dev.kilua.rpc.annotations.RpcService
import dev.kilua.rpc.RemoteOption

@RpcService
interface IDictionaryService {
    suspend fun dictionary(search: String?, initial: String?, state: String?): List<RemoteOption>
}
```

The `search` parameter is sent with the value entered by the user into the search box of the select control. It should be used to filter the returned list of options.

The `initial` parameter is sent with the initial value of the select control. If it's not null, the option bound to the given value should be returned (even if `search` is not null and does not match the initial value).

The `state` parameter allows you to send optional, additional data to the backend service, with the help of the `stateFunction` parameter of the `tomSelectRemote()` function.

The `RemoteOption` class is defined as:

```kotlin
@Serializable
data class RemoteOption(
    val value: String? = null,
    val text: String? = null,
    val className: String? = null,
    val subtext: String? = null,
    val icon: String? = null,
    val content: String? = null,
    val disabled: Boolean = false,
    val divider: Boolean = false
)
```

and allows to send full set of properties for every option.

To use `tomSelectRemote()` form control, you initialize it with the `ServiceManager` instance and a callable reference to the right method.

```kotlin
TomSelectRemote(
    serviceManager = getServiceManager(), 
    function = IDictionaryService::dictionary,
    stateFunction = { someState.toString() }
)
```

## Tom Typeahead Remote

The `tomTypeaheadRemote()` component, provided by the `kilua-tom-select-remote` module, can be used to render a typeahead text field with values loaded directly from the server. Unlike standard `tomTypeahead()` component (which can also load values from an AJAX source but needs a defined endpoint) `tomTypeaheadRemote()` is bound directly to the method of the remote service. The method signature looks like this:

```kotlin
import dev.kilua.rpc.annotations.RpcService

@RpcService
interface IValueService {
    suspend fun values(search: String?, state: String?): List<String>
}
```

The `search` parameter is sent with the value entered by the user in the text field. It should be used to filter the returned list of values.

The `state` parameter allows you to send optional, additional data to the backend service, with the help of the `stateFunction` parameter of the `tomTypeaheadRemote()` function.

To use `tomTypeaheadRemote()` form control, you initialize it with the `ServiceManager` instance and a callable reference to the right method.

```kotlin
tomTypeaheadRemote(
    serviceManager = getServiceManager(), 
    function = IValueService::values,
    stateFunction = { someState.toString() }
)
```

## Tabulator Remote

The `tabulatorRemote()` component, provided by the `kilua-tabulator-remote` module, is a specialized version of the `tabulator()` composable, dedicated for use with Kilua RPC fullstack interfaces. Unlike standard Tabulator component (which can also load data from an AJAX source but needs a defined endpoint) `tabulatorRemote()` is bound directly to the method of the remote service. The method signature looks like this:

```kotlin
import dev.kilua.rpc.annotations.RpcService
import dev.kilua.rpc.RemoteData
import dev.kilua.rpc.RemoteFilter
import dev.kilua.rpc.RemoteSorter

@Serializable
data class Row(val column1: String, val column2: String, val column3: String)

@RpcService
interface IRowDataService {
    suspend fun rowData(page: Int?, size: Int?, filter: List<RemoteFilter>?, sorter: List<RemoteSorter>?, state: String?): RemoteData<Row>
}
```

This model is prepared for server side pagination, sorting, filtering and also receiving external state, but the parameters are nullable, and will be sent only when configured by the appropriate `TabulatorOptions`.

```kotlin
tabulatorRemote(
    getServiceManager(),
    IRowDataService::rowData,
    { someState.toString() },
    TabulatorOptions(
        layout = Layout.FitColumns,
        pagination = true,
        paginationMode = PaginationMode.Remote,
        paginationSize = 3,
        filterMode = FilterMode.Remote,
        sortMode = SortMode.Remote,
        columns = listOf(
            ColumnDefinition("Column 1", Row::column1.name, headerFilter = Editor.Input),
            ColumnDefinition("Column 2", Row::column2.name),
            ColumnDefinition("Column 3", Row::column3.name)
        )
    ), serializer = serializer()
)
```

The `page` parameter contains the current requested page, the `size` parameter - the number of requested rows, the `filter` and `sorter` parameters contain values of selected filters and sorters. The returned data is always wrapped into `RemoteData` class, defined as:

```kotlin
@Serializable
data class RemoteData<T>(val data: List<T> = listOf(), val lastPage: Int = 0, val lastRow: Int? = null)
```

You can ignore `lastPage` and `lastRow` values, if the `tabulatorRemote()` component is not configured for remote pagination.
