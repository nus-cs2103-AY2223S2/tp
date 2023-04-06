package seedu.loyaltylift.testutil;

import seedu.loyaltylift.logic.commands.AddOrderCommand;
import seedu.loyaltylift.logic.commands.AddOrderCommand.AddOrderDescriptor;
import seedu.loyaltylift.model.attribute.Address;
import seedu.loyaltylift.model.attribute.Name;
import seedu.loyaltylift.model.order.Order;
import seedu.loyaltylift.model.order.Quantity;

/**
 * A utility class to help with building AddOrderDescriptor objects.
 */
public class AddOrderDescriptorBuilder {

    private AddOrderCommand.AddOrderDescriptor descriptor;

    public AddOrderDescriptorBuilder() {
        descriptor = new AddOrderCommand.AddOrderDescriptor();
    }

    public AddOrderDescriptorBuilder(AddOrderCommand.AddOrderDescriptor descriptor) {
        this.descriptor = new AddOrderDescriptor(descriptor);
    }

    /**
     * Returns an {@code AddOrderDescriptor} with fields containing {@code order}'s details
     */
    public AddOrderDescriptorBuilder(Order order) {
        descriptor = new AddOrderDescriptor();
        descriptor.setName(order.getName());
        descriptor.setAddress(order.getAddress());
        descriptor.setQuantity(order.getQuantity());
    }

    /**
     * Sets the {@code Name} of the {@code AddOrderDescriptor} that we are building.
     */
    public AddOrderDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code AddOrderDescriptor} that we are building.
     */
    public AddOrderDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code Quantity} of the {@code AddOrderDescriptor} that we are building.
     */
    public AddOrderDescriptorBuilder withQuantity(String quantity) {
        descriptor.setQuantity(new Quantity(Integer.parseInt(quantity)));
        return this;
    }

    public AddOrderCommand.AddOrderDescriptor build() {
        return descriptor;
    }
}
