package seedu.loyaltylift.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.loyaltylift.commons.core.GuiSettings;
import seedu.loyaltylift.model.customer.Customer;
import seedu.loyaltylift.model.order.Order;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Customer> PREDICATE_SHOW_ALL_CUSTOMERS = unused -> true;
    Predicate<Order> PREDICATE_SHOW_ALL_ORDERS = unused -> true;

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
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a customer with the same identity as {@code customer} exists in the address book.
     */
    boolean hasCustomer(Customer customer);

    /**
     * Deletes the given customer.
     * The customer must exist in the address book.
     */
    void deleteCustomer(Customer target);

    /**
     * Adds the given customer.
     * {@code customer} must not already exist in the address book.
     */
    void addCustomer(Customer customer);

    /**
     * Replaces the given customer {@code target} with {@code editedCustomer}.
     * {@code target} must exist in the address book.
     * The customer identity of {@code editedCustomer} must not be the same as
     * another existing customer in the address book.
     */
    void setCustomer(Customer target, Customer editedCustomer);

    /**
     * Sets the {@code Customer} object to be displayed in the information panel.
     * @param customer A Customer object.
     */
    void setCustomerToDisplay(Customer customer);

    /**
     * Returns the {@code Customer} object to be displayed in the information panel.
     * @return A Customer object.
     */
    Customer getCustomerToDisplay();

    /** Returns an unmodifiable view of the filtered customer list */
    ObservableList<Customer> getFilteredCustomerList();

    /**
     * Updates the filter of the filtered customer list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredCustomerList(Predicate<Customer> predicate);

    /**
     * Sorts the filtered customer list using the given {@code comparator}.
     * @throws NullPointerException if {@code comparator} is null.
     */
    void sortFilteredCustomerList(Comparator<Customer> comparator);

    /**
     * Returns true if a order with the same identity as {@code order} exists in the address book.
     */
    boolean hasOrder(Order order);

    /**
     * Deletes the given order.
     * The order must exist in the address book.
     */
    void deleteOrder(Order target);

    /**
     * Adds the given order.
     * {@code order} must not already exist in the address book.
     */
    void addOrder(Order order);

    /**
     * Replaces the given order {@code target} with {@code editedOrder}.
     * {@code target} must exist in the address book.
     * The order identity of {@code editedOrder} must not be the same as another existing order in the address book.
     */
    void setOrder(Order target, Order editedOrder);

    /**
     * Sets the {@code Order} object to be displayed in the information panel.
     * @param order An Order object.
     */
    void setOrderToDisplay(Order order);

    /**
     * Returns the {@code Order} object to be displayed in the information panel.
     * @return An Order object.
     */
    Order getOrderToDisplay();

    /** Returns an unmodifiable view of the filtered order list */
    ObservableList<Order> getFilteredOrderList();

    /**
     * Updates the filter of the filtered order list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredOrderList(Predicate<Order> predicate);

    /**
     * Sorts the filtered order list using the given {@code comparator}.
     * @throws NullPointerException if {@code comparator} is null.
     */
    void sortFilteredOrderList(Comparator<Order> comparator);

    /** Returns an unmodifiable view of the filtered customer's order list */
    ObservableList<Order> getFilteredCustomerOrderList();

    /**
     * Updates the filter of the filtered customer's order list to filter by the given {@code customer}.
     * @throws NullPointerException if {@code customer} is null.
     */
    void updateFilteredCustomerOrderList(Customer customer);
}
