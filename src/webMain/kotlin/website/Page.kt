package website

import kotlinx.serialization.Serializable

@Serializable
enum class Page(
    val title: String,
    val route: String? = null,
    val isSection: Boolean = false,
    val parent: Page? = null,
    val isDrawerOpen: Boolean = true,
) {
    Home("Kilua", isDrawerOpen = false),
    Introduction("Introduction", "/introduction"),
    ComposeWorld("Compose world", "/compose-world"),
    GettingStarted("Getting started", "/getting-started", isSection = true),
    SettingUp("Setting up", "/setting-up", parent = GettingStarted),
    CreatingANewApplication("Creating a new application", "/creating-a-new-application", parent = GettingStarted),
    DevelopmentWorkflow("Development workflow", "/development-workflow", parent = GettingStarted),
    BuildingForProduction("Building for production", "/building-for-production", parent = GettingStarted),
    DevelopmentGuide("Development guide", "/development-guide", isSection = true),
    Learning("Learning", "/learning", parent = DevelopmentGuide),
    ComposableFunctions("Composable functions", "/composable-functions", parent = DevelopmentGuide),
    BrowserApis("Browser APIs", "/browser-apis", parent = DevelopmentGuide),
    InteroperabilityWithJavascript(
        "Interoperability with JavaScript",
        "/interoperability-with-javascript",
        parent = DevelopmentGuide
    ),
    WorkingWithCompose("Working with Compose", "/working-with-compose", parent = DevelopmentGuide),
    RenderingHtml("Rendering HTML", "/rendering-html", parent = DevelopmentGuide),
    WorkingWithCss("Working with CSS", "/working-with-css", parent = DevelopmentGuide),
    Resources("Resources", "/resources", parent = DevelopmentGuide),
    Icons("Icons", "/icons", parent = DevelopmentGuide),
    Modules("Modules", "/modules", parent = DevelopmentGuide),
    Routing("Routing", "/routing", parent = DevelopmentGuide),
    LayoutContainers("Layout containers", "/layout-containers", parent = DevelopmentGuide),
    Events("Events", "/events", parent = DevelopmentGuide),
    Forms("Forms", "/forms", parent = DevelopmentGuide),
    SvgImages("SVG images", "/svg-images", parent = DevelopmentGuide),
    DragAndDrop("Drag and Drop", "/drag-and-drop", parent = DevelopmentGuide),
    Toasts("Toasts", "/toasts", parent = DevelopmentGuide),
    HotModuleReplacement("Hot Module Replacement", "/hot-module-replacement", parent = DevelopmentGuide),
    Debugging("Debugging", "/debugging", parent = DevelopmentGuide),
    Internationalization("Internationalization", "/internationalization", parent = DevelopmentGuide),
    RestClient("Rest Client", "/rest-client", parent = DevelopmentGuide),
    MarkdownAndSanitization("Markdown and Sanitization", "/markdown-and-sanitization", parent = DevelopmentGuide),
    KtmlTemplates("KTML Templates", "/ktml-templates", parent = DevelopmentGuide),
    UsingBootstrap("Using Bootstrap", "/using-bootstrap", parent = DevelopmentGuide),
    UsingTailwindcss("Using Tailwindcss", "/using-tailwindcss", parent = DevelopmentGuide),
    UsingTabulator("Using Tabulator", "/using-tabulator", parent = DevelopmentGuide),
    Animation("Animation", "/animation", parent = DevelopmentGuide),
    UsingJetpackComposeApi("Using Jetpack Compose API", "/using-jetpack-compose-api", parent = DevelopmentGuide),
    FullstackComponents("Fullstack components", "/fullstack-components", parent = DevelopmentGuide),
    ServerSideRendering("Server-Side Rendering", "/server-side-rendering", parent = DevelopmentGuide),
    NotFound("Page not found", isDrawerOpen = false),
    ;

    val path: String? = if (parent != null) parent.path + route else route
    val redirect: Page?
        get() = if (isSection) entries.find { it.parent == this } else null
}
