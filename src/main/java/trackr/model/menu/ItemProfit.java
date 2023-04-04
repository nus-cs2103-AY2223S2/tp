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
    private static final String VALIDATION_REGEX = "^-?\\d+(.\\d{0,2})?$";
    private static final DecimalFormat DF = new DecimalFormat("0.00");
    private final Double value;
    private final String formattedValue;


    /**
     * Constructs an ItemProfit Object
     */
    public ItemProfit(ItemSellingPrice itemSellingPrice, ItemCost itemSellingCost) {
        requireAllNonNull(itemSellingPrice, itemSellingCost);
        this.value = itemSellingPrice.getValue() - itemSellingCost.getValue();
        this.formattedValue = DF.format(this.value);
        checkArgument(isValidProfit(formattedValue), MESSAGE_CONSTRAINTS);
    }

    /**
     * Constructs a {@code ItemProfit} with {@code itemProfit} of any numerical format.
     */
    public ItemProfit(Double itemProfit) {
        requireNonNull(itemProfit);
        this.value = itemProfit;
        this.formattedValue = DF.format(this.value);
    }

    /**
     * Returns true if a given string is a valid profit.
     */
    public static boolean isValidProfit(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public Double getValue() {
        return value;
    }

    public String toJsonString() {
        return formattedValue;
    }

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
