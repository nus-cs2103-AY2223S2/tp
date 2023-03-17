package seedu.loyaltylift.model.customer;

import seedu.loyaltylift.commons.exceptions.IllegalValueException;
import seedu.loyaltylift.logic.commands.AddPointsCommand;

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

    public Points editPoints(Points.AddPoints p) throws IllegalValueException {
        Points newPoints = p.modifier.isPositive()
                ? addPoints(p)
                : subtractPoints(p);
        return newPoints;
    }

    private Points addPoints(Points.AddPoints p) throws IllegalValueException {
        Integer newPoints = this.value + p.value;
        if (isValidPoints(newPoints)) {
            return new Points(newPoints);
        } else {
            throw new IllegalValueException(Points.MESSAGE_CONSTRAINTS);
        }
    }

    private Points subtractPoints(Points.AddPoints p) throws IllegalValueException {
        Integer newPoints = this.value - p.value;
        if (isValidPoints(newPoints)) {
            return new Points(newPoints);
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

    public static class AddPoints {
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

        public final Integer value;
        public final Modifier modifier;
        public static final String MESSAGE_CONSTRAINTS = "In the addpoints command, points must be an integer.\n"
                + "Points can only range from 0 to 999 999.\n"
                + "A modifier, '+' or '-' can be added in front of points to add or subtract points respectively.\n"
                + "If no such modifier exists, addpoints command will default to an addition.";

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
