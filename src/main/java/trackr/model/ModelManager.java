package trackr.model;

import static java.util.Objects.requireNonNull;
import static trackr.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import trackr.commons.core.GuiSettings;
import trackr.commons.core.LogsCenter;
import trackr.model.item.Item;
import trackr.model.item.ReadOnlyItemList;
import trackr.model.menu.ItemProfit;
import trackr.model.menu.ItemSellingPrice;
import trackr.model.menu.MenuItem;
import trackr.model.order.Order;
import trackr.model.person.Supplier;
import trackr.model.task.Task;

/**
 * Represents the in-memory model of trackr data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final SupplierList supplierList;
    private final TaskList taskList;
    private final Menu menu;
    private final OrderList orderList;
    private final UserPrefs userPrefs;
    private final FilteredList<Supplier> filteredSuppliers;
    private final FilteredList<Task> filteredTasks;
    private final FilteredList<Order> filteredOrders;
    private final FilteredList<MenuItem> filteredMenuItems;

    /**
     * Initializes a ModelManager with the given supplier list, taskList and userPrefs.
     */
    public ModelManager(ReadOnlySupplierList supplierList, ReadOnlyTaskList taskList, ReadOnlyMenu menu,
                        ReadOnlyOrderList orderList, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(supplierList, taskList, orderList, userPrefs);

        logger.fine("Initializing with supplier list: " + supplierList
                + " and task list: " + taskList
                + " and menu: " + menu
                + " and order list: " + orderList
                + " and user prefs " + userPrefs);

        this.supplierList = new SupplierList(supplierList);
        this.taskList = new TaskList(taskList);
        this.menu = new Menu(menu);
        this.orderList = new OrderList(orderList);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredSuppliers = new FilteredList<>(this.supplierList.getItemList());
        filteredTasks = new FilteredList<>(this.taskList.getItemList());
        filteredOrders = new FilteredList<>(this.orderList.getItemList());
        filteredMenuItems = new FilteredList<>(this.menu.getItemList());

    }

    public ModelManager() {
        this(new SupplierList(), new TaskList(), new Menu(), new OrderList(), new UserPrefs());
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

    // =================================================== Item =======================================================

    @Override
    public <T extends Item> void setItemList(ModelEnum modelEnum) {
        switch (modelEnum) {
        case SUPPLIER:
            this.supplierList.resetData(supplierList);
            break;
        case TASK:
            this.taskList.resetData(taskList);
            break;
        case MENUITEM:
            this.menu.resetData(menu);
            break;
        case ORDER:
            this.orderList.resetData(orderList);
            break;
        case CUSTOMER:
        default:
            break;
        }
    }

    @Override
    public ReadOnlyItemList<? extends Item> getItemList(ModelEnum modelEnum) {
        switch (modelEnum) {
        case SUPPLIER:
            return supplierList;
        case TASK:
            return taskList;
        case MENUITEM:
            return menu;
        case ORDER:
            return orderList;
        case CUSTOMER:
        default:
            return null;
        }
    }

    @Override
    public <T extends Item> boolean hasItem(T item, ModelEnum modelEnum) {
        requireNonNull(item);
        switch (modelEnum) {
        case SUPPLIER:
            return supplierList.hasItem((Supplier) item);
        case TASK:
            return taskList.hasItem((Task) item);
        case MENUITEM:
            return menu.hasItem((MenuItem) item);
        case ORDER:
            return orderList.hasItem((Order) item);
        case CUSTOMER:
        default:
            return false;
        }
    }

    @Override
    public <T extends Item> void deleteItem(T item, ModelEnum modelEnum) {
        switch (modelEnum) {
        case SUPPLIER:
            supplierList.removeItem((Supplier) item);
            break;
        case TASK:
            taskList.removeItem((Task) item);
            break;
        case MENUITEM:
            menu.removeItem((MenuItem) item);
            break;
        case ORDER:
            orderList.removeItem((Order) item);
            break;
        case CUSTOMER:
        default:
            break;
        }
    }

    @Override
    public <T extends Item> void addItem(T item, ModelEnum modelEnum) {
        switch (modelEnum) {
        case SUPPLIER:
            supplierList.addItem((Supplier) item);
            updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS, modelEnum);
            break;
        case TASK:
            taskList.addItem((Task) item);
            updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS, modelEnum);
            break;
        case MENUITEM:
            menu.addItem((MenuItem) item);
            updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS, modelEnum);
            break;
        case ORDER:
            orderList.addItem((Order) item);
            updateFilteredItemList(PREDICATE_SHOW_ALL_ITEMS, modelEnum);
            break;
        case CUSTOMER:
        default:
            break;
        }
    }

    @Override
    public <T extends Item> void setItem(T item, T itemEdited, ModelEnum modelEnum) {
        requireAllNonNull(item, itemEdited);
        switch (modelEnum) {
        case SUPPLIER:
            supplierList.setItem((Supplier) item, (Supplier) itemEdited);
            break;
        case TASK:
            taskList.setItem((Task) item, (Task) itemEdited);
            break;
        case MENUITEM:
            menu.setItem((MenuItem) item, (MenuItem) itemEdited);
            break;
        case ORDER:
            orderList.setItem((Order) item, (Order) itemEdited);
            break;
        case CUSTOMER:
        default:
            break;
        }
    }

    @Override
    public FilteredList<? extends Item> getFilteredItemList(ModelEnum modelEnum) {
        switch (modelEnum) {
        case SUPPLIER:
            return filteredSuppliers;
        case TASK:
            return filteredTasks;
        case MENUITEM:
            return filteredMenuItems;
        case ORDER:
            return filteredOrders;
        case CUSTOMER:
        default:
            return null;
        }
    }

    @Override
    public void updateFilteredItemList(Predicate<Item> predicate, ModelEnum modelEnum) {
        requireNonNull(predicate);
        switch (modelEnum) {
        case SUPPLIER:
            filteredSuppliers.setPredicate(predicate);
            break;
        case TASK:
            filteredTasks.setPredicate(predicate);
            break;
        case MENUITEM:
            filteredMenuItems.setPredicate(predicate);
            break;
        case ORDER:
            filteredOrders.setPredicate(predicate);
            break;
        case CUSTOMER:
        default:
            break;
        }
    }


    //=========== SupplierList - Supplier ==============================================================================

    @Override
    public ReadOnlySupplierList getSupplierList() {
        return supplierList;
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

    //=========== TaskList ===================================================================================

    @Override
    public ReadOnlyTaskList getTaskList() {
        return taskList;
    }

    /**
     * Update the sorted task list.
     * @param comparator The comparator used to sort the tasks.
     */
    @Override
    public void sortFilteredTaskList(Comparator<Task> comparator) {
        requireNonNull(comparator);
        taskList.sortItems(comparator);
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

    //=========== Menu ===================================================================================

    @Override
    public ReadOnlyMenu getMenu() {
        return menu;
    }

    //=========== Filtered Menu Accessors ===============================================================

    /**
     * Returns an unmodifiable view of the list of {@code MenuItem} backed by the internal list of
     * {@code versionedMenu}
     */
    @Override
    public ObservableList<MenuItem> getFilteredMenu() {
        return filteredMenuItems;
    }

    //=========== OrderList ===================================================================================

    @Override
    public ReadOnlyOrderList getOrderList() {
        return orderList;
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

    /**
     * Update the sorted order list.
     * @param comparator The comparator used to sort the orders.
     */
    @Override
    public void sortFilteredOrderList(Comparator<Order> comparator) {
        requireNonNull(comparator);
        orderList.sortItems(comparator);
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
                && menu.equals(other.menu)
                && orderList.equals(other.orderList)
                && userPrefs.equals(other.userPrefs)
                && filteredSuppliers.equals(other.filteredSuppliers)
                && filteredTasks.equals(other.filteredTasks)
                && filteredMenuItems.equals(other.filteredMenuItems)
                && filteredOrders.equals(other.filteredOrders);
    }

    //=========== Calculation =============================================================

    @Override
    public ItemProfit getTotalProfits() {
        ObservableList<Order> allOrders = this.getFilteredOrderList();
        Double total = allOrders.stream()
                .map(x -> x.getTotalProfit().getValue())
                .reduce(0.0, (subTotal, element) -> subTotal + element);
        return new ItemProfit(total);
    }

    @Override
    public ItemSellingPrice getTotalSales() {
        ObservableList<Order> allOrders = this.getFilteredOrderList();
        Double total = allOrders.stream()
                .map(x -> x.getTotalRevenue().getValue())
                .reduce(0.0, (subTotal, element) -> subTotal + element);
        return new ItemSellingPrice(total);
    }

}
