package trackr.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import trackr.commons.core.GuiSettings;
import trackr.model.item.Item;
import trackr.model.item.ReadOnlyItemList;
import trackr.model.order.Order;
import trackr.model.person.Supplier;
import trackr.model.task.Task;

/**
 * The API of the Model component.
 */
public interface Model {

    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate PREDICATE_SHOW_ALL_ITEMS = unused -> true;

    // ================================================= User Prefs =================================================

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' trackr file path.
     */
    Path getTrackrFilePath();

    /**
     * Sets the user prefs' trackr file path.
     */
    void setTrackrFilePath(Path trackrFilePath);

    // =================================================== Item =======================================================

    <T extends Item> void setItemList(ModelEnum modelEnum);

    ReadOnlyItemList<? extends Item> getItemList(ModelEnum modelEnum);

    <T extends Item> boolean hasItem(T item, ModelEnum modelEnum);

    <T extends Item> void deleteItem(T item, ModelEnum modelEnum);

    <T extends Item> void addItem(T item, ModelEnum modelEnum);

    <T extends Item> void setItem(T item, T itemEdited, ModelEnum modelEnum);

    ObservableList<? extends Item> getFilteredItemList(ModelEnum modelEnum);

    void updateFilteredItemList(Predicate<Item> predicate, ModelEnum modelEnum);

    // =================================================== Supplier ===================================================

    /**
     * Replaces supplier list data with the data in {@code supplierList}.
     */
    void setSupplierList(ReadOnlySupplierList supplierList);

    /**
     * Returns the SupplierList
     */
    ReadOnlySupplierList getSupplierList();

    /**
     * Returns true if a Supplier with the same identity as {@code supplier} exists in the address book.
     */
    boolean hasSupplier(Supplier supplier);

    /**
     * Deletes the given supplier.
     * The supplier must exist in the address book.
     */
    void deleteSupplier(Supplier target);

    /**
     * Replaces the given supplier {@code target} with {@code editedSupplier}.
     * {@code target} must exist in the supplier list.
     * The person identity of {@code editedSupplier} must not be the same
     * as another existing supplier in the supplier list.
     */
    void setSupplier(Supplier target, Supplier editedSupplier);

    /**
     * Returns an unmodifiable view of the filtered supplier list
     */
    ObservableList<Supplier> getFilteredSupplierList();

    /**
     * Updates the filter of the filtered supplier list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredSupplierList(Predicate<Supplier> predicate);

    // ===================================================== Task =====================================================

    /**
     * Replaces task list data with the data in {@code taskList}.
     */
    void setTaskList(ReadOnlyTaskList taskList);

    /**
     * Returns the TaskList
     */
    ReadOnlyTaskList getTaskList();

    /**
     * Returns true if a task with the same identity as {@code task} exists in the task list.
     */
    boolean hasTask(Task task);

    /**
     * Deletes the given task.
     * The task must exist in the task list.
     */
    void deleteTask(Task target);

    /**
     * Replaces the given task {@code target} with {@code editedTask}.
     * {@code target} must exist in the task list.
     * The task identity of {@code editedTask} must not be the same as another existing task in the task list.
     */
    void setTask(Task target, Task editedTask);

    /**
     * Returns an unmodifiable view of the filtered task list
     */
    ObservableList<Task> getFilteredTaskList();

    /**
     * Updates the filter of the filtered task list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTaskList(Predicate<Task> predicate);

    // ================================================= Order =================================================

    /**
     * Replaces order list data with the data in {@code orderList}.
     */
    void setOrderList(ReadOnlyOrderList orderList);

    /**
     * Returns the OrderList
     */
    ReadOnlyOrderList getOrderList();

    /**
     * Returns true if an order with the same identity as {@code order} exists in the order list.
     */
    boolean hasOrder(Order order);

    /**
     * Deletes the given order.
     * The order must exist in the order list.
     */
    void deleteOrder(Order target);

    /**
     * Replaces the given order {@code target} with {@code editedOrder}.
     * {@code target} must exist in the order list.
     * The order identity of {@code editedOrder} must not be the same as another existing order in the order list.
     */
    void setOrder(Order target, Order editedOrder);

    /**
     * Returns an unmodifiable view of the filtered order list
     */
    ObservableList<Order> getFilteredOrderList();

    /**
     * Updates the filter of the filtered order list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredOrderList(Predicate<Order> predicate);
}
