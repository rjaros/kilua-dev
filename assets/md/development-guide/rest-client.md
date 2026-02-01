# REST client

The `dev.kilua.rest.RestClient` class, located in the `kilua-rest` module, can be used to connect to any HTTP service. You can use remote services with both dynamic and type-safe calls (using `@Serializable` classes). The `RestClient` class has only a single `receive()` method, which uses the builder pattern for configuration and returns a `RestResponse<T>` object. A number of extension functions is defined, which allow you to make typical calls easier.

| Extension function | Result type                               |
|--------------------|-------------------------------------------|
| `requestDynamic()` | `RestResponse<JsAny>`                     |
| `request()`        | `RestResponse<T>` (`T` - type-safe value) |
| `callDynamic()`    | `JsAny?`                                  |
| `call()`           | `T` - type-safe value                     |

## Configuration

You can configure HTTP request both when creating a `RestClient` instance and also when calling individual methods.

```kotlin
val restClient = RestClient {
    headers = {
        listOf(
            "User-Agent" to "KiluaApp"
        )
    }
    baseUrl = "https://api.github.com"
    serializersModule = SerializersModule { // custom serializers for any data types 
        polymorphic(A::class) {
            subclass(B::class)
            subclass(C::class)
        }
    }
}

val searchResult: SearchResult = restClient.call("https://api.github.com/search/repositories", Query("kilua")) {
    method = HttpMethod.Post
    contentType = "application/json"
    headers = {
        listOf(
            "User-Agent" to "KiluaApp"
        )
    }
}
```

## Examples

Check the following examples of different http calls.

### Dynamic parameters, dynamic result

```kotlin
import dev.kilua.rest.callDynamic

val restClient = RestClient()
val result: JsAny? = restClient.callDynamic("https://api.github.com/search/repositories") {
    data = jsObjectOf("q" to "kilua")
}
```

### Dynamic parameters, type-safe result

```kts
import dev.kilua.rest.call

@Serializable
data class Repository(val id: Int, val full_name: String?, val description: String?, val fork: Boolean)

val restClient = RestClient()
val items: List<Repository> = restClient.call("https://api.github.com/search/repositories") {
    data = jsObjectOf("q" to "kilua")
    resultTransform = { it?.jsGet("items") }
}
```

### Type-safe parameters, dynamic result

```kotlin
import dev.kilua.rest.callDynamic

@Serializable
data class Query(val q: String?)

val restClient = RestClient()
val result: JsAny? = restClient.callDynamic("https://api.github.com/search/repositories", Query("kilua"))
```

### Type-safe parameters, type-safe result

```kotlin
import dev.kilua.rest.call

@Serializable
data class Query(val q: String?)
@Serializable
data class SearchResult(val total_count: Int, val incomplete_results: Boolean)

val restClient = RestClient()
val searchResult: SearchResult = restClient.call("https://api.github.com/search/repositories", Query("kilua"))
```

## RestResponse class

A wrapper `RestResponse` class is defined as:

```kotlin
public data class RestResponse<T>(val data: T?, val textStatus: String, val response: Response)
```

When using `receive()` ,`request()` or `requestDynamic()` functions, the returned `RestResponse` object gives you access to the returned data as well as the native `Response` object, which allows you to access the server response (e.g. to get HTTP header values and other information):

```kotlin
import dev.kilua.rest.requestDynamic

val restClient = RestClient()
val restResponse: RestResponse<JsAny> = restClient.requestDynamic("https://api.github.com/search/repositories") {
    data = jsObjectOf("q" to "kilua")
}
println(restResponse.response.headers.get("Content-Type")) // application/json; charset=utf-8
```
