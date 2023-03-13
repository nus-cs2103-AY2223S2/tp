package seedu.address.model.person;

import java.util.function.Predicate;

/*
    ContactContainsEmailPredicate is a predicate that filters the model based on email

    @author Haiqel Bin Hanaffi
 */
public class ContactContainsEmailPredicate implements Predicate<Person> {
    private final String emailAddr;

    public ContactContainsEmailPredicate(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    @Override
    public boolean test(Person person) {
        // perform filtering based on email
        String currentEmailAddr = person.getEmail().toString();
        return (currentEmailAddr.equals(this.emailAddr) ? true : false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactContainsEmailPredicate // instanceof handles nulls
                && emailAddr.equals(((ContactContainsEmailPredicate) other).emailAddr)); // state check
    }
}
