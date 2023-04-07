package seedu.wife.logic.commands.foodcommands;

import static java.util.Objects.requireNonNull;
import static seedu.wife.model.Model.PREDICATE_SHOW_ALL_FOODS;

import java.util.List;

import seedu.wife.commons.core.Messages;
import seedu.wife.commons.core.index.Index;
import seedu.wife.logic.commands.Command;
import seedu.wife.logic.commands.CommandResult;
import seedu.wife.logic.commands.exceptions.CommandException;
import seedu.wife.model.Model;
import seedu.wife.model.food.Food;
import seedu.wife.model.food.Quantity;


/**
 * Decreases the quantity of a food item by a specified amount or 1 by default.
 */
public class DecreaseCommand extends Command {
    public static final String COMMAND_WORD = "dec";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Decreases the quantity of the food item"
            + " at the given index. "
            + "If no specific quantity is mentioned, it decreases by 1 by default\n"
            + "Parameters: INDEX [q/quantity to decrease by]...\n"
            + "Example: " + COMMAND_WORD + " 1 q/10";
    public static final String MESSAGE_DECREASE_FOOD_SUCCESS = "Decreased Food: %1$s";

    public final Index index;
    public final DecreaseFoodDescriptor decreaseFoodDescriptor;

    /**
     * @param index of the food item in the filtered food list to edit
     * @param decreaseFoodDescriptor quantity descriptor to decrease the food item with
     */
    public DecreaseCommand(Index index, DecreaseFoodDescriptor decreaseFoodDescriptor) {
        requireNonNull(index);
        requireNonNull(decreaseFoodDescriptor);
        this.index = index;
        this.decreaseFoodDescriptor = new DecreaseFoodDescriptor(decreaseFoodDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Food> lastShownList = model.getFilteredFoodList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FOOD_DISPLAYED_INDEX);
        }

        Food foodToDecrease = lastShownList.get(index.getZeroBased());
        Food decreasedFood = createDecreasedFood(foodToDecrease, decreaseFoodDescriptor);

        model.setFood(foodToDecrease, decreasedFood);
        model.updateFilteredFoodList(PREDICATE_SHOW_ALL_FOODS);
        String feedbackToUser = String.format(MESSAGE_DECREASE_FOOD_SUCCESS, decreasedFood)
                + " by " + decreaseFoodDescriptor.getQuantity();
        return new CommandResult(feedbackToUser);

    }

    /**
     * Creates and returns a {@code Food} with the details of {@code foodToEdit}
     * edited with {@code editFoodDescriptor}.
     */
    private static Food createDecreasedFood(Food foodToDecrease, DecreaseFoodDescriptor decreaseFoodDescriptor)
            throws CommandException {
        assert foodToDecrease != null;
        Quantity updatedQuantity = foodToDecrease.getQuantity()
                .decreaseQuantity(decreaseFoodDescriptor.getQuantity());
        return new Food(foodToDecrease.getName(), foodToDecrease.getUnit(), updatedQuantity,
                foodToDecrease.getExpiryDate(), foodToDecrease.getTags());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DecreaseCommand)) {
            return false;
        }

        // state check
        DecreaseCommand e = (DecreaseCommand) other;
        return index.equals(e.index)
                && decreaseFoodDescriptor.equals(e.decreaseFoodDescriptor);
    }

    /**
     * Stores the quantity to decrease the quantity of the food item with. A non-negative value will decrease the
     * corresponding quantity of the food item by that value.
     */
    public static class DecreaseFoodDescriptor {

        private Quantity quantity;

        public DecreaseFoodDescriptor() {
            this.quantity = new Quantity("1");
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public DecreaseFoodDescriptor(DecreaseCommand.DecreaseFoodDescriptor toCopy) {
            setQuantity(toCopy.quantity);
        }

        public void setQuantity(Quantity quantity) {
            this.quantity = quantity;
        }

        public Quantity getQuantity() {
            return this.quantity;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof DecreaseCommand.DecreaseFoodDescriptor)) {
                return false;
            }

            // state check
            DecreaseCommand.DecreaseFoodDescriptor e = (DecreaseCommand.DecreaseFoodDescriptor) other;

            return getQuantity().equals(e.getQuantity());
        }
    }
}
