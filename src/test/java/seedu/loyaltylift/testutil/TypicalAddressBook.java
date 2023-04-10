package seedu.loyaltylift.testutil;

import static seedu.loyaltylift.testutil.TypicalCustomers.getTypicalCustomers;
import static seedu.loyaltylift.testutil.TypicalOrders.getTypicalOrders;

import seedu.loyaltylift.model.AddressBook;
import seedu.loyaltylift.model.customer.Customer;
import seedu.loyaltylift.model.order.Order;

/**
 * A utility class contains the @{code AddressBook} to be used in tests.
 */
public class TypicalAddressBook {

    private TypicalAddressBook() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical customers.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Customer customer : getTypicalCustomers()) {
            ab.addCustomer(customer);
        }
        for (Order order : getTypicalOrders()) {
            ab.addOrder(order);
        }
        return ab;
    }


}
