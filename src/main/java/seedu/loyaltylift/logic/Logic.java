package seedu.loyaltylift.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.loyaltylift.commons.core.GuiSettings;
import seedu.loyaltylift.logic.commands.CommandResult;
import seedu.loyaltylift.logic.commands.exceptions.CommandException;
import seedu.loyaltylift.logic.parser.exceptions.ParseException;
import seedu.loyaltylift.model.ReadOnlyAddressBook;
import seedu.loyaltylift.model.customer.Customer;
import seedu.loyaltylift.model.order.Order;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the AddressBook.
     *
     * @see seedu.loyaltylift.model.Model#getAddressBook()
     */
    ReadOnlyAddressBook getAddressBook();

    /** Returns an unmodifiable view of the filtered list of customers */
    ObservableList<Customer> getFilteredCustomerList();

    /** Returns an unmodifiable view of the filtered list of orders */
    ObservableList<Order> getFilteredOrderList();

    /** Returns an unmodifiable view of the filtered list of orders belonging to a customer */
    ObservableList<Order> getFilteredCustomerOrderList();

    /** Returns an unmodifiable view of the filtered list of orders belonging to a customer */
    void updateFilteredCustomerOrderList(Customer customer);

    /**
     * Returns the {@code Customer} object to be displayed in the information panel.
     * @return A Customer object.
     */
    Customer getCustomerToDisplay();

    /**
     * Returns the {@code Order} object to be displayed in the information panel.
     * @return An Order object.
     */
    Order getOrderToDisplay();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
