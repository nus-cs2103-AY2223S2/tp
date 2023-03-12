package seedu.address.model.application;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the company's name for an Application in the internship book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 * Comment to let merge operation detect file. To be deleted subsequently.
 */
public class CompanyName {
    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String name;

    /**
     * Constructs a {@code CompanyName}.
     *
     * @param name A valid company name.
     */
    public CompanyName(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        this.name = name;
    }

    /**
     * Returns true if a given string is a valid company name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CompanyName // instanceof handles nulls
                && name.equals(((CompanyName) other).name)); // state check
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
