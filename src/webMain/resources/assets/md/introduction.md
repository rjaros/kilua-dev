# Introduction

**Kilua** is an open source web framework created for the [Kotlin](https://kotlinlang.org/) language, based on [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/) runtime. It allows developers to create declarative UI components and manage their state. Kilua renders to plain HTML DOM (not canvas) and supports both Kotlin/Wasm and Kotlin/JS targets.

## Main features

#### Powerful Compose programming model and state management

Create declarative UI components with `@Composable` functions. Use a variety of state management functions, including `remember {}`. Let Compose automatically refresh the correct parts of your UI when the state of your application changes.&#x20;

#### **Ready to use components**

Kilua gives you a lot of ready to use GUI components, which can be used as builder blocks for the application UI. Hundreds of features are available with easy to learn and consistent API.

* sophisticated containers (tabs, grid, horizontal, vertical, flexbox, split, lazy-layouts)
* forms with type-safe data model and built-in validation
* many different text input components including rich text editor, typeahead and input mask support
* buttons, checkboxes and radios
* date and time pickers
* spinner, range and numeric input components
* advanced select box with remote data support
* reactive tables
* accordion, carousel, dropdown and offcanvas sidebar
* tooltips and popovers
* modals including ready to use alerts and confirm dialogs
* configurable toasts
* theme switcher for dark mode with auto-detection
* optional Jetpack-like API including `Modifier`, `Box`, `Row` and `Column`
* animation module powered by CSS and [Motion](https://motion.dev/) library
* interactive maps powered by [Leaflet](https://leafletjs.com/) library
* Kotlin based templates powered by [Ktml](https://github.com/ktool-dev/ktml) engine
* built-in support for [Font Awesome](https://fontawesome.com/) icons
* built-in support for [Bootstrap Icons](https://icons.getbootstrap.com/)

#### Direct support for Tailwind CSS or Bootstrap styling

Kilua is ready for your creativity, whenever you like modern, utility-first approach of [Tailwindcss](https://tailwindcss.com/) or more traditional way of [Bootstrap](https://getbootstrap.com/). Both modules include support for light/dark mode selection.

#### **Compile the same application code to Kotlin/Wasm or Kotlin/JS targets**

Compiling your code to Kotlin/Wasm makes your application up to 50% faster. On the other hand, the Kotlin/JS version is fully compatible with older web browsers. You don't have to change anything in your codebase - when building for Wasm target, the JS version will be used as a fallback for unsupported browsers.

#### **Type-safe fullstack**

A companion [Kilua RPC](https://github.com/rjaros/kilua-rpc) library implements type-safe fullstack support for a bunch of popular server side frameworks - [Ktor](https://ktor.io), [Jooby](https://jooby.io), [Spring Boot](https://spring.io/projects/spring-boot), [Javalin](https://javalin.io), [Vert.x](https://vertx.io) and [Micronaut](https://micronaut.io). It allows you to easily build maintainable applications with shared code for data model and business logic. Kilua provides built-in fullstack components - Tabulator, Select and Typeahead, allowing easy access to remote data sources.

There is also support for type-safe websocket connections and SSE (server-sent events), based on [Kotlin coroutines channels](https://kotlinlang.org/docs/reference/coroutines/channels.html).

#### Server-Side Rendering

Kilua is the first Kotlin/Wasm and Kotlin/JS web framework supporting true Server-Side Rendering. SSR is a crucial concept in modern web development that enhances user experience and boosts SEO performance. Kilua SSR support is based on the possibility to run exactly the same application code both in the browser and in Node.js environment. What's more, you can easily use WASM compilation target for much better performance.

