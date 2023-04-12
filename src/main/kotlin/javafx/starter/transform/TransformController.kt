package javafx.starter.transform

import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.CheckBox
import javafx.scene.control.ComboBox
import javafx.scene.control.Label
import javafx.scene.control.ListCell
import javafx.scene.control.ListView
import javafx.scene.control.TextArea
import javafx.starter.common.FxController
import javafx.starter.common.UI_DELAY
import javafx.starter.utils.coroutinue.FxCoroutineScope
import javafx.starter.utils.coroutinue.throttleLatest
import javafx.util.Callback
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.javafx.JavaFx
import kotlinx.coroutines.javafx.asFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mu.KotlinLogging
import org.slf4j.Logger
import java.util.*


/**
 * @author: pickjob@126.com
 * @date: 2023-04-10
 */
class TransformController : FxController {
    @FXML
    lateinit var src: TextArea

    @FXML
    lateinit var dest: TextArea

    @FXML
    lateinit var transform: Button

    @FXML
    lateinit var reverse: CheckBox

    @FXML
    lateinit var reverseLabel: Label

    @FXML
    lateinit var transType: ComboBox<ITransform>

    override fun buildUI() {
        val cellFactory = Callback<ListView<ITransform>, ListCell<ITransform>> {
            val regex = Regex("(.+)\\.(.+)ITransformImpl")

            object : ListCell<ITransform>() {
                override fun updateItem(item: ITransform?, empty: Boolean) {
                    super.updateItem(item, empty)
                    if (empty || item == null) {
                        text = null;
                        graphic = null;
                    } else {
                        val className = item.javaClass.name
                        text = if (regex.matches(className)) {
                            regex.find(className)!!.destructured.component2()
                        } else {
                            className
                        }
                    }
                }
            }
        }
        transType.buttonCell = cellFactory.call(null)
        transType.cellFactory = cellFactory
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun addHandler() {
        val loader: ServiceLoader<ITransform> = ServiceLoader.load(ITransform::class.java)
        for ((idx, transform) in loader.iterator().withIndex()) {
            transType.items.add(transform)
            if (idx == 0) {
                transType.value = transform
            }
        }
        FxCoroutineScope.launch {
            transType.selectionModel.selectedItemProperty()
                .asFlow()
                .throttleLatest()
                .filter {
                    it != null
                }
                .collect {
                    withContext(Dispatchers.JavaFx) {
                        reverse.isDisable = !it.supportReverse
                    }
                }
        }
        reverseLabel.onMouseClicked = EventHandler { _ ->
            if (!reverse.isDisabled) {
                reverse.isSelected = !reverse.isSelected
            }
        }
        transform.onMouseClicked = EventHandler { _ ->
            dest.text = if (reverse.isSelected) {
                transType.selectionModel.selectedItem.reverse(src.text)
            } else {
                transType.selectionModel.selectedItem.transform(src.text)
            }
        }
    }


    private val logger: Logger = KotlinLogging.logger {}
}