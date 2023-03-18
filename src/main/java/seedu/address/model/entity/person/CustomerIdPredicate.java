package seedu.address.model.entity.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class CustomerIdPredicate implements Predicate<Customer> {
    private final int id;

    public CustomerIdPredicate(int id) {
        this.id = id;
    }

    @Override
    public boolean test(Customer person) {
        return person.getId() == id;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CustomerIdPredicate // instanceof handles nulls
                && id == ((CustomerIdPredicate) other).id); // state check
    }

}
