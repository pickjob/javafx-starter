package javafx.starter.showcase

import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.scene.Node
import javafx.scene.control.Accordion
import javafx.scene.control.Button
import javafx.scene.control.ButtonBar
import javafx.scene.control.CheckBox
import javafx.scene.control.CheckMenuItem
import javafx.scene.control.ChoiceBox
import javafx.scene.control.ComboBox
import javafx.scene.control.DatePicker
import javafx.scene.control.Hyperlink
import javafx.scene.control.Label
import javafx.scene.control.ListView
import javafx.scene.control.Menu
import javafx.scene.control.MenuBar
import javafx.scene.control.MenuItem
import javafx.scene.control.Pagination
import javafx.scene.control.PasswordField
import javafx.scene.control.ProgressBar
import javafx.scene.control.ProgressIndicator
import javafx.scene.control.RadioButton
import javafx.scene.control.RadioMenuItem
import javafx.scene.control.SeparatorMenuItem
import javafx.scene.control.Slider
import javafx.scene.control.Spinner
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import javafx.scene.control.TitledPane
import javafx.scene.control.ToggleButton
import javafx.scene.control.ToggleGroup
import javafx.scene.control.ToolBar
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.starter.common.FxController
import javafx.starter.utils.STYLE_CLASS_H_BOX
import mu.KotlinLogging
import org.slf4j.Logger

/**
 * @author: pickjob@126.com
 * @date: 2023-04-06
 */
class ControlController : FxController {
    @FXML
    lateinit var details: VBox

    override fun buildUI() {
        val children = details.children
        // Label
        val label = Label("Label")
        children.add(
            createSection(
                "Label",
                label,
            )
        )

        // TextField、PasswordField
        val passwordField = PasswordField()
        passwordField.promptText = "PromptText"
        val textField = TextField("TextField")
        children.add(
            createSection(
                "TextField、PasswordField",
                textField,
                passwordField,
            )
        )

        // TextArea
        val textArea = TextArea()
        textArea.promptText = "PromptText"
        children.add(
            createSection(
                "TextArea",
                textArea,
            )
        )

        // Button、ToggleButton、Hyperlink
        val button = Button("Button")
        val hyperlink = Hyperlink("Hyperlink")
        children.add(
            createSection(
                "Button、Hyperlink",
                button,
                hyperlink,
            )
        )

        val toggleButtonGroup = ToggleGroup()
        val toggleButton1 = ToggleButton("ToggleButton1")
        toggleButton1.toggleGroup = toggleButtonGroup
        val toggleButton2 = ToggleButton("ToggleButton1")
        toggleButton2.toggleGroup = toggleButtonGroup
        val toggleButton3 = ToggleButton("ToggleButton1")
        toggleButton3.toggleGroup = toggleButtonGroup
        children.add(
            createSection(
                "ToggleButton",
                toggleButton1,
                toggleButton2,
                toggleButton3,
            )
        )

        // RadioButton
        val radioGroup = ToggleGroup()
        val radioBtn1 = RadioButton("RadioButton A")
        radioBtn1.toggleGroup = radioGroup
        val radioBtn2 = RadioButton("RadioButton B")
        radioBtn2.toggleGroup = radioGroup
        val radioBtn3 = RadioButton("RadioButton C")
        radioBtn3.toggleGroup = radioGroup
        children.add(
            createSection(
                " RadioButton",
                radioBtn1,
                radioBtn2,
                radioBtn3,
            )
        )

        // CheckBox
        val checkBox1 = CheckBox("CheckBox A")
        val checkBox2 = CheckBox("CheckBox B")
        val checkBox3 = CheckBox("CheckBox C")
        children.add(
            createSection(
                " CheckBox",
                checkBox1,
                checkBox2,
                checkBox3,
            )
        )

        // ButtonBar、ToolBar
        val yesButton = Button("Yes")
        ButtonBar.setButtonData(yesButton, ButtonBar.ButtonData.YES)
        val noButton = Button("No")
        ButtonBar.setButtonData(noButton, ButtonBar.ButtonData.NO)
        val buttonBar = ButtonBar()
        buttonBar.buttons.addAll(yesButton, noButton)
        val toolBar = ToolBar(
            Button("New"),
            Button("Open"),
            Button("Close"),
        )
        children.add(
            createSection(
                "ButtonBar、ToolBar",
                buttonBar,
                toolBar,
            )
        )

        // ProgressIndicator、ProgressBar
        val progressIndicator = ProgressIndicator()
        val progressBar = ProgressBar()
        children.add(
            createSection(
                "ProgressIndicator、ProgressBar",
                progressIndicator,
                progressBar,
            )
        )

        // Spinner、Slider
        val spinner = Spinner<Int>(0, 10, 5)
        val slider = Slider(0.0, 10.0, 5.0)
        children.add(
            createSection(
                "Spinner、Slider",
                spinner,
                slider,
            )
        )

        // Pagination
        val pagination = Pagination(3)
        children.add(
            createSection(
                "Pagination",
                pagination,
            )
        )

        // MenuBar、Menu、MenuItem
        val menu1 = Menu("File")
        menu1.items.addAll(
            MenuItem("MenuItem"),
            SeparatorMenuItem(),
            RadioMenuItem("RadioMenuItem"),
            CheckMenuItem("CheckMenuItem"),
        )
        val menu2 = Menu("Options")
        val menu3 = Menu("Help")
        val menuBar = MenuBar(menu1, menu2, menu3)
        children.add(
            createSection(
                "MenuBar、Menu、MenuItem",
                menuBar,
            )
        )

        val items = FXCollections.observableList(mutableListOf("Item A", "Item B", "Item C"))

        // ListView
        val listView = ListView<String>()
        listView.items.addAll(items)
        children.add(
            createSection(
                "ListView",
                listView,
            )
        )

        // ChoiceBox、ComboBox
        val choiceBox = ChoiceBox<String>()
        choiceBox.items.addAll(items)
        val comboBox = ComboBox<String>()
        comboBox.items.addAll(items)
        children.add(
            createSection(
                "ChoiceBox、ComboBox",
                choiceBox,
                comboBox,
            )
        )

        // DatePicker
        val datePicker = DatePicker()
        children.add(
            createSection(
                "DatePicker",
                datePicker,
            )
        )

        // Accordion、TitledPane
        val titledPane1 = TitledPane("TitledPane1", Label("TitledPane1"))
        val titledPane2 = TitledPane("TitledPane2", Label("TitledPane2"))
        val titledPane3 = TitledPane("TitledPane3", Label("TitledPane3"))
        val accordion = Accordion()
        accordion.panes.addAll(titledPane1, titledPane2, titledPane3)

        children.add(
            createSection("Accordion、TitledPane", accordion)
        )
    }

    override fun addHandler() {
    }

    private fun createSection(label: String, vararg nodes: Node): Node {
        val sectionLabel = Label(label)
        val sectionContent = HBox()
        sectionContent.children.addAll(nodes)
        sectionContent.styleClass.add(STYLE_CLASS_H_BOX)
        sectionLabel.labelFor = sectionContent

        val section = HBox()
        section.styleClass.add(STYLE_CLASS_H_BOX)
        section.children.addAll(sectionLabel, sectionContent)
        return section
    }

    private val logger: Logger = KotlinLogging.logger {}
}
