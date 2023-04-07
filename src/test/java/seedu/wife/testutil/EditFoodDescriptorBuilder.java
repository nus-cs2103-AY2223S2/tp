package seedu.wife.testutil;

import seedu.wife.logic.commands.foodcommands.EditCommand.EditFoodDescriptor;
import seedu.wife.model.food.ExpiryDate;
import seedu.wife.model.food.Food;
import seedu.wife.model.food.Name;
import seedu.wife.model.food.Quantity;
import seedu.wife.model.food.Unit;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditFoodDescriptorBuilder {

    private EditFoodDescriptor descriptor;

    public EditFoodDescriptorBuilder() {
        descriptor = new EditFoodDescriptor();
    }

    public EditFoodDescriptorBuilder(EditFoodDescriptor descriptor) {
        this.descriptor = new EditFoodDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditFoodDescriptor} with fields containing {@code food}'s details
     */
    public EditFoodDescriptorBuilder(Food food) {
        descriptor = new EditFoodDescriptor();
        descriptor.setName(food.getName());
        descriptor.setUnit(food.getUnit());
        descriptor.setQuantity(food.getQuantity());
        descriptor.setExpiryDate(food.getExpiryDate());
    }

    /**
     * Sets the {@code Name} of the {@code EditFoodDescriptor} that we are building.
     */
    public EditFoodDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Unit} of the {@code EditFoodDescriptor} that we are building.
     */
    public EditFoodDescriptorBuilder withUnit(String unit) {
        descriptor.setUnit(new Unit(unit));
        return this;
    }

    /**
     * Sets the {@code Quantity} of the {@code EditFoodDescriptor} that we are building.
     */
    public EditFoodDescriptorBuilder withQuantity(String quantity) {
        descriptor.setQuantity(new Quantity(quantity));
        return this;
    }

    /**
     * Sets the {@code Expiry Date} of the {@code EditFoodDescriptor} that we are building.
     */
    public EditFoodDescriptorBuilder withExpiryDate(String date) {
        descriptor.setExpiryDate(new ExpiryDate(date));
        return this;
    }

    public EditFoodDescriptor build() {
        return descriptor;
    }
}
