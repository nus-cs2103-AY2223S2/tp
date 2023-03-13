package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's profession in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidProfession(String)}
 */
public class Profession {

    public static final String MESSAGE_CONSTRAINTS =
            "Profession should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the profession must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String profession;
    public String value;

    /**
     * Constructs a {@code Profession}.
     *
     * @param profession A valid profession.
     */
    public Profession(String profession) {
        requireNonNull(profession);
        checkArgument(isValidProfession(profession), MESSAGE_CONSTRAINTS);
        this.profession = profession;
    }

    /**
     * Returns true if a given string is a valid profession.
     */
    public static boolean isValidProfession(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return profession;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Profession // instanceof handles nulls
                && profession.equals(((Profession) other).profession)); // state check
    }

    @Override
    public int hashCode() {
        return profession.hashCode();
    }

}
