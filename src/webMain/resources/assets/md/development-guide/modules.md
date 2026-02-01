# Modules

Kilua consists of both required and optional functionality. Modules can be added as dependencies in `build.gradle.kts` file.

All modules which include CSS stylesheets require explicit initialization. This also applies to the core module. This initialization ensures a predictable order in which all styles will be applied. The initialization is performed by adding dedicated module objects as parameters to the `startApplication()` function.

```kotlin
fun main() {
    startApplication(
        ::App,
        js("import.meta.webpackHot").unsafeCast<Hot?>(), // K/JS only
        BootstrapModule,
        BootstrapCssModule,
        FontAwesomeModule,
        TomSelectModule,
        CoreModule // always used as the last initializer
    )
}
```

> [!TIP]
> Some modules include more than one, optional initializers. E.g. `kilua-tabulator` module includes `TabulatorModule, TabulatorDefaultModule, TabulatorBulmaModule`, `TabulatorMaterializeModule`, `TabulatorMidnightModule`, `TabulatorModernModule`, `TabulatorSemanticUIModule`, `TabulatorSimpleModule` and `TabulatorSiteDarkModule` for all CSS styles supported by the Tabulator component.

This is the current list of available modules.

| Module | Description | Initialization  |
|--------|-------------|:---------------:|
| kilua | Core module required for all applications. |        +        |
| kilua-animation | Composable functions for animations powered by CSS and [Motion](https://motion.dev/) library. |        +        |
| kilua-bootstrap | [Bootstrap](https://getbootstrap.com/) based components. |        +        |
| kilua-bootstrap-icons | [Bootstrap Icons](https://icons.getbootstrap.com/) support. |        +        |
| kilua-fontawesome | [Font Awesome](https://fontawesome.com) support. |        +        |
| kilua-i18n | Internationalization support. |                 |
| kilua-imask | Text input mask support powered by [Imask.js](https://imask.js.org/) |        +        |
| kilua-jetpack | Jetpack-like API (`Modifier`, `Box`, `Row`, `Column`) |        +        |
| kilua-ktml | Kotlin based HTML templates powered by [Ktml](https://github.com/ktool-dev/ktml) engine. |                 |
| kilua-lazy-layouts | `LazyRow` and `LazyColumn` implementations. |                 |
| kilua-leaflet | Interactive maps. |                 |
| kilua-marked | Markdown parser powered by [marked](https://marked.js.org/) library. |                 |
| kilua-rest | Configurable REST/HTTP client. |                 |
| kilua-routing | Routing module based on [routing-compose](https://github.com/hfhbd/routing-compose) library. |                 |
| kilua-rsup-progress | Progress bar support powered by [Rsup Progress](https://skt-t1-byungi.github.io/rsup-progress/) library. |                 |
| kilua-sanitize-html | HTML sanitizer. |                 |
| kilua-select-remote | Select form component tailored for full-stack applications. |                 |
| kilua-splitjs | `SplitPanel` component. |        +        |
| kilua-ssr | Frontend side SSR support. |                 |
| kilua-ssr-server | Backend side core SSR support. |                 |
| kilua-ssr-server-javalin | SSR support module for Javalin. |                 |
| kilua-ssr-server-jooby | SSR support module for Jooby.  |                 |
| kilua-ssr-server-ktor | SSR support module for Ktor. |                 |
| kilua-ssr-server-micronaut | SSR support module for Micronaut. |                 |
| kilua-ssr-server-spring-boot | SSR support module for Spring Boot. |                 |
| kilua-ssr-server-vertx | SSR support module for Vert.x. |                 |
| kilua-svg | SVG rendering support. |                 |
| kilua-tabulator | [Tabulator](https://tabulator.info/) component. |        +        |
| kilua-tabulator-remote | Tabulator component tailored for full-stack applications. |                 |
| kilua-tailwindcss | [TailwindCSS](https://tailwindcss.com/) support. |        +        |
| kilua-tempus-dominus | Data/Time select component powered by [Tempus Dominus](https://getdatepicker.com/). |        +        |
| kilua-toastify | Toast messages. |        +        |
| kilua-tom-select | Select and typeahead (autocomplete) components based on [Tom Select](https://tom-select.js.org/) library. |        +        |
| kilua-tom-select-remote | Select and typeahead (autocomplete) form components tailored for full-stack applications. |                 |
| kilua-trix | Rich text form component. |        +        | 
