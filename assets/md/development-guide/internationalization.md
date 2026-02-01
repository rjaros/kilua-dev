# Internationalization

## Features

* Automatic language detection (based on the browser language settings).
* Dynamic language change.
* Multi-language support for built-in advanced components:
  * rich date and time picker - 26 languages
  * rich text editor - 2 languages
* Minimal impact of internationalization process on the application source code.
* Automatic extraction of text for translation from the application code.
* Support for well known translation files format (gettext `*.po` files).

## Requirements

Apply `kotlinx-gettext` Gradle plugin and add `kilua-i18n` module to your dependencies.

```toml
[versions]
gettext = "0.7.0"

[libraries]
kilua-i18n = { module = "dev.kilua:kilua-i18n", version.ref = "kilua" }

[plugins]
gettext = { id = "name.kropp.kotlinx-gettext", version.ref = "gettext" }
```

```kotlin
plugins {
    alias(libs.plugins.gettext)
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kilua.i18n)
            }
        }
    }
}

gettext {
    potFile.set(File(projectDir, "src/commonMain/resources/modules/i18n/messages.pot"))
    keywords.set(listOf("tr","trn:1,2","trc:2","trnc:2,3","marktr"))
}
```

## Using multi-language text in the application sources

To mark some text for translation use one of the methods of the `dev.kilua.i18n.I18n` class instead of plain string literals. You can start your work with an empty instance of the class.

```kotlin
import dev.kilua.i18n.I18n

val i18n = I18n()
```

There are a few different methods in the `I18n` class, which can be used for singular and plural forms in different contexts.

|Method|Description|
|------|-----------|
|`tr()`|Translate a singular form|
|`trn()`|Translate plural forms|
|`trc()`|Translate a singular form with a given context|
|`trnc()`|Translate plural forms with a given context|
|`marktr()`|Mark text for translation|

These methods are `@Composable`, and they are bound to the application current locale. When the locale changes, all methods using translated texts are automatically recomposed.

This is an example of multi-language code with singular and plural forms.

```kotlin
div {
    +i18n.tr("This is a localized message.")
}
div {
    val count = 3
    +i18n.trn("{{name}} has one car", "{{name}} has {{count}} cars", count, "name" to "Robert", "count" to "$count")
}
```

> [!TIP]
> You can use `{{ }}`  operators to substitute named values.

## Translation files

The whole idea of gettext translations is that the translation key is the actual text in the primary language. Until you create and initialize translation files for some other language, your application will work correctly just by using string literals used in the source code. It's probably a good practice to use English literals in your code and other languages in the translation files.

To generate basic translation files run the command:

```
./gradlew gettext                                (on Linux)
gradlew.bat gettext                              (on Windows)
```

This command will search your sources for any usages of internationalization methods (`tr` or others) and generate a `messages.pot` file in the `src/commonMain/resources/modules/i18n` directory. This file is the base for your translations. For any language you would like to support, copy the `messages.pot` file to `messages-XX.po`, where XX is a country code (en, de, es, fr etc.). These files should be translated according to the [PO format specification](https://www.gnu.org/software/gettext/manual/html_node/PO-Files.html). You can use many popular tools for editing PO files to simplify the translation process.

```
# SOME DESCRIPTIVE TITLE.
# Copyright (C) YEAR THE PACKAGE'S COPYRIGHT HOLDER
# This file is distributed under the same license as the KVision package.
# FIRST AUTHOR <EMAIL@ADDRESS>, YEAR.
#
#, fuzzy
msgid ""
msgstr ""
"Project-Id-Version: Kilua\n"
"Report-Msgid-Bugs-To: \n"
"POT-Creation-Date: 2018-08-18 01:34+0200\n"
"PO-Revision-Date: YEAR-MO-DA HO:MI+ZONE\n"
"Last-Translator: FULL NAME <EMAIL@ADDRESS>\n"
"Language-Team: LANGUAGE <LL@li.org>\n"
"Language: Polish\n"
"MIME-Version: 1.0\n"
"Content-Type: text/plain; charset=CHARSET\n"
"Content-Transfer-Encoding: 8bit\n"
"Plural-Forms: nplurals=3; plural=n==1 ? 0 : n%10>=2 && n%10<=4 && (n%100<10 || n%100>=20) ? 1 : 2;\n"

msgid "This is a localized message."
msgstr "To jest przetłumaczona wiadomość."

msgid "{{name}} has one car"
msgid_plural "{{name}} has {{count}} cars"
msgstr[0] "{{name}} ma jeden samochód"
msgstr[1] "{{name}} ma {{count}} samochody"
msgstr[2] "{{name}} ma {{count}} samochodów"
```

> [!IMPORTANT]
> You should correctly set Language and Plural-Forms headers of your PO files.

After adding some new texts to your sources you can call the `./gradlew gettext` task again to refresh the `messages.pot` file. You can then use the [msgmerge](https://www.gnu.org/software/gettext/manual/html_node/msgmerge-Invocation.html) tool from the GNU gettext package to merge new keys with existing translation files. You can also add new `msgid` and `msgstr` lines to your translation files manually.

## Initializing translations

To initialize translations for one or more languages, you need to pass the translation files as constructor parameters of the `I18n` class.

```kotlin
@JsModule("./modules/i18n/messages-en.po")
external object messagesEn: JsAny

@JsModule("./modules/i18n/messages-pl.po")
external object messagesPl: JsAny

val i18n = I18n(
    "en" to messagesEn,
    "pl" to messagesPl
)
```

## Changing the application locale

The current locale is stored in the `LocaleManager.currentLocale` property. It is initialized with the default locale from the browser language settings. It can be changed at any time using `LocaleManager.setCurrentLocale()` method.

```kotlin
select(listOf("en" to "English", "pl" to "Polski", "de" to "Deutsch"), LocaleManager.currentLocale.language) {
    onChange {
        LocaleManager.setCurrentLocale(SimpleLocale(this.value ?: "en"))
    }
}
```
