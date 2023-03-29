package seedu.wife.testutil;

import seedu.wife.logic.commands.foodcommands.IncreaseCommand.IncreaseFoodDescriptor;
import seedu.wife.model.food.Food;
import seedu.wife.model.food.Quantity;

/**
 * A utility class to help with building IncreaseFoodDescriptor objects.
 */
public class IncreaseFoodDescriptorBuilder {

    private IncreaseFoodDescriptor descriptor;

    public IncreaseFoodDescriptorBuilder() {
        descriptor = new IncreaseFoodDescriptor();
    }

    public IncreaseFoodDescriptorBuilder(IncreaseFoodDescriptor descriptor) {
        this.descriptor = new IncreaseFoodDescriptor(descriptor);
    }

    /**
     * Returns an {@code IncreaseFoodDescriptor} with fields containing {@code food}'s details
     */
    public IncreaseFoodDescriptorBuilder(Food food) {
        descriptor = new IncreaseFoodDescriptor();
        descriptor.setQuantity(food.getQuantity());
    }

    /**
     * Sets the {@code Quantity} of the {@code IncreaseFoodDescriptor} that we are building.
     */
    public IncreaseFoodDescriptorBuilder withQuantity(String quantity) {
        descriptor.setQuantity(new Quantity(quantity));
        return this;
    }

    public IncreaseFoodDescriptor build() {
        return descriptor;
    }
}
