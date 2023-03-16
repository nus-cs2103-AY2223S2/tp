package seedu.loyaltylift.model.customer;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Customer's points in the address book.
 * The minimum points a customer can have is 0.
 */
public class Points {

    public static final String MESSAGE_CONSTRAINTS = "Points must be a positive integer "
            + "and can only range from 0 to 999 999";

    public static final Integer MAXIMUM_POINTS = 999999;
    public static final Integer MINIMUM_POINTS = 0;


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

    /**
     * Returns true if a given point is valid.
     */
    public static boolean isValidPoints(Integer test) {
        return (test >= MINIMUM_POINTS && test <= MAXIMUM_POINTS);
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
