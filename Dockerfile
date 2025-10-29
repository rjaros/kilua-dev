FROM gradle:9.1.0-jdk21 AS build

WORKDIR /app

COPY . .

RUN gradle jarWithWasmJs --no-daemon

FROM ghcr.io/graalvm/native-image-community:24 AS graalvm

WORKDIR /app

COPY --from=build /app/build/libs/kilua-dev.jar .

RUN native-image --no-fallback \
    --install-exit-handlers \
    -H:+UnlockExperimentalVMOptions \
    --initialize-at-build-time=kotlinx.coroutines \
    --initialize-at-build-time=ch.qos.logback.classic.Logger \
    --initialize-at-build-time=ch.qos.logback \
    --initialize-at-build-time=io.ktor,kotlin \
    --initialize-at-build-time=org.slf4j.LoggerFactory \
    --initialize-at-build-time=org.slf4j.helpers \
    --initialize-at-build-time=org.xml.sax.helpers.AttributesImpl \
    --initialize-at-build-time=org.xml.sax.helpers.LocatorImpl \
    --initialize-at-build-time=kotlinx.io \
    -H:+ReportExceptionStackTraces \
    -H:IncludeResources="ssr.zip" \
    -H:IncludeResources=".*/assets/.*" \
    -H:IncludeResources="assets/.*wasm$" \
    -H:IncludeResources="assets/.*js$" \
    -H:IncludeResources="assets/.*html$" \
    -H:IncludeResources="assets/.*ttf$" \
    -H:IncludeResources="assets/.*woff2$" \
    -H:IncludeResources="assets/.*txt$" \
    -cp kilua-dev.jar -H:Class=website.MainKt -o kilua-dev

FROM node:24

WORKDIR /app

COPY --from=graalvm /app/kilua-dev /app/kilua-dev

COPY deploy/application.conf .

CMD ["/app/kilua-dev", "-Djava.home=/app", "-config=application.conf"]
