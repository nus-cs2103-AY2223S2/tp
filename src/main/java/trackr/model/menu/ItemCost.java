package trackr.model.menu;

import static java.util.Objects.requireNonNull;
import static trackr.commons.util.AppUtil.checkArgument;

/**
 * Represents a Item's cost in the Item list.
 * Guarantees: immutable; is valid as declared in {@link #isValidCost(String)}
 */
public class ItemCost {
    public static final String MESSAGE_CONSTRAINTS =
            "Cost should only contain numbers, and it should be at most 3 digits long";
    public static final String VALIDATION_REGEX = "\\d{1,3}";
    public final float value;

    /**
     * Constructs an Cost Object
     */
    @SuppressWarnings("checkstyle:CommentsIndentation")
    public ItemCost(String value) {
        requireNonNull(value);
        checkArgument(isValidCost(value), MESSAGE_CONSTRAINTS);
        this.value = Float.parseFloat(value);
    }

    /**
     * Returns true if a given string is a valid Cost.
     */
    public static boolean isValidCost(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return "$" + value;
    }

    public String toJsonString() {
        return Float.toString(value);
    }

    public float getValue() {
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
        return Float.hashCode(value);
    }

}
