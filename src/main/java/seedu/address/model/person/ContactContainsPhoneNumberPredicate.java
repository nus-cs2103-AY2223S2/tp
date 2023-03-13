package seedu.address.model.person;

import java.util.function.Predicate;

/*
    ContactContainsPhoneNumberPredicate is a predicate that filters the model based on phone number

    @author Haiqel Bin Hanaffi
 */
public class ContactContainsPhoneNumberPredicate implements Predicate<Person> {
    private final String phoneNumber;

    public ContactContainsPhoneNumberPredicate(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean test(Person person) {
        // perform filtering based on phoneNumber
        String currentNumber = person.getPhone().toString();
        return (currentNumber.equals(this.phoneNumber) ? true : false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactContainsPhoneNumberPredicate // instanceof handles nulls
                && phoneNumber.equals(((ContactContainsPhoneNumberPredicate) other).phoneNumber)); // state check
    }
}
