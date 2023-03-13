package seedu.address.model.person;

import java.util.function.Predicate;

/*
    ContactContainsEmailPredicate is a predicate that filters the model based on address

    @author Haiqel Bin Hanaffi
 */
public class ContactContainsAddressPredicate implements Predicate<Person> {
    private final String address;

    public ContactContainsAddressPredicate(String address) {
        this.address = address;
    }

    @Override
    public boolean test(Person person) {
        // perform filtering based on address
        String currentAddress = person.getAddress().toString();

        return (currentAddress.equals(this.address) ? true : false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactContainsAddressPredicate // instanceof handles nulls
                && address.equals(((ContactContainsAddressPredicate) other).address)); // state check
    }
}
