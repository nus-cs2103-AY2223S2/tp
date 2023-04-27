package seedu.wife.logic.commands.foodcommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.wife.commons.core.Messages;
import seedu.wife.commons.core.index.Index;
import seedu.wife.logic.commands.Command;
import seedu.wife.logic.commands.CommandResult;
import seedu.wife.logic.commands.exceptions.CommandException;
import seedu.wife.model.Model;
import seedu.wife.model.food.Food;

/**
 * Views the food identified by the index number used in the displayed food list.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Views the food identified by the index number used in the displayed food list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEW_FOOD_SUCCESS = "Viewed Food: %1$s";

    private final Index targetIndex;

    /**
     * Creates a ViewCommand to view the specified {@code targetIndex}
     */
    public ViewCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Food> lastShownList = model.getFilteredFoodList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_FOOD_DISPLAYED_INDEX);
        }

        Food foodToView = lastShownList.get(targetIndex.getZeroBased());
        model.viewFood(foodToView);
        return new CommandResult(String.format(MESSAGE_VIEW_FOOD_SUCCESS, foodToView));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommand // instanceof handles nulls
                && targetIndex.equals(((ViewCommand) other).targetIndex)); // state check
    }
}
