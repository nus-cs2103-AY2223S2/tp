package trackr.model.menu;

import static java.util.Objects.requireNonNull;
import static trackr.commons.util.AppUtil.checkArgument;

import java.text.DecimalFormat;
//import static trackr.commons.util.AppUtil.checkArgument;

/**
 * Represents an Item's profit in the Item list.
 * Guarantees: immutable; is valid as declared in {@link #isValidProfit(String)}
 */
public class ItemProfit {
    public static final String MESSAGE_CONSTRAINTS =
            "Profit should only contain numbers, and it should be at most 2 decimal place";
    public static final String VALIDATION_REGEX = "^-?\\d+(.\\d{0,2})?$";
    public static final DecimalFormat DF = new DecimalFormat("0.00");
    public final String value;

    /**
     * Constructs an ItemProfit Object
     */
    public ItemProfit(ItemPrice itemPrice, ItemCost itemCost) {
        requireNonNull(itemPrice);
        requireNonNull(itemCost);
        this.value = DF.format(itemPrice.getValue() - itemCost.getValue());
        System.out.println(value);
        checkArgument(isValidProfit(value), MESSAGE_CONSTRAINTS);
    }

    /**
     * Returns true if a given string is a valid Profit.
     */
    public static boolean isValidProfit(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return "Profit: $" + value;
    }

    public String toJsonString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ItemProfit // instanceof handles nulls
                && value.equals(((ItemProfit) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
