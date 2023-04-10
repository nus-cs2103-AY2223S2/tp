package seedu.loyaltylift.model.customer;

import java.util.function.Predicate;

/**
 * Tests that a {@code Customer}'s {@code CustomerType} matches the given type.
 */
public class CustomerTypePredicate implements Predicate<Customer> {
    private final CustomerType customerType;

    public CustomerTypePredicate(CustomerType customerType) {
        this.customerType = customerType;
    }

    @Override
    public boolean test(Customer customer) {
        return customer.getCustomerType().equals(customerType);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CustomerTypePredicate // instanceof handles nulls
                && customerType.equals(((CustomerTypePredicate) other).customerType)); // state check
    }

}
