package trackr.model.menu;

import static trackr.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import trackr.model.ModelEnum;
import trackr.model.item.Item;

/**
 * Represents a Menu in the menu list.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class MenuItem extends Item {

    // Data fields
    private final ItemName itemName;
    private final ItemSellingPrice itemPrice;
    private final ItemCost itemCost;
    private final ItemProfit itemProfit;

    /**
     * Every field must be present and not null.
     */
    public MenuItem(ItemName itemName, ItemSellingPrice itemPrice, ItemCost itemCost) {
        super(ModelEnum.Item);
        requireAllNonNull(itemName, itemPrice, itemCost);
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCost = itemCost;
        this.itemProfit = new ItemProfit(itemPrice, itemCost);
    }

    public ItemName getItemName() {
        return itemName;
    }

    public ItemSellingPrice getItemPrice() {
        return itemPrice;
    }

    public ItemCost getItemCost() {
        return itemCost;
    }

    public ItemProfit getItemProfit() {
        return itemProfit;
    }

    /**
     * Returns true if both Items have the same name.
     * This defines a weaker notion of equality between two Items.
     */
    @Override
    public boolean isSameItem(Item otherItem) {
        if (otherItem == this) {
            return true;
        }

        if (!(otherItem instanceof MenuItem)) {
            return false;
        }

        MenuItem otherMenuItem = (MenuItem) otherItem;

        return otherItem != null
                && otherMenuItem.getItemName().equals(getItemName());
    }

    /**
     * Returns true if both Items have the same name, cost and price.
     * This defines a stronger notion of equality between two Items.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof MenuItem)) {
            return false;
        }

        MenuItem otherMenuItem = (MenuItem) other;

        return otherMenuItem != null
                && otherMenuItem.getItemName().equals(getItemName())
                && otherMenuItem.getItemPrice().equals(getItemPrice())
                && otherMenuItem.getItemCost().equals(getItemCost());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(itemName, itemPrice, itemCost);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getItemName())
                .append("; ")
                .append(getItemPrice())
                .append("; ")
                .append(getItemCost())
                .append("; ")
                .append(getItemProfit());
        return builder.toString();
    }
}
