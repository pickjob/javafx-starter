package javafx.starter.common

/**
 * @author: pickjob@126.com
 * @date: 2023-04-06
 */
interface FxController {
    fun buildUI()

    fun addHandler()

    fun initialize() {
        buildUI()
        addHandler()
    }
}