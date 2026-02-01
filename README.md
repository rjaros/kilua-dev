## Kilua website

The sources for the Kilua website published at [https://kilua.dev](https://kilua.dev).

# Building and running

To build the application, you need to have JDK 21 or later installed.

The following tasks are available:

- `./gradlew jsViteRun` - run the Vite dev server for JS target on `http://localhost:3000`
- `./gradlew -t jsViteCompileDev` - run the development compilation in continuous mode for JS target
- `./gradlew jsViteBuild` - build production application for JS target to `build/vite/js/dist` directory
- `./gradlew wasmJsViteRun` - run the Vite dev server for Wasm target on `http://localhost:3000`
- `./gradlew -t wasmJsViteCompileDev` - run the development compilation in continuous mode for Wasm target
- `./gradlew wasmJsViteBuild` - build production application for Wasm target to `build/vite/wasmJs/dist` directory
- `./gradlew -t jsBrowserDevelopmentRun` - run the webpack dev server in continuous build mode for JS target on `http://localhost:3000`
- `./gradlew -t wasmJsBrowserDevelopmentRun` - run the webpack dev server in continuous build mode for Wasm target on `http://localhost:3000`
- `./gradlew -t jvmRun` - run the JVM dev server on `http://localhost:8080`
- `./gradlew jsBrowserDistribution` - build production application for JS target to `build/dist/js/productionExecutable` directory
- `./gradlew wasmJsBrowserDistribution` - build production application for Wasm target to `build/dist/wasmJs/productionExecutable` directory
- `./gradlew jarWithJs` - build full application with JS frontend to `build/libs` directory
- `./gradlew jarWithWasmJs` - build full application with Wasm frontend to `build/libs` directory
- `./gradlew exportWithJs` - export pre-rendered static website with JS frontend to `build/site` directory
- `./gradlew exportWithWasmJs` - export pre-rendered static website with Wasm frontend to `build/site` directory

Note: For auto reload with Ktor JVM backend you need to run  `./gradlew -t compileKotlinJvm` in a separate Gradle process.

Note: use `gradlew.bat` instead of `./gradlew` on Windows operating system.
