package trackr.model.menu;

import static java.util.Objects.requireNonNull;
import static trackr.commons.util.AppUtil.checkArgument;

/**
 * Represents a Item's price in the Item list.
 * Guarantees: immutable; is valid as declared in {@link #isValidPrice(String)}
 */
public class ItemPrice {
    public static final String MESSAGE_CONSTRAINTS =
            "Price should only contain numbers, and it should be at most 4 digits long";
    public static final String VALIDATION_REGEX = "\\d{1,3}";
    public final float value;

    /**
     * Constructs a Price Object
     */
    public ItemPrice(String value) {
        requireNonNull(value);
        checkArgument(isValidPrice(value), MESSAGE_CONSTRAINTS);
        this.value = Float.parseFloat(value);
    }

    /**
     * Returns true if a given string is a valid price.
     */
    public static boolean isValidPrice(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return "Selling Price: $" + value;
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
                || (other instanceof ItemPrice // instanceof handles nulls
                && value == ((ItemPrice) other).value); // state check
    }

    @Override
    public int hashCode() {
        return Float.hashCode(value);
    }
}
