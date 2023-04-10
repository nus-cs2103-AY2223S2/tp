package trackr.model.person;

import static java.util.Objects.requireNonNull;
import static trackr.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's address.
 * Guarantees: immutable; is valid as declared in {@link #isValidPersonAddress(String)}
 */
//@@author liumc-sg-reused
public class PersonAddress {

    public static final String MESSAGE_CONSTRAINTS = "Addresses can take any values, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String personAddress;

    /**
     * Constructs an {@code PersonAddress}.
     *
     * @param personAddress A valid person address.
     */
    public PersonAddress(String personAddress) {
        requireNonNull(personAddress);
        checkArgument(isValidPersonAddress(personAddress), MESSAGE_CONSTRAINTS);
        this.personAddress = personAddress;
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidPersonAddress(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return personAddress;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonAddress // instanceof handles nulls
                && personAddress.equals(((PersonAddress) other).personAddress)); // state check
    }

    @Override
    public int hashCode() {
        return personAddress.hashCode();
    }

}
