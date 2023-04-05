package trackr.model.menu;

/**
 * Represents an Item's profit in the Item list.
 * Guarantees: immutable; is valid as declared in {@link #isValidProfit(String)}
 */
public class ItemProfit extends ItemPrice {

    /**
     * Constructs an ItemProfit Object
     */
    public ItemProfit(ItemSellingPrice itemPrice, ItemCost itemCost) {
        super(itemPrice, itemCost);
    }

    public ItemProfit(Double val) {
        super(val);
    }

    @Override
    public String toString() {
        return "Profit: $" + this.getFormattedValue();
    }
}
