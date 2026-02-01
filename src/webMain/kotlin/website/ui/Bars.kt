package website.ui

import androidx.compose.runtime.Composable
import dev.kilua.core.IComponent
import dev.kilua.svg.path
import dev.kilua.svg.svg

@Composable
fun IComponent.bars() {
    svg(
        viewBox = "0 0 24 24",
        className = "inline-block h-6 w-6 stroke-current md:h-8 md:w-8"
    ) {
        attribute("width", "20")
        attribute("height", "20")
        attribute("fill", "none")
        path(
            d = "M4 6h16M4 12h16M4 18h16"
        ) {
            attribute("strokeLineCap", "round")
            attribute("strokeLineJoin", "round")
            attribute("strokeWidth", "2")
        }
    }
}