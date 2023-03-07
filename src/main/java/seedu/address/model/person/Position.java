package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Position in the openings list.
 * Guarantees: immutable;
 */
public class Position {

    public final String value;

    /**
     * Constructs an {@code Position}.
     *
     * @param position A valid position.
     */
    public Position(String position) {
        requireNonNull(position);
        value = position;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Position // instanceof handles nulls
                && value.equals(((Position) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
