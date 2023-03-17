package seedu.loyaltylift.model.customer;

import static java.util.Objects.requireNonNull;

import seedu.loyaltylift.commons.exceptions.IllegalValueException;

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
    public final Integer cumulative;

    /**
     * Constructs an {@code Points}.
     *
     * @param points A valid amount of points.
     */
    public Points(Integer points, Integer maxPoints) {
        requireNonNull(points);
        value = points;
        cumulative = maxPoints;
    }

    /**
     * For adding or subtracting points.
     *
     * @param p the object with points to be added or subtracted from
     * @return a new Points object, value depends on whether it is an addition or subtraction.
     * @throws IllegalValueException when points are not valid
     */
    public Points editPoints(Points.AddPoints p) throws IllegalValueException {
        Points newPoints = p.modifier.isPositive()
                ? addPoints(p)
                : subtractPoints(p);
        return newPoints;
    }

    private Points addPoints(Points.AddPoints p) throws IllegalValueException {
        Integer newPoints = this.value + p.value;
        Integer newCumulativePoints = this.cumulative + p.value;
        if (isValidPoints(newPoints) && isValidPoints(newCumulativePoints)) {
            return new Points(newPoints, newCumulativePoints);
        } else {
            throw new IllegalValueException(Points.MESSAGE_CONSTRAINTS);
        }
    }

    private Points subtractPoints(Points.AddPoints p) throws IllegalValueException {
        Integer newPoints = this.value - p.value;
        if (isValidPoints(newPoints)) {
            return new Points(newPoints, this.cumulative);
        } else {
            throw new IllegalValueException(Points.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Returns true if a given point is valid.
     */
    public static boolean isValidPoints(Integer test) {
        return (test >= MINIMUM_POINTS && test <= MAXIMUM_POINTS);
    }

    @Override
    public String toString() {
        return value.toString()
                + " (Cumulative: "
                + cumulative.toString()
                + ")";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Points // instanceof handles nulls
                && value.equals(((Points) other).value)) // state check
                && cumulative.equals(((Points) other).cumulative);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Represents a temporary Points object, to be used for addition or subtraction.
     */
    public static class AddPoints {
        /**
         * Represents a Point's modifier.
         * Currently, it is either a PLUS or MINUS to represent addition and subtraction respectively.
         */
        public enum Modifier {
            PLUS("+"),
            MINUS("-");

            public static final String MESSAGE_CONSTRAINTS = "Modifier must '+' or '-'";

            private String value;
            private Modifier(String value) {
                this.value = value;
            }

            public boolean isPositive() {
                return (this.toString().compareTo("+") == 0);
            }

            public String toString() {
                return this.value;
            }
        }

        public static final String MESSAGE_CONSTRAINTS = "In the addpoints command, points must be an integer.\n"
                + "Points can only range from 0 to 999 999.\n"
                + "A modifier, '+' or '-' can be added in front of points to add or subtract points respectively.\n"
                + "If no such modifier exists, addpoints command will default to an addition.";

        public final Integer value;
        public final Modifier modifier;

        /**
         * Constructs an {@code Points.AddPoints}.
         *
         * @param value A valid amount of points.
         * @param modifier A valid modifier.
         */
        public AddPoints(Integer value, Modifier modifier) {
            this.value = value;
            this.modifier = modifier;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof Points.AddPoints)) {
                return false;
            }

            // state check
            Points.AddPoints e = (Points.AddPoints) other;
            return value.equals(e.value)
                    && modifier.equals(e.modifier);
        }
    }
}
