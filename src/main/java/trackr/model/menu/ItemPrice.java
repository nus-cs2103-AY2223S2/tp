package trackr.model.menu;

import static java.util.Objects.requireNonNull;
import static trackr.commons.util.AppUtil.checkArgument;

import java.text.DecimalFormat;

/**
 * ItemPrice represents any currency that may be associated.
 */
public class ItemPrice {
    public static final String MESSAGE_CONSTRAINTS =
            "Price should only contain positive numbers, and it should be at most 2 decimal place";
    private static final String VALIDATION_REGEX = "^[0-9]+(\\.[0-9]{0,2})?$";
    private static final DecimalFormat DF = new DecimalFormat("0.00");
    private final Double value;
    private final String formattedValue;

    /**
     * Constructs an ItemPrice object with the given price value as a String.
     *
     * @param value The price value as a String.
     * @throws NullPointerException If value is null.
     * @throws IllegalArgumentException If value is not a valid price value.
     */
    public ItemPrice(String value) {
        requireNonNull(value);
        checkArgument(isValidPrice(value), MESSAGE_CONSTRAINTS);
        this.value = Double.parseDouble(value);
        this.formattedValue = DF.format(this.value);
    }

    /**
     * Constructs an ItemPrice object with the given price value as a Double.
     *
     * @param itemPrice The price value as a Double.
     * @throws NullPointerException If itemPrice is null.
     */
    public ItemPrice(Double itemPrice) {
        requireNonNull(itemPrice);
        this.value = itemPrice;
        this.formattedValue = DF.format(this.value);
    }

    /**
     * Returns true if a given string matches the validation regex for price.
     *
     * @param test The string to test for validity as a price value.
     * @return True if test is a valid price value, false otherwise.
     */
    public static boolean isValidPrice(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the price value as a Double.
     *
     * @return the price value as a Double
     */
    public Double getValue() {
        return value;
    }

    /**
     * Returns the formatted price value as a String.
     *
     * @return the formatted price value as a String.
     */
    public String getFormattedValue() {
        return formattedValue;
    }

    /**
     * Returns the formatted price value of the item as a JSON string for storage purposes.
     *
     * @return The formatted price value as a JSON string.
     */
    public String toJsonString() {
        return formattedValue;
    }

    @Override
    public String toString() {
        return formattedValue;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ItemPrice // instanceof handles nulls
                && value.equals(((ItemPrice) other).getValue())); // state check
    }

    @Override
    public int hashCode() {
        return Double.hashCode(value);
    }
}
