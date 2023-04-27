package trackr.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import trackr.commons.core.GuiSettings;
import trackr.logic.commands.CommandResult;
import trackr.logic.commands.exceptions.CommandException;
import trackr.logic.parser.exceptions.ParseException;
import trackr.model.ReadOnlyMenu;
import trackr.model.ReadOnlyOrderList;
import trackr.model.ReadOnlySupplierList;
import trackr.model.ReadOnlyTaskList;
import trackr.model.menu.ItemProfit;
import trackr.model.menu.ItemSellingPrice;
import trackr.model.menu.MenuItem;
import trackr.model.order.Order;
import trackr.model.person.Supplier;
import trackr.model.task.Task;

/**
 * API of the Logic component.
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     *
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the SupplierList.
     *
     * @see trackr.model.Model#getSupplierList()
     */
    ReadOnlySupplierList getSupplierList();

    /**
     * Returns an unmodifiable view of the filtered list of suppliers.
     */
    ObservableList<Supplier> getFilteredSupplierList();

    //@@author chongweiguan-reused
    /**
     * Returns the OrderList
     *
     * @see trackr.model.Model#getOrderList()
     */
    ReadOnlyOrderList getOrderList();

    /**
     * Returns an unmodifiable view of the filtered list of orders.
     */
    ObservableList<Order> getFilteredOrderList();
    //@@author

    /**
     * Returns cumulative profits.
     */
    ItemProfit getTotalProfits();

    /**
     * Returns cumulative sales/ revenue.
     */
    ItemSellingPrice getTotalSales();

    //@@author liumc-sg-reused
    /**
     * Returns the TaskList.
     *
     * @see trackr.model.Model#getTaskList()
     */
    ReadOnlyTaskList getTaskList();
    //@@author

    /**
     * Returns an unmodifiable view of the filtered list of tasks.
     */
    //@@author liumc-sg-reused
    ObservableList<Task> getFilteredTaskList();
    //@@author

    /**
     * Returns the Menu.
     *
     * @see trackr.model.Model#getMenu()
     */
    ReadOnlyMenu getMenu();

    /**
     * Returns an unmodifiable view of the filtered list of menu items.
     */
    ObservableList<MenuItem> getFilteredMenu();


    /**
     * Returns the user prefs' trackr file path.
     */
    Path getTrackrFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
