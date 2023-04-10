package javafx.starter

import fr.brouillard.oss.cssfx.CSSFX
import fx.starter.utils.centralizeStage
import javafx.application.Application
import javafx.application.Platform
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import javafx.starter.utils.CSS_COLOR
import javafx.starter.utils.CSS_THEME
import javafx.starter.utils.FXML_JAVAFX_APPLICATION
import mu.KotlinLogging
import org.slf4j.Logger

class JavaFxApplication : Application() {

    override fun start(stage: Stage) {
        val loader = FXMLLoader(javaClass.getResource(FXML_JAVAFX_APPLICATION))
        val toolBox: Parent = loader.load()
        val scene = Scene(toolBox)
        scene.stylesheets.add(javaClass.getResource(CSS_COLOR).toString())
        scene.stylesheets.add(javaClass.getResource(CSS_THEME).toString())
        stage.scene = scene
        centralizeStage(stage)
        logger.info("JavaFx is running")
        stage.show()
    }

    override fun init() {
        super.init()
        val properties = System.getProperties()
        properties["cssfx.log"] = "TRUE"
        properties["cssfx.log.level"] = "INFO"
        CSSFX.start()
        Platform.runLater {
            logger.info("Initial UncaughtExceptionHandler")
            Thread.currentThread().setUncaughtExceptionHandler { t: Thread, e: Throwable ->
                logger.error("${t.name}[${t.id}] catch an unhandled exception !!!")
                logger.error(e.message, e)
            }
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(JavaFxApplication::class.java)
        }
    }

    private val logger: Logger = KotlinLogging.logger {}
}
