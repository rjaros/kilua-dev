## Kilua website

The sources for the Kilua website published at [https://kilua.dev](https://kilua.dev).

# Building and running

To build the application, you need to have JDK 21 or later installed.

The following tasks are available:

- `./gradlew -t jsBrowserDevelopmentRun` - run the webpack dev server in continuous build mode for JS target on `http://localhost:3000`
- `./gradlew -t wasmJsBrowserDevelopmentRun` - run the webpack dev server in continuous build mode for Wasm target on `http://localhost:3000`

- `./gradlew jarWithJs` - build and package the production application with JS frontend to `build/libs` directory
- `./gradlew jarWithWasmJs` - build and package the production application with Wasm frontend to `build/libs` directory

Note: use `gradlew.bat` instead of `./gradlew` on Windows operating system.
