# Using Tailwindcss

[Tailwindcss](https://tailwindcss.com/) is a utility-first CSS framework for rapidly building custom user interfaces. It provides a set of pre-defined CSS classes that can be directly applied to HTML elements, allowing developers to style their applications without writing custom CSS. Unlike traditional CSS frameworks that provide pre-designed components, Tailwindcss focuses on low-level utility classes that can be combined to create unique designs. It also requires a build step to generate the final CSS file based on the classes used in the project, which helps keep the file size small and optimized for performance.

## Installation

> [!TIP]
> When using Kilua Project Wizard to create a new Kilua project, you get everything set up for you automatically when you include Tailwindcss module.

Using Tailwindcss with Kilua requires some setup to integrate it into Kotlin development workflow. First, you need to create two configuration files: `tailwind.config.js` and `tailwind.css` inside the `resources` directory of your Kilua project.

The `tailwind.config.js` file:

```javascript
module.exports = {
    content: {
        files: [ "SOURCES" ]
    },
}
```
> [!NOTE]
> The `SOURCES` string will be automatically replaced with the path to your Kotlin source files during the build process. Don't do this manually.

The `tailwind.css` file:

```css
@config "./tailwind.config.js";
@import "tailwindcss";
@custom-variant dark (&:where(.dark, .dark *));
@source inline("bg-neutral-500 text-white font-bold inline-block rounded-full");
```

> [!NOTE]
> You can customize the `tailwind.css` file, include plugins and add your own styles as needed.

Next, you need to add `kilua-tailwindcss` module to you project dependencies in the `build.gradle.kts` file, and add `TailwindcssModule` initializer to your `startApplication()` call.

```kotlin
fun main() {
    startApplication(
        ::App,
        bundlerHot(),
        TailwindcssModule,
        CoreModule
    )
}
```
As a final step, you need to configure the bundler. If you are using Webpack, add `webpack.config.d/tailwind.js` file to your project:

```javascript
;(function() {
    config.module.rules.push({
        test: /tailwind\.css$/,
        use: [
            {
                loader: "postcss-loader",
                options: {
                    postcssOptions: {
                        plugins: [
                            ["@tailwindcss/postcss", {} ],
                            (config.devServer ? undefined : [ "cssnano", {} ])
                        ]
                    }
                }
            }
        ]
    });
})();
```

When using Vite, add Tailwindcss plugin to your `build.gradle.kts` file:

```kotlin
vite {
    plugin("@tailwindcss/vite", "tailwindcss", libs.versions.tailwindcss.asProvider().get())
    // ...
}
```

Once Tailwindcss is set up in your Kilua project, you can start using its utility classes in your Kotlin code.

## Color mode

To support color mode switching you need to initialize `ThemeManger` object using its `init()` method.

```kotlin
class App : Application() {
    override fun start() {
        ThemeManager.init()
    }
}
```

By default, the last selected color mode will be used, and it will be automatically saved in the browser local storage. You can customize this behavior with optional parameters.

```kotlin
ThemeManager.init(initialTheme = Theme.Auto, remember = false)
```

You can use `ThemeManager.theme` property to get and set the current color mode at any time.

```kotlin
ThemeManager.theme = Theme.Dark
```

You can also use built-in theme switcher component.

```kotlin
themeSwitcher(
    round = true,
    className = "bg-primary hover:bg-secondary w-10 h-10 px-3"
)
```

The theme switcher uses Font Awesome icons by default, so you should also add `kilua-fontawesome` module dependency to your project. If your applications use a different icons set (e.g. Bootstrap Icons), you can use optional switcher parameters to customize icons.

```kotlin
themeSwitcher(
    autoIcon = "bi bi-circle-half",
    lightIcon = "bi bi-moon",
    darkIcon = "bi bi-sun-fill",
    round = true,
    className = "bg-primary hover:bg-secondary w-10 h-10 px-3"
)
```
