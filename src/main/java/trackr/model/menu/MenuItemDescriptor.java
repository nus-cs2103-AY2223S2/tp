package trackr.model.menu;

import java.util.Optional;

import trackr.commons.util.CollectionUtil;
import trackr.model.item.ItemDescriptor;

/**
 * Stores the details of a task.
 * Each non-empty field value will replace the corresponding field value of the task.
 */
public class MenuItemDescriptor implements ItemDescriptor<MenuItem> {
    private ItemName itemName;
    private ItemSellingPrice itemPrice;
    private ItemCost itemCost;
    private ItemProfit itemProfit;

    public MenuItemDescriptor() {}

    /**
     * Copy constructor.
     */
    public MenuItemDescriptor(MenuItemDescriptor toCopy) {
        setItemName(toCopy.itemName);
        setItemPrice(toCopy.itemPrice);
        setItemCost(toCopy.itemCost);
        setItemProfit(toCopy.itemProfit);
    }

    /**
     * Returns true if at least one field is edited.
     */
    public boolean isAnyFieldNonNull() {
        return CollectionUtil.isAnyNonNull(itemName, itemPrice, itemCost, itemProfit);
    }

    public void setItemName(ItemName itemName) {
        this.itemName = itemName;
    }

    public Optional<ItemName> getItemName() {
        return Optional.ofNullable(itemName);
    }

    public void setItemPrice(ItemSellingPrice itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Optional<ItemSellingPrice> getItemPrice() {
        return Optional.ofNullable(itemPrice);
    }

    public void setItemCost(ItemCost itemCost) {
        this.itemCost = itemCost;
    }

    public Optional<ItemCost> getItemCost() {
        return Optional.ofNullable(itemCost);
    }

    public void setItemProfit(ItemProfit itemProfit) {
        this.itemProfit = itemProfit;
    }

    public Optional<ItemProfit> getItemProfit() {
        return Optional.ofNullable(itemProfit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MenuItemDescriptor)) {
            return false;
        }

        // state check
        MenuItemDescriptor menuItemDescriptor = (MenuItemDescriptor) other;

        return getItemName().equals(menuItemDescriptor.getItemName())
                && getItemPrice().equals(menuItemDescriptor.getItemPrice())
                && getItemCost().equals(menuItemDescriptor.getItemCost());
    }
}
