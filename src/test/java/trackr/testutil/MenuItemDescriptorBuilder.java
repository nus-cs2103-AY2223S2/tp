package trackr.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import trackr.model.commons.Tag;
import trackr.model.menu.ItemCost;
import trackr.model.menu.ItemName;
import trackr.model.menu.ItemProfit;
import trackr.model.menu.ItemSellingPrice;
import trackr.model.menu.MenuItem;
import trackr.model.menu.MenuItemDescriptor;

/**
 * A utility class to help with building EditMenuItemDescriptor objects.
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
     * Returns an {@code EditMenuItemDescriptor} with fields containing {@code MenuItem}'s details
     */
    public MenuItemDescriptorBuilder(MenuItem menuItem) {
        descriptor = new MenuItemDescriptor();
        descriptor.setItemName(menuItem.getItemName());
        descriptor.setItemPrice(menuItem.getItemPrice());
        descriptor.setItemCost(menuItem.getItemCost());
    }

    /**
     * Sets the {@code Name} of the {@code EditMenuItemDescriptor} that we are building.
     */
    public MenuItemDescriptorBuilder withName(String name) {
        descriptor.setItemName(new ItemName(name));
        return this;
    }

    /**
     * Sets the {@code Price} of the {@code EditMenuItemDescriptor} that we are building.
     */
    public MenuItemDescriptorBuilder withPrice(String price) {
        descriptor.setItemPrice(new ItemSellingPrice(price));
        return this;
    }

    /**
     * Sets the {@code Cost} of the {@code EditMenuItemDescriptor} that we are building.
     */
    public MenuItemDescriptorBuilder withCost(String cost) {
        descriptor.setItemCost(new ItemCost(cost));
        return this;
    }

    public MenuItemDescriptor build() {
        return descriptor;
    }
}
