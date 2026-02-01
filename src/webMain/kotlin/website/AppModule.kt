package website

import website.externals.getMarked
import dev.kilua.rest.RestClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val appModule = module {
    single {
        CoroutineScope(Dispatchers.Default + SupervisorJob())
    }
    single {
        Json {
            ignoreUnknownKeys = true
            prettyPrint = true
        }
    }
    single {
        RestClient()
    }
    single {
        getMarked()
    }
}
