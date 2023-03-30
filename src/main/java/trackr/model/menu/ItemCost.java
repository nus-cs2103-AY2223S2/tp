package trackr.model.menu;

import static java.util.Objects.requireNonNull;
import static trackr.commons.util.AppUtil.checkArgument;

import java.text.DecimalFormat;

/**
 * Represents a Item's cost in the Item list.
 * Guarantees: immutable; is valid as declared in {@link #isValidCost(String)}
 */
public class ItemCost {
    public static final String MESSAGE_CONSTRAINTS =
            "Cost should only contain positive numbers, and it should be at most 2 decimal place";
    public static final String VALIDATION_REGEX = "^\\d+(.\\d{0,2})?$";
    public static final DecimalFormat DF = new DecimalFormat("0.00");
    public final Double value;

    /**
     * Constructs an Cost Object
     */
    @SuppressWarnings("checkstyle:CommentsIndentation")
    public ItemCost(String value) {
        requireNonNull(value);
        checkArgument(isValidCost(value), MESSAGE_CONSTRAINTS);
        this.value = Double.parseDouble(value);
    }

    /**
     * Returns true if a given string is a valid Cost.
     */
    public static boolean isValidCost(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return "Cost Price: $" + DF.format(this.value);
    }

    public String toJsonString() {
        return Double.toString(value);
    }

    public Double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ItemCost // instanceof handles nulls
                && value == (((ItemCost) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return Double.hashCode(value);
    }

}
