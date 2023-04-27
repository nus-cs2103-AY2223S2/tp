package trackr.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import trackr.commons.exceptions.IllegalValueException;
import trackr.model.Menu;
import trackr.model.OrderList;
import trackr.model.ReadOnlyMenu;
import trackr.model.ReadOnlyOrderList;
import trackr.model.ReadOnlySupplierList;
import trackr.model.ReadOnlyTaskList;
import trackr.model.SupplierList;
import trackr.model.TaskList;
import trackr.model.menu.MenuItem;
import trackr.model.order.Order;
import trackr.model.person.Supplier;
import trackr.model.task.Task;

/**
 * An Immutable Trackr that is serializable to JSON format.
 */
@JsonRootName(value = "trackr")
class JsonSerializableTrackr {

    public static final String MESSAGE_DUPLICATE_PERSON = "Suppliers list contains duplicate supplier(s).";
    public static final String MESSAGE_DUPLICATE_TASK = "Tasks list contains duplicate task(s).";
    public static final String MESSAGE_DUPLICATE_ORDER = "Orders list contains duplicate order(s).";
    public static final String MESSAGE_DUPLICATE_MENUITEM = "Menu contains duplicate menu item(s).";

    private final List<JsonAdaptedSupplier> suppliers = new ArrayList<>();
    private final List<JsonAdaptedTask> tasks = new ArrayList<>();
    private final List<JsonAdaptedOrder> orders = new ArrayList<>();
    private final List<JsonAdaptedMenuItem> menuItems = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTrackr} with the given suppliers, tasks, order and menu items.
     */
    @JsonCreator
    public JsonSerializableTrackr(@JsonProperty("suppliers") List<JsonAdaptedSupplier> suppliers,
                                  @JsonProperty("tasks") List<JsonAdaptedTask> tasks,
                                  @JsonProperty("menuItems") List<JsonAdaptedMenuItem> menuItems,
                                  @JsonProperty("orders") List<JsonAdaptedOrder> orders) {
        this.suppliers.addAll(suppliers);
        this.tasks.addAll(tasks);
        this.menuItems.addAll(menuItems);
        this.orders.addAll(orders);
    }

    /**
     * Converts a given {@code ReadOnlySupplierList} and {@code ReadOnlyTaskList} into this class for Jackson use.
     *
     * @param sourceSupplier future changes to this will not affect the created {@code JsonSerializableTrackr}.
     * @param sourceTask future changes to this will not affect the created {@code JsonSerializableTrackr}.
     * @param sourceOrder future changes to this will not affect the created {@code JsonSerializableTrackr}.
     */
    public JsonSerializableTrackr(ReadOnlySupplierList sourceSupplier, ReadOnlyTaskList sourceTask,
                                  ReadOnlyMenu sourceMenuItem, ReadOnlyOrderList sourceOrder) {
        suppliers.addAll(sourceSupplier
            .getItemList()
            .stream()
            .map(JsonAdaptedSupplier::new)
            .collect(Collectors.toList()));
        tasks.addAll(sourceTask
                .getItemList()
                .stream()
                .map(JsonAdaptedTask::new)
                .collect(Collectors.toList()));
        menuItems.addAll(sourceMenuItem
                .getItemList()
                .stream()
                .map(JsonAdaptedMenuItem::new)
                .collect(Collectors.toList()));
        orders.addAll(sourceOrder
                .getItemList()
                .stream()
                .map(JsonAdaptedOrder::new)
                .collect(Collectors.toList()));
    }

    //@@author liumc-sg-reused
    /**
     * Converts this trackr into the model's {@code SupplierList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public SupplierList toModelType() throws IllegalValueException {
        SupplierList supplierList = new SupplierList();
        for (JsonAdaptedSupplier jsonAdaptedPerson : suppliers) {
            Supplier person = jsonAdaptedPerson.toModelType();
            if (supplierList.hasItem(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            supplierList.addItem(person);
        }
        return supplierList;
    }

    /**
     * Converts this trackr into the model's {@code TaskList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TaskList toTaskModelType() throws IllegalValueException {
        TaskList taskList = new TaskList();
        for (JsonAdaptedTask jsonAdaptedTask : tasks) {
            Task task = jsonAdaptedTask.toModelType();
            if (taskList.hasItem(task)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TASK);
            }
            taskList.addItem(task);
        }
        return taskList;
    }

    //@@author changgittyhub-reused
    /**
     * Converts this trackr into the model's {@code Menu} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Menu toMenuItemModelType() throws IllegalValueException {
        Menu menu = new Menu();
        for (JsonAdaptedMenuItem jsonAdaptedMenuItem : menuItems) {
            MenuItem menuItem = jsonAdaptedMenuItem.toModelType();
            if (menu.hasItem(menuItem)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_MENUITEM);
            }
            menu.addItem(menuItem);
        }
        return menu;
    }

    //@@author chongweiguan-reused
    /**
     * Converts this trackr into the model's {@code OrderList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public OrderList toOrderModelType() throws IllegalValueException {
        OrderList orderList = new OrderList();
        for (JsonAdaptedOrder jsonAdaptedOrder : orders) {
            Order order = jsonAdaptedOrder.toModelType();
            if (orderList.hasItem(order)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_ORDER);
            }
            orderList.addItem(order);
        }
        return orderList;
    }
    //@@author
}
