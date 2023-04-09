package trackr.model.menu;

/**
 * Represents a Item's price in the Item list.
 * Guarantees: immutable; is valid as declared in {@link #isValidPrice(String)}
 */
public class ItemSellingPrice extends ItemPrice {

    /**
     * Constructs a {@code ItemSellingPrice}.
     */
    public ItemSellingPrice(String value) {
        super(value);
    }

    public ItemSellingPrice(Double value) {
        super(value);
    }
    @Override
    public String toString() {
        return "Selling Price: $" + this.getFormattedValue();
    }
}
