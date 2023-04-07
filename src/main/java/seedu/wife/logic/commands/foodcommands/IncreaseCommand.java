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
 * Increases the quantity of a food item by a specified amount or 1 by default.
 */
public class IncreaseCommand extends Command {
    public static final String COMMAND_WORD = "inc";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Increases the quantity of the food item"
            + " at the given index. "
            + "If no specific quantity is mentioned, it increases by 1 by default\n"
            + "Parameters: INDEX [q/quantity to increase by]...\n"
            + "Example: " + COMMAND_WORD + " 1 q/100";
    public static final String MESSAGE_INCREASE_FOOD_SUCCESS = "Increased Food: %1$s";

    public final Index index;
    public final IncreaseFoodDescriptor increaseFoodDescriptor;

    /**
     * @param index of the food item in the filtered food list to edit
     * @param increaseFoodDescriptor quantity descriptor to increase the food item with
     */
    public IncreaseCommand(Index index, IncreaseFoodDescriptor increaseFoodDescriptor) {
        requireNonNull(index);
        requireNonNull(increaseFoodDescriptor);
        this.index = index;
        this.increaseFoodDescriptor = new IncreaseFoodDescriptor(increaseFoodDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Food> lastShownList = model.getFilteredFoodList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FOOD_DISPLAYED_INDEX);
        }

        Food foodToIncrease = lastShownList.get(index.getZeroBased());
        Food increasedFood = createIncreasedFood(foodToIncrease, increaseFoodDescriptor);

        model.setFood(foodToIncrease, increasedFood);
        model.updateFilteredFoodList(PREDICATE_SHOW_ALL_FOODS);
        String feedbackToUser = String.format(MESSAGE_INCREASE_FOOD_SUCCESS, increasedFood)
                + " by " + increaseFoodDescriptor.getQuantity();
        return new CommandResult(feedbackToUser);

    }

    /**
     * Creates and returns a {@code Food} with the details of {@code foodToEdit}
     * edited with {@code editFoodDescriptor}.
     */
    private static Food createIncreasedFood(Food foodToIncrease, IncreaseFoodDescriptor increaseFoodDescriptor) {
        assert foodToIncrease != null;
        Quantity updatedQuantity = foodToIncrease.getQuantity().increaseQuantity(increaseFoodDescriptor.getQuantity());
        return new Food(foodToIncrease.getName(), foodToIncrease.getUnit(), updatedQuantity,
                foodToIncrease.getExpiryDate(), foodToIncrease.getTags());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof IncreaseCommand)) {
            return false;
        }

        // state check
        IncreaseCommand e = (IncreaseCommand) other;
        return index.equals(e.index)
                && increaseFoodDescriptor.equals(e.increaseFoodDescriptor);
    }

    /**
     * Stores the quantity to increase the quantity of the food item with. A non-negative value will increase the
     * corresponding quantity of the food item by that value.
     */
    public static class IncreaseFoodDescriptor {

        private Quantity quantity;

        public IncreaseFoodDescriptor() {
            this.quantity = new Quantity("1");
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public IncreaseFoodDescriptor(IncreaseFoodDescriptor toCopy) {
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
            if (!(other instanceof IncreaseCommand.IncreaseFoodDescriptor)) {
                return false;
            }

            // state check
            IncreaseCommand.IncreaseFoodDescriptor e = (IncreaseCommand.IncreaseFoodDescriptor) other;

            return getQuantity().equals(e.getQuantity());
        }
    }
}
