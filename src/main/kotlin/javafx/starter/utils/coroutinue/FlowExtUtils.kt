package javafx.starter.utils.coroutinue

import javafx.starter.common.UI_DELAY
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.transform

/**
 * @author: pickjob@126.com
 * @date: 2023-03-17
 */
fun <T> Flow<T>.throttleLatest(): Flow<T> = this
    .transform {
        emit(it)
        delay(UI_DELAY)
    }
