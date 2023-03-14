package seedu.loyaltylift.model.customer;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Customer's points in the address book.
 * The minimum points a customer can have is 0.
 */
public class Points {
    public final Integer value;

    /**
     * Constructs an {@code Points}.
     *
     * @param points A valid amount of points.
     */
    public Points(Integer points) {
        requireNonNull(points);
        value = points;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Points // instanceof handles nulls
                && value.equals(((Points) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
