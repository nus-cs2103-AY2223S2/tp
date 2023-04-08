package seedu.address.model.prescription;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the Cost of a patient's medication
 * Guarantees: immutable; is valid as declared in {@link #isValidCost(String)}
 */
public class Cost {

    public static final String MESSAGE_CONSTRAINTS = "COST should either be a positive number or 0. "
            + "It can also have up to 2 decimal places";
    public static final String VALIDATION_REGEX = "^\\d+(\\.\\d{1,2})?$";

    private final Float value;

    /**
     * Constructs a {@code Cost}.
     *
     * @param cost A valid cost for the prescription.
     */
    public Cost(String cost) {
        requireNonNull(cost);
        checkArgument(isValidCost(cost), MESSAGE_CONSTRAINTS);
        value = Float.parseFloat(cost);
    }

    /**
     * Returns true if a given string is a valid cost
     */
    public static boolean isValidCost(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public float getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("%.2f", value);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Cost // instanceof handles nulls
                && value.equals(((Cost) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
