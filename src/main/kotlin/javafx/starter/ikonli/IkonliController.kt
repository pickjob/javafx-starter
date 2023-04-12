package javafx.starter.ikonli

import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.scene.control.TextField
import javafx.scene.control.Tooltip
import javafx.scene.input.Clipboard
import javafx.scene.input.DataFormat
import javafx.scene.input.MouseEvent
import javafx.scene.layout.GridPane
import javafx.starter.common.FxController
import javafx.starter.utils.coroutinue.FxCoroutineScope
import javafx.starter.utils.coroutinue.throttleLatest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.javafx.JavaFx
import kotlinx.coroutines.javafx.asFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mu.KotlinLogging
import org.kordamp.ikonli.Ikon
import org.kordamp.ikonli.IkonProvider
import org.kordamp.ikonli.javafx.FontIcon
import org.slf4j.Logger
import java.util.*


/**
 * @author: pickjob@126.com
 * @date: 2023-04-10
 */
class IkonliController : FxController {
    @FXML
    lateinit var keyWord: TextField

    @FXML
    lateinit var cheatSheet: GridPane


    override fun buildUI() {
    }

    override fun addHandler() {
        val providers = ServiceLoader.load(IkonProvider::class.java)
        val icons = mutableListOf<FontIcon>()
        for (provider in providers) {
            for (ikon in EnumSet.allOf(provider.getIkon() as Class<out Enum<*>>)) {
                val ikon = ikon as Ikon
                val icon = FontIcon.of(ikon)
                val tooltip = Tooltip(ikon.description)
                icon.onMouseClicked = EventHandler<MouseEvent> { event ->
                    val map = hashMapOf<DataFormat, Any>()
                    map.put(DataFormat.PLAIN_TEXT, ikon.description)
                    Clipboard.getSystemClipboard().setContent(map)
                }
                icon.userData = tooltip
                Tooltip.install(icon, tooltip)
                icons.add(icon)
            }
        }
        cheatSheet.userData = icons
        FxCoroutineScope.launch {
            keyWord.textProperty().asFlow().throttleLatest().filter {
                it != ""
            }.collectLatest { key ->
                withContext(Dispatchers.JavaFx) {
                    reset()
                }
                val icons = cheatSheet.userData as List<FontIcon>
                icons.asFlow().filter { icon ->
                    icon.iconCode.description.contains(key)
                }.buffer(columnCount).collect {
                    withContext(Dispatchers.JavaFx) {
                        addFontIcon(it)
                    }
                }
            }
        }
    }

    private fun addFontIcon(icon: FontIcon) {
        iconIdx++
        cheatSheet.add(icon, iconIdx % columnCount, iconIdx / columnCount)
    }

    private fun reset() {
        cheatSheet.children.clear()
        iconIdx = 0
    }

    private var iconIdx = 0
    private val columnCount = 20
    private val logger: Logger = KotlinLogging.logger {}
}