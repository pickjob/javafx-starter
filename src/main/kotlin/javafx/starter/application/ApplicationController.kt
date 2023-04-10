package javafx.starter.application

import javafx.application.Platform
import javafx.beans.property.SimpleDoubleProperty
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.control.SelectionMode
import javafx.scene.control.SplitPane
import javafx.scene.control.Tab
import javafx.scene.control.TabPane
import javafx.scene.control.TreeItem
import javafx.scene.control.TreeView
import javafx.scene.input.MouseEvent
import javafx.starter.common.FxController
import javafx.starter.utils.FXML_SHOWCASE_CONTROL
import mu.KotlinLogging
import org.slf4j.Logger

/**
 * @author: pickjob@126.com
 * @date: 2023-04-04
 */
class ApplicationController : FxController {
    @FXML
    lateinit var application: SplitPane

    @FXML
    lateinit var navigator: TreeView<NavigatorRecord>

    @FXML
    lateinit var details: TabPane

    override fun buildUI() {
        val rootItem = TreeItem(navigatorRecord)
        buildNavigator(rootItem)
        navigator.root = rootItem
        resizeNavigator()
    }

    override fun addHandler() {
        navigator.selectionModel.selectionMode = SelectionMode.SINGLE
        navigator.selectionModel.selectedItemProperty().addListener { _, _, newValue ->
            if (!newValue.isExpanded) {
                newValue.isExpanded = true
            }
            val selectedRecord = newValue.value
            logger.info("${selectedRecord.name} selected")
            if (selectedRecord.targetPath == "") return@addListener
            for (tab in details.tabs) {
                if (tab.userData == selectedRecord) {
                    details.selectionModel.select(tab)
                    return@addListener
                }
            }
            val tab = Tab(selectedRecord.name)
            val loader = FXMLLoader(javaClass.getResource(selectedRecord.targetPath))
            val tabContent: Parent = loader.load()
            tab.content = tabContent
            tab.userData = selectedRecord
            details.tabs.add(tab)
        }
        Platform.runLater {
            application.scene.widthProperty().addListener { _, _, _ ->
                resizeNavigator()
            }
        }
    }

    private var navigatorRecord: NavigatorRecord

    init {
        val controlShowcase = NavigatorRecord("ControlShowCase", FXML_SHOWCASE_CONTROL, emptyList())
        val showcase = NavigatorRecord("ShowCase", "", listOf(controlShowcase))
        navigatorRecord = NavigatorRecord("HOME", "", listOf(showcase))
    }

    private fun resizeNavigator() {
        Platform.runLater {
            application.setDividerPositions(divider.get())
        }
    }

    private fun buildNavigator(root: TreeItem<NavigatorRecord>) {
        for (navigator in root.value.children) {
            val leaf = TreeItem<NavigatorRecord>(navigator)
            root.children.add(leaf)
            buildNavigator(leaf)
        }
    }

    private val divider = SimpleDoubleProperty(0.2)
    private val logger: Logger = KotlinLogging.logger {}
}