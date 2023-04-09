package trackr.testutil;

import trackr.model.menu.ItemCost;
import trackr.model.menu.ItemName;
import trackr.model.menu.ItemSellingPrice;
import trackr.model.menu.MenuItem;
import trackr.model.menu.MenuItemDescriptor;

/**
 * A utility class to help with building EditSupplierDescriptor objects.
 */
public class MenuItemDescriptorBuilder {

    private MenuItemDescriptor descriptor;

    public MenuItemDescriptorBuilder() {
        descriptor = new MenuItemDescriptor();
    }

    public MenuItemDescriptorBuilder(MenuItemDescriptor descriptor) {
        this.descriptor = new MenuItemDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditSupplierDescriptor} with fields containing {@code supplier}'s details
     */
    public MenuItemDescriptorBuilder(MenuItem menuItem) {
        descriptor = new MenuItemDescriptor();
        descriptor.setItemName(menuItem.getItemName());
        descriptor.setItemPrice(menuItem.getItemPrice());
        descriptor.setItemCost(menuItem.getItemCost());
    }

    /**
     * Sets the {@code Name} of the {@code EditSupplierDescriptor} that we are building.
     */
    public MenuItemDescriptorBuilder withItemName(String name) {
        descriptor.setItemName(new ItemName(name));
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code EditSupplierDescriptor} that we are building.
     */
    public MenuItemDescriptorBuilder withItemSellingPrice(String sellingPrice) {
        descriptor.setItemPrice(new ItemSellingPrice(sellingPrice));
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code EditSupplierDescriptor} that we are building.
     */
    public MenuItemDescriptorBuilder withItemCostPrice(String costPrice) {
        descriptor.setItemCost(new ItemCost(costPrice));
        return this;
    }

    public MenuItemDescriptor build() {
        return descriptor;
    }
}
