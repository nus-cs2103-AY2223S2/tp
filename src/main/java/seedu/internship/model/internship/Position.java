package seedu.internship.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.internship.commons.util.AppUtil.checkArgument;

/**
 * Represents an Internship's Position in the internship catalogue.
 * Guarantees: immutable; is valid as declared in {@link #isValidPosition(String)}
 */
public class Position {

    public static final String MESSAGE_CONSTRAINTS =
            "Names of position should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String positionName;

    /**
     * Constructs a {@code Position}.
     *
     * @param positionName A valid name for a Position.
     */
    public Position(String positionName) {
        requireNonNull(positionName);
        checkArgument(isValidPosition(positionName), MESSAGE_CONSTRAINTS);
        this.positionName = positionName;
    }

    /**
     * Returns true if a given string is a valid Position.
     */
    public static boolean isValidPosition(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return positionName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Position // instanceof handles nulls
                && positionName.equals(((Position) other).positionName)); // state check
    }

    @Override
    public int hashCode() {
        return positionName.hashCode();
    }

}
