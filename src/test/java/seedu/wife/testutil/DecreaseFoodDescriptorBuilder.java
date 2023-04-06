package seedu.wife.testutil;

import seedu.wife.logic.commands.foodcommands.DecreaseCommand.DecreaseFoodDescriptor;
import seedu.wife.model.food.Food;
import seedu.wife.model.food.Quantity;

/**
 * A utility class to help with building DecreaseFoodDescriptor objects.
 */
public class DecreaseFoodDescriptorBuilder {

    private DecreaseFoodDescriptor descriptor;

    public DecreaseFoodDescriptorBuilder() {
        descriptor = new DecreaseFoodDescriptor();
    }

    public DecreaseFoodDescriptorBuilder(DecreaseFoodDescriptor descriptor) {
        this.descriptor = new DecreaseFoodDescriptor(descriptor);
    }

    /**
     * Returns an {@code DecreaseFoodDescriptor} with fields containing {@code food}'s details
     */
    public DecreaseFoodDescriptorBuilder(Food food) {
        descriptor = new DecreaseFoodDescriptor();
        descriptor.setQuantity(food.getQuantity());
    }

    /**
     * Sets the {@code Quantity} of the {@code DecreaseFoodDescriptor} that we are building.
     */
    public DecreaseFoodDescriptorBuilder withQuantity(String quantity) {
        descriptor.setQuantity(new Quantity(quantity));
        return this;
    }

    public DecreaseFoodDescriptor build() {
        return descriptor;
    }
}
