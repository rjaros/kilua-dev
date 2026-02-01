# Forms

Forms are one of the most important parts of GUI of many applications, especially the ones which collect and manage data
from the users. Traditionally handling HTML forms is a complex process, composed of many small steps like placing HTML
form controls on the page, setting and displaying initial data, reading user data from individual HTML elements and
finally transforming and validating the data. It gets even worse if you are using custom visual components for complex
data types - date, time, rich text, multiple select, uploaded files etc.

Kilua lets you work with forms in a very simple, consistent and efficient way. It hides all complex data transformations
inside the framework logic and offers you a fully type-safe binding between your data and your forms. It has support for
many different, both simple and complex, form controls. And it gives you ready to use data validation.

## Form data model

Form data is modeled with a standard Kotlin data class enhanced with `@Serializable` annotation
from [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization) library. Every field of this model class
holds the value of one input item rendered within a form. The model support basic data types: `String`, `Number`(
including `Int`and `Double`), `Boolean`, `LocalDate`, `LocalTime`, `LocalDateTime` and also a dedicated type
`List<KFile>` (a list of uploaded files).

```kotlin
@Serializable
data class FormModel(
    val text: String? = null,
    val password: String? = null,
    val password2: String? = null,
    val textarea: String? = null,
    val richtext: String? = null,
    val date: LocalDate? = null,
    val time: LocalTime? = null,
    val checkbox: Boolean = false,
    val radio: Boolean = false,
    val select: String? = null,
    val tomselect: String? = null,
    val spinner: Int? = null,
    val radiogroup: String? = null,
    val upload: List<KFile>? = null
)
```

> [!IMPORTANT]
> When defining form data model you need to make sure all the fields have default values.

## From controls

Kilua comes with a bunch of built-in or modular form composables for many different types of form data:

| Composable           | Data type        | Module               | Description                              |
|----------------------|------------------|----------------------|------------------------------------------|
| `text()`             | `String?`        | built-in             | A text field                             |
| `textArea()`         | `String?`        | built-in             | A text area                              |
| `password()`         | `String?`        | built-in             | A password field                         |
| `checkBox()`         | `Boolean`        | built-in             | A checkbox                               |
| `triStateCheckBox()` | `Boolean?`       | built-in             | A tri-state checkbox (with `null` state) |
| `radio()`            | `Boolean`        | built-in             | A radio button                           |
| `radioGroup()`       | `String?`        | built-in             | A group of radio buttons                 |
| `colorPicker()`      | `String?`        | built-in             | An HTML color picker                     |
| `numeric()`          | `Number?`        | built-in             | A numeric field                          |
| `range()`            | `Number?`        | built-in             | A range field                            |
| `spinner()`          | `Int?`           | built-in             | A spinner numeric field                  |
| `select()`           | `String?`        | built-in             | A simple select field                    |
| `date()`             | `LocalDate?`     | built-in             | A simple HTML date picker                |
| `dateTime()`         | `LocalDateTime?` | built-in             | A simple HTML date and time picker       |
| `time()`             | `LocalTime?`     | built-in             | A simple HTML time picker                |
| `upload()`           | `List<KFile>?`   | built-in             | A File picker                            |
| `imaskNumeric()`     | `Number?`        | kilua-imask          | A numeric field with masking             |
| `richText()`         | `String?`        | kilua-trix           | A rich text editor                       |
| `richDate()`         | `LocalDate?`     | kilua-tempus-dominus | A rich date picker                       |
| `richDateTime()`     | `LocalDateTime?` | kilua-tempus-dominus | A rich date and time picker              |
| `richTime()`         | `LocalTime?`     | kilua-tempus-dominus | A rich time picker                       |
| `tomSelect()`        | `String?`        | kilua-tom-select     | A rich select field                      |
| `tomTypeahead()`     | `String?`        | kilua-tom-select     | A text field with autocomplete           |

## Creating a form

To create a form just use a `form()` composable function, using a type parameter for static data model.

Let's build an example login form, with two standard fields and additional "remember me" checkbox. We will use this data
model class:

```kotlin
@Serializable
data class LoginForm(
    val username: String? = null,
    val password: String? = null,
    val rememberMe: Boolean = false
)
```

We start with a `form` composable:

```kotlin
form<LoginForm> {
}
```

Next, we need to add form controls:

```kotlin
form<LoginForm> {
    text()
    password()
    checkBox()
}
```

We connect form controls with our data model using `bind` method calls:

```kotlin
form<LoginForm> {
    text {
        bind(LoginForm::username)
    }
    password {
        bind(LoginForm::password)
    }
    checkBox {
        bind(LoginForm::rememberMe)
    }
}
```

Now our form (strictly speaking a component created by the composable, which can be accessed with `this@form` or
returned with `formRef {}` variant of the composable) can be treated as a kind of "black box". We can both get and set
the data using our model class with `getData()` and `setData()` methods. In our case we can initialize the form with
some non-default values (`rememberMe = true`) and print login data after clicking the "Login" button:

```kotlin
form<LoginForm> {
    text {
        bind(LoginForm::username)
    }
    password {
        bind(LoginForm::password)
    }
    checkBox {
        bind(LoginForm::rememberMe)
    }
    button("Login") {
        onClick {
            println("Login data: ${this@form.getData()}")
        }
    }
    LaunchedEffect(Unit) {
        this@form.setData(LoginForm(rememberMe = true))
    }
}
```

Our form is working, but it's not very accessible, because there are no labels for our fields. We can add them manually:

```kotlin
form<LoginForm> {
    label(htmlFor = "username") {
        +"Username:"
    }
    text(id = "username") {
        bind(LoginForm::username)
    }
    label(htmlFor = "password") {
        +"Password:"
    }
    password(id = "password") {
        bind(LoginForm::password)
    }
    label(htmlFor = "remember") {
        +"Remember me:"
    }
    checkBox(id = "remember", value = true) {
        bind(LoginForm::rememberMe)
    }
    button("Login") {
        onClick {
            println("Login data: ${this@form.getData()}")
        }
    }
    LaunchedEffect(Unit) {
        this@form.setData(LoginForm(rememberMe = true))
    }
}
```

We can also use a `fieldWithLabel` helper function, which will automatically generate ID attributes for our controls:

```kotlin
form<LoginForm> {
    fieldWithLabel("Username") {
        text(id = it) {
            bind(LoginForm::username)
        }
    }
    fieldWithLabel("Password") {
        password(id = it) {
            bind(LoginForm::password)
        }
    }
    fieldWithLabel("Remember me") {
        checkBox(id = it) {
            bind(LoginForm::rememberMe)
        }
    }
    button("Login") {
        onClick {
            println("Login data: ${this@form.getData()}")
        }
    }
    LaunchedEffect(Unit) {
        this@form.setData(LoginForm(rememberMe = true))
    }
}
```

And finally we can add some flexbox styling to make the form more visually appealing:

```kotlin
form<LoginForm> {
    vPanel(gap = 5.px) {
        maxWidth(400.px)
        hPanel(justifyContent = JustifyContent.SpaceBetween, gap = 15.px) {
            fieldWithLabel("Username") {
                text(id = it) {
                    bind(LoginForm::username)
                }
            }
        }
        hPanel(justifyContent = JustifyContent.SpaceBetween, gap = 15.px) {
            fieldWithLabel("Password") {
                password(id = it) {
                    bind(LoginForm::password)
                }
            }
        }
        hPanel(justifyContent = JustifyContent.Start, gap = 5.px) {
            fieldWithLabel("Remember me", labelAfter = true) {
                checkBox(id = it) {
                    bind(LoginForm::rememberMe)
                }
            }
        }
        hPanel(justifyContent = JustifyContent.Center) {
            button("Login") {
                onClick {
                    println("Login data: ${this@form.getData()}")
                }
            }
        }
    }
    LaunchedEffect(Unit) {
        this@form.setData(LoginForm(rememberMe = true))
    }
}
```

## Form validation

Kilua forms support validation for single fields and for the form as a whole. You can easily mark some fields as
required using `required = true` parameter. You can specify validation functions and error messages for single fields
using `bindWithValidationMessage` method instead of simple `bind`. You can also set `validator` function for the whole
form and check the relationships between different fields. Finally, you can validate the form with the `validate()`
method:

```kotlin
form<LoginForm> {
    vPanel(gap = 5.px) {
        maxWidth(400.px)
        hPanel(justifyContent = JustifyContent.SpaceBetween, gap = 15.px) {
            fieldWithLabel("Username") {
                bindWithValidationMessage(LoginForm::username) {
                    (it.value != null && it.value!!.length <= 20) to "Maximum length is 20 characters"
                }
            }
        }
        hPanel(justifyContent = JustifyContent.SpaceBetween, gap = 15.px) {
            fieldWithLabel("Password") {
                password(id = it, required = true) {
                    bind(LoginForm::password)
                }
            }
        }
        hPanel(justifyContent = JustifyContent.Start, gap = 5.px) {
            fieldWithLabel("Remember me", labelAfter = true) {
                checkBox(id = it) {
                    bind(LoginForm::rememberMe)
                }
            }
        }
        hPanel(justifyContent = JustifyContent.Center) {
            button("Login") {
                onClick {
                    if (this@form.validate()) {
                        println("Login data: ${this@form.getData()}")
                    }
                }
            }
        }
    }
    validator = {
        if (this[LoginForm::username] == this[LoginForm::password]) {
            it.copy(isInvalid = true, invalidMessage = "Don't use the same username and password")
        } else {
            it
        }
    }
    LaunchedEffect(Unit) {
        this@form.setData(LoginForm(rememberMe = true))
    }
}
```

The results of the validation process are not displayed automatically. Instead, they are collected within the `Validation`
object, which can be accessed with `validationStateFlow` property of the form component. You can use
`validationStateFlow.collectAsState()` to handle validation as standard compose state. It's up to you how to display the
collected validation data. This is only an example:

```kotlin
form<LoginForm> {
    val validation by validationStateFlow.collectAsState()

    vPanel(gap = 5.px) {
        if (validation.isInvalid && validation.invalidMessage != null) {
            div {
                color(Color.Red)
                +validation.invalidMessage!!
            }
        }
        maxWidth(400.px)
        hPanel(justifyContent = JustifyContent.SpaceBetween, gap = 15.px) {
            fieldWithLabel("Username") {
                text(id = it, required = true) {
                    bindWithValidationMessage(LoginForm::username) {
                        (it.value != null && it.value!!.length <= 20) to "Maximum length is 20 characters"
                    }
                }
            }
        }
        val validationUsername = validation[LoginForm::username]
        if (validationUsername?.isInvalid == true || validationUsername?.isEmptyWhenRequired == true) {
            div {
                color(Color.Red)
                if (validationUsername.isEmptyWhenRequired) {
                    +"Username is required"
                } else if (validationUsername.invalidMessage != null) {
                    +validationUsername.invalidMessage!!
                }
            }
        }
        hPanel(justifyContent = JustifyContent.SpaceBetween, gap = 15.px) {
            fieldWithLabel("Password") {
                password(id = it, required = true) {
                    bind(LoginForm::password)
                }
            }
        }
        val validationPassword = validation[LoginForm::password]
        if (validationPassword?.isInvalid == true || validationPassword?.isEmptyWhenRequired == true) {
            div {
                color(Color.Red)
                if (validationPassword.isEmptyWhenRequired) {
                    +"Password is required"
                } else if (validationPassword.invalidMessage != null) {
                    +validationPassword.invalidMessage!!
                }
            }
        }
        hPanel(justifyContent = JustifyContent.Start, gap = 5.px) {
            fieldWithLabel("Remember me", labelAfter = true) {
                checkBox(id = it) {
                    bind(LoginForm::rememberMe)
                }
            }
        }
        hPanel(justifyContent = JustifyContent.Center) {
            button("Login") {
                onClick {
                    if (this@form.validate()) {
                        println("Login data: ${this@form.getData()}")
                    }
                }
            }
        }
    }
    validator = {
        if (this[LoginForm::username] == this[LoginForm::password]) {
            it.copy(isInvalid = true, invalidMessage = "Don't use the same username and password")
        } else {
            it
        }
    }
    LaunchedEffect(Unit) {
        this@form.setData(LoginForm(rememberMe = true))
    }
}
```

## Custom type fields

Form data model doesn't support custom types out of the box, but it's possible to add support for your own type as long
as you can convert this type to and from a `String`.

Define a custom class for your model. It needs a `toString()` method to convert its data to the `String` value used
within a form and should be serializable with a custom serializer.

```kotlin
@Serializable(with = ObjectIdSerializer::class)
class ObjectId(val id: Int) {
    override fun toString(): String {
        return "$id"
    }
}

object ObjectIdSerializer : KSerializer<ObjectId> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("com.example.ObjectId")
    override fun deserialize(decoder: Decoder): ObjectId {
        val str = decoder.decodeString()
        return ObjectId(str.toInt())
    }
    override fun serialize(encoder: Encoder, value: ObjectId) {
        encoder.encodeString(value.toString())
    }
}
```

Now you can use this class within your data model.

```kotlin
@Serializable
data class Form(
    val text: String? = null,
    val password: String? = null,
    val objectId: ObjectId? = null
)
```

When creating a form, you can use any control based on `String` data type and use `bindCustom` method to create data
binding.

```kotlin
text {
    bindCustom(Form::objectId)
}
```

## Dynamic forms

Sometimes it's not possible to know the data model of the form because the fields need to be added and/or removed
dynamically. For such use cases Kilua allows you to model your data with a `Map<String, Any?>` type. The values are
still strictly typed, but you need to use casting to access the correct type of data. To create a dynamic form just use
`form` composable without any type parameter and create bindings using plain string identifiers:

```kotlin
form {
    fieldWithLabel("Username") {
        text(id = it) {
            bind("username")
        }
    }
    fieldWithLabel("Password") {
        password(id = it) {
            bind("password")
        }
    }
    button("Login") {
        onClick {
            val map = this@form.getData()
            println("Username: ${map["username"]}")
            println("Password: ${map["password"]}")
        }
    }
}
```
