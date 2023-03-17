package seedu.address.model.person.doctor;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Doctor's specialty in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidSpecialty(String)}
 */
public class Specialty {

    public static final String MESSAGE_CONSTRAINTS =
            "Specialty should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the specialty must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String specialty;

    /**
     * Constructs a {@code Specialty}.
     *
     * @param specialty A valid specialty.
     */
    public Specialty(String specialty) {
        requireNonNull(specialty);
        checkArgument(isValidSpecialty(specialty), MESSAGE_CONSTRAINTS);
        this.specialty = specialty;
    }

    /**
     * Returns true if a given string is a valid specialty.
     */
    public static boolean isValidSpecialty(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return specialty;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Specialty // instanceof handles nulls
                && specialty.equals(((Specialty) other).specialty)); // state check
    }

    @Override
    public int hashCode() {
        return specialty.hashCode();
    }
}
