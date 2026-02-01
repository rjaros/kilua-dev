# Development workflow

To run the application with Gradle continuous build, enter one of these commands:

```
./gradlew -t jsBrowserDevelopmentRun                 (run JS target on Linux)
./gradlew -t wasmJsBrowserDevelopmentRun             (run WasmJS target on Linux)
gradlew.bat -t jsBrowserDevelopmentRun               (run JS target on Windows)
gradlew.bat -t wasmJsBrowserDevelopmentRun           (run WasmJS target on Windows)
```

> [!TIP]
> You can choose whether you want to develop JS or WasmJS version of your application without any code changes. In general, it's recommended to develop with JS target, because the Kotlin/JS compiler is a lot faster and also HMR is fully supported.

After Gradle finishes downloading dependencies and building the application, open [http://localhost:3000/](http://localhost:3000/) in your favourite browser.

You can import the project in **IntelliJ IDEA** and open `src/commonMain/kotlin/main.kt` file. You can of course use your favourite text editor.

Make some code changes in the `start` function:

```kotlin
override fun start() {
    root("root") {
        div {
            +"Hello Kilua!"
        }
    }
}
```

You should see your changes immediately in the browser.
