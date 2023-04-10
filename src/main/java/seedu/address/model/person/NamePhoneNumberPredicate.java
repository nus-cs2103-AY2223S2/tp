package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} and {@code Phone} matches the name and phone given.
 */
public class NamePhoneNumberPredicate implements Predicate<Person> {
    private final Name name;
    private final Phone phone;

    /**
     * Constructor for NamePhoneNumberPredicate.
     * @param name Name of the person.
     * @param phone Phone number of the person.
     */
    public NamePhoneNumberPredicate(Name name, Phone phone) {
        this.name = name;
        this.phone = phone;
    }

    @Override
    public boolean test(Person person) {
        return person.getName().equals(name) && person.getPhone().equals(phone);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NamePhoneNumberPredicate // instanceof handles nulls
                && name.equals(((NamePhoneNumberPredicate) other).name) // state check
                && phone.equals(((NamePhoneNumberPredicate) other).phone)); // state check
    }
}
