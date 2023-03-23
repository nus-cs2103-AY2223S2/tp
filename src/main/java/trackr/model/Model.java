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
     * Returns the SupplierList
     */
    ReadOnlySupplierList getSupplierList();

    /**
     * Returns an unmodifiable view of the filtered supplier list
     */
    ObservableList<Supplier> getFilteredSupplierList();

    // ===================================================== Task =====================================================

    /**
     * Returns the TaskList
     */
    ReadOnlyTaskList getTaskList();

    /**
     * Returns an unmodifiable view of the filtered task list
     */
    ObservableList<Task> getFilteredTaskList();

    // ================================================= Order =================================================

    /**
     * Returns the OrderList
     */
    ReadOnlyOrderList getOrderList();

    /**
     * Returns an unmodifiable view of the filtered order list
     */
    ObservableList<Order> getFilteredOrderList();
}
