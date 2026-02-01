package website

import com.copperleaf.ballast.BallastViewModelConfiguration
import com.copperleaf.ballast.build
import com.copperleaf.ballast.core.BasicViewModel
import com.copperleaf.ballast.core.FifoInputStrategy
import com.copperleaf.ballast.core.LoggingInterceptor
import com.copperleaf.ballast.core.PrintlnLogger
import com.copperleaf.ballast.withViewModel
import com.copperleaf.ballast.plusAssign
import dev.kilua.ssr.getSsrState
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.Json

class ViewModel(
    coroutineScope: CoroutineScope,
    json: Json,
    inputHandler: InputHandler,
    eventHandler: EventHandler
) : BasicViewModel<
        Contract.Inputs,
        Contract.Events,
        Contract.State>(
    config = BallastViewModelConfiguration.Builder(inputStrategy = FifoInputStrategy()).apply {
        if (isDevelopmentBuild) {
            this += LoggingInterceptor()
            logger = { PrintlnLogger(it) }
        }
    }.withViewModel(
        initialState = getSsrState<Contract.State>(json) ?: Contract.State(Page.Home),
        inputHandler = inputHandler,
        name = "KiluaDev ViewModel",
    ).build(),
    eventHandler = eventHandler,
    coroutineScope = coroutineScope,
)
