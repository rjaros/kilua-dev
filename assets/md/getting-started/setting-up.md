# Setting up

Kilua applications are built with [Gradle](http://gradle.org/). The official Kotlin Multiplatform Gradle plugin is used to manage NPM dependencies, pack bundles (via [Webpack](https://webpack.github.io/)) and test the application using [Karma](http://karma-runner.github.io/1.0/index.html). By using Gradle continuous build, you also can get hot module replacement (HMR) feature (apply code changes in the browser on the fly).

Kilua also supports [Vite for Kotlin](https://gitlab.com/opensavvy/automation/kotlin-vite) plugin, which uses [Vite](https://vite.dev) bundler - a modern and faster alternative to Webpack.

> [!NOTE]
> HMR is currently supported only when developing for Kotlin/JS target. For Kotlin/WasmJS you can use simple hot reload.

## Requirements

To build a typical Kilua application you should have some tools installed on your machine and available on the system PATH:

* [JDK](https://jdk.java.net/) 21 (required)
* [Git](https://git-scm.com) with additional UNIX tools if using Windows (optional, but recommended)
* GNU [xgettext](https://www.gnu.org/software/gettext) and [msgmerge](https://www.gnu.org/software/gettext) utilities to use internationalization features (optional)

> [!IMPORTANT]
> Make sure you are building Kilua applications on the local file system. Issues have been reported when building from network drives or virtual file systems.
