package seedu.ultron.model.opening;

import static java.util.Objects.requireNonNull;
import static seedu.ultron.commons.util.AppUtil.checkArgument;

/**
 * Represents a Opening's position in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPosition(String)}
 */
public class Position {

    public static final String MESSAGE_CONSTRAINTS =
            "Positions should only contain alphanumeric characters and must not be blank.";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String fullPosition;

    /**
     * Constructs a {@code Position}.
     *
     * @param position A valid position.
     */
    public Position(String position) {
        requireNonNull(position);
        checkArgument(isValidPosition(position), MESSAGE_CONSTRAINTS);
        fullPosition = position;
    }

    /**
     * Returns true if a given string is a valid position.
     */
    public static boolean isValidPosition(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullPosition;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Position // instanceof handles nulls
                && fullPosition.replaceAll(" ", "").equalsIgnoreCase((
                        (Position) other).fullPosition.replaceAll(" ", ""))); // state check
    }

    @Override
    public int hashCode() {
        return fullPosition.hashCode();
    }

}
