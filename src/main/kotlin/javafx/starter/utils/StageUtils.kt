package fx.starter.utils

import javafx.stage.Screen
import javafx.stage.Stage

/**
 * @author: pickjob@126.com
 * @date: 2023-03-20
 */
fun centralizeStage(stage: Stage) {
    val bounds = Screen.getPrimary().visualBounds
    stage.width = bounds.width  * 2 / 3
    stage.height = bounds.height * 2 / 3
    stage.x = bounds.width / 6
    stage.y = bounds.height / 6
    stage.title = "JavaFX Application"
}