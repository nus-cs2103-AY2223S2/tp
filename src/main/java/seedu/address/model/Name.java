package seedu.address.model;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a non-blank name of an entity in the tracker.
 * Guarantees: immutable, is valid as declared in {@link #isValidName(String)}.
 */
public class Name implements Comparable<Name> {

    /*
     * The first character of the name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String name;

    /**
     * Constructs a {@code Name} which is validated using {@link Name#VALIDATION_REGEX}.
     *
     * @param name A valid name.
     */
    public Name(String name, String constraintsMesssage) {
        this(name, VALIDATION_REGEX, constraintsMesssage);
    }

    /**
     * Constructs a {@code Name} which is validated using the {@code validationRegex}.
     *
     * @param name A valid name.
     * @param validationRegex The regex use to validate the name.
     */
    public Name(String name, String validationRegex, String constraintsMesssage) {
        requireAllNonNull(name, validationRegex);
        checkArgument(isValidName(name, validationRegex), constraintsMesssage);
        this.name = name;
    }

    @Override
    public int compareTo(Name o) {
        return name.toLowerCase().compareTo(o.name.toLowerCase());
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                        && name.equals(((Name) other).name)); // state check
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * Returns true if {@code test} is a valid name.
     *
     * @param test The string to check if it is a valid name.
     * @return True if {@code test} is a valid name. Otherwise, false.
     */
    public static boolean isValidName(String test) {
        return isValidName(test, VALIDATION_REGEX);
    }

    /**
     * Returns true if {@code test} is a valid name according to the {@code validationRegex}.
     *
     * @param test The string to check if it is a valid name.
     * @param validationRegex The regex to validate the name with.
     * @return True if {@code test} is a valid name. Otherwise, false.
     */
    protected static boolean isValidName(String test, String validationRegex) {
        requireAllNonNull(test, validationRegex);
        return test.matches(validationRegex);
    }

}
