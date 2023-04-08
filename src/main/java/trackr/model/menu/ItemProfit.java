package trackr.model.menu;

import static java.util.Objects.requireNonNull;
import static trackr.commons.util.AppUtil.checkArgument;
import static trackr.commons.util.CollectionUtil.requireAllNonNull;

import java.text.DecimalFormat;

/**
 * Represents an Item's profit in the Item list.
 * Guarantees: immutable; is valid as declared in {@link #isValidProfit(String)}
 */
public class ItemProfit {
    public static final String MESSAGE_CONSTRAINTS =
            "Profit should only contain numbers, and it should be at most 2 decimal place";
    private static final String VALIDATION_REGEX = "^-?[0-9]+(\\.[0-9]{1,2})?$";
    private static final DecimalFormat DF = new DecimalFormat("0.00");
    private final Double value;
    private final String formattedValue;


    /**
     * Constructs an ItemProfit object based on selling price and selling cost of an item.
     * @param itemSellingPrice The selling price of the item.
     * @param itemSellingCost The selling cost of the item.
     * @throws NullPointerException if any of the arguments are null.
     * @throws IllegalArgumentException if the calculated profit is invalid.
     */
    public ItemProfit(ItemSellingPrice itemSellingPrice, ItemCost itemSellingCost) {
        requireAllNonNull(itemSellingPrice, itemSellingCost);
        this.value = itemSellingPrice.getValue() - itemSellingCost.getValue();
        this.formattedValue = DF.format(this.value);
        checkArgument(isValidProfit(formattedValue), MESSAGE_CONSTRAINTS);
    }

    /**
     * Constructs an ItemProfit object with the given profit value.
     * @param itemProfit The profit value of the item of any numerical format
     * @throws NullPointerException if the argument is null.
     */
    public ItemProfit(Double itemProfit) {
        requireNonNull(itemProfit);
        this.value = itemProfit;
        this.formattedValue = DF.format(this.value);
    }

    /**
     * Returns true if a given string matches the validation regex for profit.
     * @param test the string to test for validity as a profit value
     * @return true if test is a valid profit value, false otherwise
     */
    public static boolean isValidProfit(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the profit value of the item.
     * @return The profit value.
     */
    public Double getValue() {
        return value;
    }

    /**
     * Returns the formatted profit value of the item as a JSON string for storage purposes.
     * @return The formatted profit value as a JSON string.
     */
    public String toJsonString() {
        return formattedValue;
    }

    /**
     * Returns a string representation of the ItemProfit object.
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return "Profit: $" + formattedValue;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ItemProfit // instanceof handles nulls
                && value.equals(((ItemProfit) other).getValue())); // state check
    }

    @Override
    public int hashCode() {
        return Double.hashCode(value);
    }
}
