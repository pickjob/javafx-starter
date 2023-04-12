package javafx.starter.application

import javafx.application.Platform
import javafx.beans.property.SimpleDoubleProperty
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.control.SelectionMode
import javafx.scene.control.SplitPane
import javafx.scene.control.Tab
import javafx.scene.control.TabPane
import javafx.scene.control.TreeItem
import javafx.scene.control.TreeView
import javafx.starter.common.FXML_IKONLI
import javafx.starter.common.FXML_SHOWCASE_CONTROL
import javafx.starter.common.FXML_TRANSFORM
import javafx.starter.common.FxController
import mu.KotlinLogging
import org.kordamp.ikonli.javafx.FontIcon
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
        rootItem.graphic = navigatorRecord.icon
        buildNavigator(rootItem)
        navigator.root = rootItem
        resizeNavigator()
    }

    override fun addHandler() {
        navigator.selectionModel.selectionMode = SelectionMode.SINGLE
        navigator.selectionModel.selectedItemProperty().addListener { _, _, newValue ->
            newValue.isExpanded = !newValue.isExpanded
            val selectedRecord = newValue.value
            logger.info("${selectedRecord.name} selected")
            if (selectedRecord.targetPath == "") return@addListener
            for (tab in details.tabs) {
                if (tab.userData == selectedRecord) {
                    details.selectionModel.select(tab)
                    return@addListener
                }
            }
            val start = System.currentTimeMillis()
            val tab = Tab(selectedRecord.name)
            val loader = FXMLLoader(javaClass.getResource(selectedRecord.targetPath))
            val tabContent: Parent = loader.load()
            tab.content = tabContent
            tab.userData = selectedRecord
            details.tabs.add(tab)
            details.selectionModel.select(tab)
            val end = System.currentTimeMillis()
            logger.info("loading ${selectedRecord.name} elapse ${end - start} ")
        }
        Platform.runLater {
            application.scene.widthProperty().addListener { _, _, _ ->
                resizeNavigator()
            }
        }
    }

    private var navigatorRecord: NavigatorRecord

    init {
        val transform = NavigatorRecord("Transform", FontIcon("si-convertio"), FXML_TRANSFORM, emptyList())
        val ikonli = NavigatorRecord("Ikonli", FontIcon("si-torbrowser"), FXML_IKONLI, emptyList())
        val controlShowcase =
            NavigatorRecord("ControlShowCase", FontIcon("bxs-joystick-button"), FXML_SHOWCASE_CONTROL, emptyList())
        val showcase = NavigatorRecord("ShowCase", FontIcon("cil-list-rich"), "", listOf(controlShowcase))

        navigatorRecord = NavigatorRecord(
            "HOME", FontIcon("fas-home"),
            "",
            listOf(
                transform,
                ikonli,
                showcase,
            )
        )
    }

    private fun resizeNavigator() {
        Platform.runLater {
            application.setDividerPositions(divider.get())
        }
    }

    private fun buildNavigator(root: TreeItem<NavigatorRecord>) {
        for (navigator in root.value.children) {
            val leaf = TreeItem<NavigatorRecord>(navigator)
            leaf.graphic = navigator.icon
            root.children.add(leaf)
            buildNavigator(leaf)
        }
    }

    private val divider = SimpleDoubleProperty(0.2)
    private val logger: Logger = KotlinLogging.logger {}
}