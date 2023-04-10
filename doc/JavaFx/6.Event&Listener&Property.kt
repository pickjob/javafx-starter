/**
 * JavaFx内置事件处理:
 *      核心接口、类:
 *          javafx.event.EventType<T extends Event>: 事件类别
 *          javafx.event.EventHandler<T extends Event> {
 *              void handle(T event);
 *          }
 *      事件继承结构:
 *          java.util.EventObject: source
 *              javafx.event.Event
 *                  javafx.scene.input.InputEvent
 *                      javafx.scene.input.MouseEvent
 *                      javafx.scene.input.DragEvent
 *                      javafx.scene.input.GestureEvent
 *                          javafx.scene.input.SwipeEvent
 *                  javafx.event.ActionEvent
 *                  javafx.stage.WindowEvent
 *                  javafx.scene.control.DialogEvent
 *
 * JavaFx内置Listener:
 *      javafx.beans.value.ChangeListener<T> = { value: ObservableValue<out Number?>?, oldVal: Number?, newVal: Number? -> }
 *
 * JavaFx内置Property处理:
 *      javafx.beans.property.Property<T>:
 *          单项绑定:
 *              bind(ObservableValue<? extends T> observable)
 *              unbind
 *          双向绑定:
 *              bindBidirectional(Property<T> other)
 *              unbindBidirectional(Property<T> other)
 */
