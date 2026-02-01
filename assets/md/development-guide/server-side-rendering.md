# Server Side Rendering

Kilua is the first Kotlin/Wasm and Kotlin/JS web framework supporting true Server-Side Rendering. SSR is a crucial
concept in modern web development that enhances user experience and boosts SEO performance.

## Overview

### SSR features

- Preparing application for SSR is as easy as changing the router class.
- Ability to use external API calls and fullstack RPC services.
- Automatically extracting CSS styles from JS bundle and injecting them into the HTML document before sending to the
  browser.
- Serialization of the application state from the server to the client side.
- Ready to use modules for Ktor, Spring Boot, Micronaut, Javalin, Jooby and Vert.x servers.

### Current limitations

- The URL address and the browser preferred locale need to be the only source of the application state.
- Using browser APIs directly is not recommended.
- Advanced JS components (like RichText, Tabulator etc.) are rendered on the server
  as simple HTML placeholders.
- Rendering authenticated content is not supported at the moment.
- The "hydration" is implemented in a simple way (by replacing the rendered content).

## Architecture

Kilua SSR support is based on the possibility to run the same application code both in the browser and in Node.js
environment. The Kilua application running with SSR consist of three main parts:

1. The client-side web application - standard Kilua application compiled to Kotlin/JS or Kotlin/WasmJS target, running
   in the browser. The application need to handle client side routing with a dedicated `ssrRouter()` (which on the
   client side is internally implemented by the `BrowserRouter` and History API - see
   the [Routing](development-guide/routing) chapter for more information). This application is responsible for rendering
   UI and all client-side interactivity after the initial HTML document is loaded.

> [!IMPORTANT]
> Be sure to use `isDom` global variable (or `renderConfig.isDom` component property) to check if your code is running
> in the browser whenever you interact directly with browser API or external JavaScript components.

2. The server-side web application - exactly the same Kilua application compiled to Kotlin/WasmJS target, running in the
   Node.js instance on the server. This application spins off very simple HTTP server using standard Node HTTP module (
   by default on port 7788) and handles a few kinds of incoming requests. Its main responsibility is returning rendered
   HTML code for the given URL address and locale. It also provides the information about routing metadata, CSS
   stylesheets used in the application and serializes the server-side application state to be used on the client side.

> [!IMPORTANT]
> The most crucial part of Kilua SSR engine is the synchronization between the routing and recomposition. You need to
> make sure the application routing request results in the correct recomposition of the UI tree. If your application
> uses
> any additional state to determine what to render, you should use [state based routing](development-guide/routing) and
> make sure the recomposition is ready before returning from the `action {}` block. One of the proven approaches is to
> integrate [Ballast](https://copper-leaf.github.io/ballast/) library for state management and use
`sendAndAwaitCompletion()` method when sending inputs to the `ViewModel`.

2. The JVM server-side application - a simple JVM module for one of the supported frameworks (Ktor, Spring Boot,
   Micronaut, Javalin, Jooby or Vert.x). The server module is responsible for handling incoming HTTP requests and
   preparing HTTP responses with correct HTML code. For every request, it communicates with the server-side Kilua
   application running in Node.js to get the rendered HTML document. It then combines it with the given HTML template by
   adding appropriate meta headers and inlining all used CSS stylesheets. The module is also responsible for caching SSR
   responses, handling SSR configuration, starting the Node.js process with correct parameters and stopping it when the
   server shuts down.

## Building

The SSR functionality is only active when running the application in production mode. So when developing your
application, you should use standard Kilua development setup with Webpack or Vite dev server. This way you can take
advantage of fast hot-reload and other development features.

> [!IMPORTANT]
> You should always make sure your application works correctly is SSR mode before releasing.

Building SSR application for production is a complex process, which is automated by the Kilua Gradle plugin (and also
Kilua RPC Gradle plugin). Your project needs special Webpack configuration, which is a bit different from the standard
application setup.

> [!TIP]
> When creating new Kilua application with the Project Wizard, you can select the SSR option and the generated project
> will contain all necessary configuration.

> [!NOTE]
> Currently the production build process is only supported with Webpack bundler. Vite support is planned for future
> releases.

The `webpack.config.d/webpack.js` file needs to contain the following additional configuration (compared to the standard
Kilua Webpack setup):

```js
config.resolve.fallback = {
    "http": false
}
// standard configuration ...
if (config.devServer) {
    // standard configuration ...
} else {
    // standard configuration ...
    config.resolve.alias = {
        "zzz-kilua-assets/k-style.css": false,
        "zzz-kilua-assets/k-animation.css": false,
        "zzz-kilua-assets/k-bootstrap.css": false,
        "zzz-kilua-assets/k-jetpack.css": false,
        "zzz-kilua-assets/k-splitjs.css": false,
        "zzz-kilua-assets/k-tabulator.css": false,
        "zzz-kilua-assets/k-tempus-dominus.css": false,
        "zzz-kilua-assets/k-toastify.css": false,
        "zzz-kilua-assets/k-tom-select.css": false,
        "zzz-kilua-assets/k-trix.css": false,
        "bootstrap/dist/css/bootstrap.min.css": false,
        "@eonasdan/tempus-dominus/dist/css/tempus-dominus.min.css": false,
        "leaflet/dist/leaflet.css": false,
        "tabulator-tables/dist/css/tabulator.min.css": false,
        "tabulator-tables/dist/css/tabulator_bootstrap5.min.css": false,
        "tabulator-tables/dist/css/tabulator_bulma.min.css": false,
        "tabulator-tables/dist/css/tabulator_materialize.min.css": false,
        "tabulator-tables/dist/css/tabulator_midnight.min.css": false,
        "tabulator-tables/dist/css/tabulator_modern.min.css": false,
        "tabulator-tables/dist/css/tabulator_simple.min.css": false,
        "tabulator-tables/dist/css/tabulator_semanticui.min.css": false,
        "tabulator-tables/dist/css/tabulator_site_dark.min.css": false,
        "toastify-js/src/toastify.css": false,
        "tom-select/dist/css/tom-select.bootstrap5.min.css": false,
        "tom-select/dist/css/tom-select.default.min.css": false,
        "tom-select/dist/css/tom-select.min.css": false,
        "trix/dist/trix.css": false,
        "./tailwind/tailwind.css": false,
        "./modules/css/custom.css": false,
    }
}
```

This configuration ensures that Node HTTP module dependency will be ignored when bundling for the browser and also
prevents including most CSS files used by Kilua in the client-side bundle (they will be inlined inside the HTML
document).

If you use any additional CSS files in your application, make sure to add them to the `resolve.alias` section, so they
are not included in the client-side bundle. When importing these CSS files also call the `CssRegister.register()`method.

```kotlin
@JsModule("./modules/css/custom.css")
external object CustomCss

class App : Application() {
    init {
        CssRegister.register("modules/css/custom.css")
        useModule(CustomCss)
    }
}
```

The SSR build process requires additional configuration in the `webpack.config.ssr.d` directory. You can find the
complete configuration in one of the example projects in
the [Kilua GitHub repository](https://github.com/rjaros/kilua/tree/main/examples/ssr-javalin/webpack.config.ssr.d). The
`webpack.config.ssr.d/webpack.js` file also contains the `resolve.alias` section similar to the one above, and you need
to add your custom CSS files there as well.

When building the project for production you need to run `jarWithJs` or `jarWithWasmJs` Gradle tasks. The complete
application will be packaged as a single fat jar inside `build/libs` directory.

## Serializing server-side state

When rendering some page on the server, sending it to the client and then running the client-side application, you often
need to preserve the state of the application. Otherwise, the client-side application would need to re-create the state
from scratch by fetching and processing all data again. It would lead to unnecessary network request and page
flickering. Kilua provides a simple mechanism to serialize the server-side state and restore it on the client side.

To serialize state on the server, provide a `stateSerializer` function when initializing the `ssrRouter()` in your Kilua
application.

```kotlin
root("root") {
    ssrRouter(
        stateSerializer = {
            Json.encodeToString(state)
        }
    ) {
        // routes configuration ...
    }
}
```

The `stateSerializer` function should return a string representation of the application state (you can use the
serialization mechanism you prefer). The stateSerializer function will be called only when the application is running on
the server.

To restore the state on the client side, you can use one of global `getSsrState()` functions. Provide a default initial
state when the SSR state is not available.

```kotlin
val initialState = getSsrState<State>(json) ?: State(Page.Home)
```

## Server configuration

The SSR server module uses several configuration options, which can be provided using configuration file specific
for the server framework you use. See the example SSR projects in
the [Kilua GitHub repository](https://github.com/rjaros/kilua/tree/main/examples/) for reference. These options allow
you to customize how the SSR engine works.

| Option                   | Description                                                                                                                      |
|--------------------------|----------------------------------------------------------------------------------------------------------------------------------|
| `ssr.nodeExecutable`     | Path to the Node.js executable. Defaults to `node`.                                                                              |
| `ssr.port`               | The port used by the Node.js HTTP server. Defaults to `7788`.                                                                      |
| `ssr.externalSsrService` | Optional URL address of the SSR application, which can be used instead of local one.                                             |
| `ssr.rpcUrlPrefix`       | The URL used by the application to connect to Kilua RPC remote services. Defaults to `http://localhost:8080`.                      |
| `ssr.rootId`             | The ID of the root element inside the main `index.html` file. Defaults to `root`.                                                |
| `ssr.contextPath`        | The context path where the application is published. Defaults to `/`.                                                            |
| `ssr.cacheTime`          | The cache time (in minutes) for the SSR response cache. Defaults to `10`. The cache can be disabled with a negative value. |

## Exporting static website

Every Kilua SSR application can be exported as a static website with pre-rendered HTML documents for all routes. To export your site, just run `exportWithJs` or `exportWithWasmJs` Gradle tasks. The exported site will be available in the `build/site` directory. Kilua will automatically create HTML documents for all static `route {}` calls defined with the `ssrRouter()` function. Dynamic routes (defined with `string {}` or `int {}` calls) will not be exported by default.

Kilua export process can be customized with several configuration options, by using `kilua.yml` file in the root of your project. By using this file you can provide additional server parameters used when exporting the site, set the default language and also define a list of additional pages to be exported (e.g. for dynamic routes).

```yaml
export:
  server_parameters:
   - "-config=./deploy/application.conf"
  language: "pl"
  pages:
    - "/product/001"
    - "/product/002"
    - "/product/003"
```
