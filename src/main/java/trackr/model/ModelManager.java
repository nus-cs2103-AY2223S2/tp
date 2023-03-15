package trackr.model;

import static java.util.Objects.requireNonNull;
import static trackr.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import trackr.commons.core.GuiSettings;
import trackr.commons.core.LogsCenter;
import trackr.model.order.Order;
import trackr.model.supplier.Supplier;
import trackr.model.task.Task;

/**
 * Represents the in-memory model of trackr data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final SupplierList supplierList;
    private final TaskList taskList;
    private final OrderList orderList;
    private final UserPrefs userPrefs;
    private final FilteredList<Supplier> filteredSuppliers;
    private final FilteredList<Task> filteredTasks;
    private final FilteredList<Order> filteredOrders;

    /**
     * Initializes a ModelManager with the given supplier list, taskList and userPrefs.
     */
    public ModelManager(ReadOnlySupplierList supplierList, ReadOnlyTaskList taskList,
            ReadOnlyOrderList orderList, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(supplierList, taskList, orderList, userPrefs);

        logger.fine("Initializing with supplier list: " + supplierList
                + " and task list: " + taskList
                + " and order list: " + orderList
                + " and user prefs " + userPrefs);

        this.supplierList = new SupplierList(supplierList);
        this.taskList = new TaskList(taskList);
        this.orderList = new OrderList(orderList);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredSuppliers = new FilteredList<>(this.supplierList.getSupplierList());
        filteredTasks = new FilteredList<>(this.taskList.getTaskList());
        filteredOrders = new FilteredList<>(this.orderList.getOrderList());
    }

    public ModelManager() {
        this(new SupplierList(), new TaskList(), new OrderList(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getTrackrFilePath() {
        return userPrefs.getTrackrFilePath();
    }

    @Override
    public void setTrackrFilePath(Path trackrFilePath) {
        requireNonNull(trackrFilePath);
        userPrefs.setTrackrFilePath(trackrFilePath);
    }

    //=========== AddressBook - Supplier ==============================================================================

    @Override
    public void setSupplierList(ReadOnlySupplierList supplierList) {
        this.supplierList.resetData(supplierList);
    }

    @Override
    public ReadOnlySupplierList getSupplierList() {
        return supplierList;
    }

    @Override
    public boolean hasSupplier(Supplier supplier) {
        requireNonNull(supplier);
        return supplierList.hasSupplier(supplier);
    }

    @Override
    public void deleteSupplier(Supplier target) {
        supplierList.removeSupplier(target);
    }

    @Override
    public void addSupplier(Supplier supplier) {
        supplierList.addSupplier(supplier);
        updateFilteredSupplierList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setSupplier(Supplier target, Supplier editedSupplier) {
        requireAllNonNull(target, editedSupplier);

        supplierList.setSupplier(target, editedSupplier);
    }

    //=========== Filtered Supplier List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Supplier} backed by the internal list of
     * {@code versionedSupplierList}
     */
    @Override
    public ObservableList<Supplier> getFilteredSupplierList() {
        return filteredSuppliers;
    }

    @Override
    public void updateFilteredSupplierList(Predicate<Supplier> predicate) {
        requireNonNull(predicate);
        filteredSuppliers.setPredicate(predicate);
    }

    //=========== TaskList ===================================================================================

    @Override
    public void setTaskList(ReadOnlyTaskList taskList) {
        this.taskList.resetData(taskList);
    }

    @Override
    public ReadOnlyTaskList getTaskList() {
        return taskList;
    }

    @Override
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return taskList.hasTask(task);
    }

    @Override
    public void deleteTask(Task target) {
        taskList.removeTask(target);
    }

    @Override
    public void addTask(Task task) {
        taskList.addTask(task);
        updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        taskList.setTask(target, editedTask);
    }

    //=========== Filtered Task List Accessors ===============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Task} backed by the internal list of
     * {@code versionedTaskList}
     */
    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return filteredTasks;
    }

    @Override
    public void updateFilteredTaskList(Predicate<Task> predicate) {
        requireNonNull(predicate);
        filteredTasks.setPredicate(predicate);
    }

    //=========== OrderList ===================================================================================

    @Override
    public void setOrderList(ReadOnlyOrderList orderList) {
        this.orderList.resetData(orderList);
    }

    @Override
    public ReadOnlyOrderList getOrderList() {
        return orderList;
    }

    @Override
    public boolean hasOrder(Order order) {
        requireNonNull(order);
        return orderList.hasOrder(order);
    }

    @Override
    public void deleteOrder(Order target) {
        orderList.removeOrder(target);
    }

    @Override
    public void addOrder(Order order) {
        orderList.addOrder(order);
        updateFilteredOrderList(PREDICATE_SHOW_ALL_ORDERS);
    }

    @Override
    public void setOrder(Order target, Order editedOrder) {
        requireAllNonNull(target, editedOrder);

        orderList.setOrder(target, editedOrder);
    }

    //=========== Filtered Order List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Order} backed by the internal list of
     * {@code versionedOrderList}
     */
    @Override
    public ObservableList<Order> getFilteredOrderList() {
        return filteredOrders;
    }

    @Override
    public void updateFilteredOrderList(Predicate<Order> predicate) {
        requireNonNull(predicate);
        filteredOrders.setPredicate(predicate);
    }

    //========================================================================================================

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return supplierList.equals(other.supplierList)
                && taskList.equals(other.taskList)
                && orderList.equals(other.orderList)
                && userPrefs.equals(other.userPrefs)
                && filteredSuppliers.equals(other.filteredSuppliers)
                && filteredTasks.equals(other.filteredTasks)
                && filteredOrders.equals(other.filteredOrders);
    }

}
