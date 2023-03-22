package seedu.address.model.person.parent;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * A relationship class
 */
public class Relationship {
    public static final String MESSAGE_CONSTRAINTS = "Relationship must be letters";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String rls;

    /**
     * Constructs an {@code Address}.
     *
     * @param relationship A valid student age.
     */
    public Relationship(String relationship) {
        requireNonNull(relationship);
        checkArgument(isValidRelationship(relationship), MESSAGE_CONSTRAINTS);
        rls = relationship;
    }

    /**
     * Returns true if a given string is a valid relationship.
     */
    public static boolean isValidRelationship(String test) {
        if (isDefaultRelationship(test)) {
            return true;
        }
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is the default string given when a relationship isn't given by user.
     *
     * @param test String value to test.
     * @return Boolean value true if the string given is the default string by the system.
     */
    public static boolean isDefaultRelationship(String test) {
        return test.equals("Insert parent relationship to student here!");
    }

    @Override
    public String toString() {
        return rls;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Relationship// instanceof handles nulls
                && rls.equals(((Relationship) other).rls)); // state check
    }

    @Override
    public int hashCode() {
        return rls.hashCode();
    }
}
