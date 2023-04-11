package javafx.starter.utils.coroutinue

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newFixedThreadPoolContext
import mu.KotlinLogging

/**
 * @author: pickjob@126.com
 * @date: 2023-03-22
 */
@OptIn(DelicateCoroutinesApi::class)
val Dispatchers.FxCoroutineDispatcher: CoroutineDispatcher
    get() = newFixedThreadPoolContext(
        Runtime.getRuntime().availableProcessors() / 2,
        "CoroutineDispatcher"
    )

val FxCoroutineScope = CoroutineScope(Dispatchers.FxCoroutineDispatcher)

val FxCoroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
    val logger = KotlinLogging.logger {}
    logger.error("catch coroutine exception", exception)
}
