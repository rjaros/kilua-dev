package website

import com.copperleaf.ballast.EventHandler
import com.copperleaf.ballast.EventHandlerScope

class EventHandler : EventHandler<
        Contract.Inputs,
        Contract.Events,
        Contract.State> {
    override suspend fun EventHandlerScope<
            Contract.Inputs,
            Contract.Events,
            Contract.State>.handleEvent(
        event: Contract.Events
    ) {
    }
}
