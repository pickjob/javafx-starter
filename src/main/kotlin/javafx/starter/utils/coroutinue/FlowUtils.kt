package javafx.starter.utils.coroutinue

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

/**
 * @author: pickjob@126.com
 * @date: 2023-03-17
 */
fun <T> Flow<T>.throttleLatest(delayMillis: Long): Flow<T> = this
    .transform {
        emit(it)
        delay(delayMillis)
    }
