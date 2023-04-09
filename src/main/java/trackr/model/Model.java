package trackr.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import trackr.commons.core.GuiSettings;
import trackr.model.item.Item;
import trackr.model.item.ReadOnlyItemList;
import trackr.model.menu.ItemProfit;
import trackr.model.menu.ItemSellingPrice;
import trackr.model.menu.MenuItem;
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

    /**
     * Sets the item list for the specified {@code ModelEnum}.
     */
    <T extends Item> void setItemList(ModelEnum modelEnum);

    /**
     * Returns an unmodifiable view of the item list for the specified {@code ModelEnum}.
     * @return an unmodifiable view of the item list for the specified {@code ModelEnum}.
     */
    ReadOnlyItemList<? extends Item> getItemList(ModelEnum modelEnum);

    /**
     * Returns true if the specified item exists in the item list for the specified {@code ModelEnum}.
     * @return true if the specified item exists in the item list for the specified {@code ModelEnum}, false otherwise.
     */
    <T extends Item> boolean hasItem(T item, ModelEnum modelEnum);

    /**
     * Deletes the specified item from the item list for the specified {@code ModelEnum}.
     */
    <T extends Item> void deleteItem(T item, ModelEnum modelEnum);

    /**
     * Adds the specified item to the item list for the specified {@code ModelEnum}.
     */
    <T extends Item> void addItem(T item, ModelEnum modelEnum);

    /**
     * Replaces the specified item in the item list for the specified {@code ModelEnum} with the edited item.
     */
    <T extends Item> void setItem(T item, T itemEdited, ModelEnum modelEnum);

    /**
     * Returns an {@code ObservableList} of items that have been filtered based on the specified criteria for the
     * specified {@code ModelEnum}.
     */
    ObservableList<? extends Item> getFilteredItemList(ModelEnum modelEnum);

    /**
     * Updates the filtered list of items according to the given {@code predicate}.
     *
     * @throws NullPointerException If {@code predicate} or {@code modelEnum} is {@code null}.
     */
    void updateFilteredItemList(Predicate<Item> predicate, ModelEnum modelEnum);


    // =================================================== Supplier ===================================================

    /**
     * Returns the SupplierList.
     */
    ReadOnlySupplierList getSupplierList();

    /**
     * Returns an unmodifiable view of the filtered supplier list.
     */
    ObservableList<Supplier> getFilteredSupplierList();

    /**
     * Sorts the filtered order list.
     */
    void sortFilteredOrderList(Comparator<Order> comparator);

    // ===================================================== Task =====================================================

    /**
     * Returns the TaskList.
     */
    ReadOnlyTaskList getTaskList();

    /**
     * Returns an unmodifiable view of the filtered task list.
     */
    ObservableList<Task> getFilteredTaskList();

    /**
     * Sorts the filtered task list.
     */
    void sortFilteredTaskList(Comparator<Task> comparator);

    // ================================================= Order =================================================

    /**
     * Returns the OrderList
     */
    ReadOnlyOrderList getOrderList();

    /**
     * Returns an unmodifiable view of the filtered order list
     */
    ObservableList<Order> getFilteredOrderList();

    // ===================================================== Menu Item ========================================

    /**
     * Returns the Menu
     */
    ReadOnlyMenu getMenu();

    /**
     * Returns an unmodifiable view of the filtered Menu Item list
     */
    ObservableList<MenuItem> getFilteredMenu();

    // ===================================================== Calculation ========================================

    /** Returns cumulative profits */
    ItemProfit getTotalProfits();

    /** Returns cumulative sales/ revenue */
    ItemSellingPrice getTotalSales();
}
