package seedu.loyaltylift.testutil;

import seedu.loyaltylift.logic.commands.EditOrderCommand;
import seedu.loyaltylift.logic.commands.EditOrderCommand.EditOrderDescriptor;
import seedu.loyaltylift.model.attribute.Address;
import seedu.loyaltylift.model.attribute.Name;
import seedu.loyaltylift.model.order.Order;
import seedu.loyaltylift.model.order.Quantity;

/**
 * A utility class to help with building EditOrderDescriptor objects.
 */
public class EditOrderDescriptorBuilder {

    private EditOrderCommand.EditOrderDescriptor descriptor;

    public EditOrderDescriptorBuilder() {
        descriptor = new EditOrderCommand.EditOrderDescriptor();
    }

    public EditOrderDescriptorBuilder(EditOrderCommand.EditOrderDescriptor descriptor) {
        this.descriptor = new EditOrderDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditOrderDescriptor} with fields containing {@code order}'s details
     */
    public EditOrderDescriptorBuilder(Order order) {
        descriptor = new EditOrderDescriptor();
        descriptor.setName(order.getName());
        descriptor.setAddress(order.getAddress());
        descriptor.setQuantity(order.getQuantity());
    }

    /**
     * Sets the {@code Name} of the {@code EditOrderDescriptor} that we are building.
     */
    public EditOrderDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditOrderDescriptor} that we are building.
     */
    public EditOrderDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code Quantity} of the {@code EditOrderDescriptor} that we are building.
     */
    public EditOrderDescriptorBuilder withQuantity(String quantity) {
        descriptor.setQuantity(new Quantity(Integer.parseInt(quantity)));
        return this;
    }

    public EditOrderCommand.EditOrderDescriptor build() {
        return descriptor;
    }
}
