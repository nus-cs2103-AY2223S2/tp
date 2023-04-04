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
    private static final String VALIDATION_REGEX = "^\\d+(.\\d{0,2})?$";
    private static final DecimalFormat DF = new DecimalFormat("0.00");
    private final Double value;
    private final String formattedValue;

    /**
     * Constructs a {@code ItemPrice} object with the given {@code value}
     */
    public ItemPrice(String value) {
        requireNonNull(value);
        checkArgument(isValidPrice(value), MESSAGE_CONSTRAINTS);
        this.value = Double.parseDouble(value);
        this.formattedValue = DF.format(this.value);
    }

    /**
     * Constructs a {@code ItemProfit} with {@code itemProfit} of any numerical format.
     */
    public ItemPrice(Double itemPrice) {
        requireNonNull(itemPrice);
        this.value = itemPrice;
        this.formattedValue = DF.format(this.value);
    }

    /**
     * Returns true if a given string is a valid price.
     */
    public static boolean isValidPrice(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public Double getValue() {
        return value;
    }

    public String getFormattedValue() {
        return formattedValue;
    }

    public String toJsonString() {
        return formattedValue;
    }

    @Override
    public String toString() {
        return "Price: $" + formattedValue;
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
