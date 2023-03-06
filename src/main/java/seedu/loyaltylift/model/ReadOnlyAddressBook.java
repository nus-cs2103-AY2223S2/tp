package seedu.loyaltylift.model;

import javafx.collections.ObservableList;
import seedu.loyaltylift.model.customer.Customer;
import seedu.loyaltylift.model.order.Order;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the customers list.
     * This list will not contain any duplicate customers.
     */
    ObservableList<Customer> getCustomerList();

    /**
     * Returns an unmodifiable view of the orders list.
     * This list will not contain any duplicate orders.
     */
    ObservableList<Order> getOrderList();

}
