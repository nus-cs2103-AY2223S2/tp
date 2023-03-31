package trackr.model.menu;

/**
 * Represents a Item's cost in the Item list.
 * Guarantees: immutable; is valid as declared in {@link #isValidCost(String)}
 */
public class ItemCost extends ItemPrice {

    /**
     * Constructs an Cost Object
     */
    public ItemCost(String value) {
        super(value);
    }

    @Override
    public String toString() {
        return "Cost: $" + this.getFormattedValue();
    }
}
