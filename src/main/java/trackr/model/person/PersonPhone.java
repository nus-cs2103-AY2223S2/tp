package trackr.model.person;

import static java.util.Objects.requireNonNull;
import static trackr.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's phone number.
 * Guarantees: immutable; is valid as declared in {@link #isValidPersonPhone(String)}
 */
//@@author liumc-sg-reused
public class PersonPhone {

    public static final String MESSAGE_CONSTRAINTS =
            "Phone numbers should only contain numbers, and it should at least 3 digits long";
    public static final String VALIDATION_REGEX = "\\d{3,}";

    public final String personPhone;

    /**
     * Constructs a {@code PersonPhone}.
     *
     * @param personPhone A valid person phone number.
     */
    public PersonPhone(String personPhone) {
        requireNonNull(personPhone);
        checkArgument(isValidPersonPhone(personPhone), MESSAGE_CONSTRAINTS);
        this.personPhone = personPhone;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPersonPhone(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return personPhone;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonPhone // instanceof handles nulls
                && personPhone.equals(((PersonPhone) other).personPhone)); // state check
    }

    @Override
    public int hashCode() {
        return personPhone.hashCode();
    }

}
