package seedu.wife.logic.commands.deletecommands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import seedu.wife.commons.core.Messages;
import seedu.wife.logic.commands.Command;
import seedu.wife.logic.commands.CommandResult;
import seedu.wife.logic.commands.exceptions.CommandException;
import seedu.wife.model.Model;
import seedu.wife.model.food.Food;
import seedu.wife.model.tag.Tag;

/**
 * Deletes a food identified using it's displayed index from WIFE.
 */
public class DeleteByTagCommand extends Command {

    public static final String COMMAND_WORD = "delbytag";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the food identified by the index number used in the displayed food list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " vegetable";

    public static final String MESSAGE_DELETE_FOOD_SUCCESS = "Deleted Food:";

    private final Tag targetTag;

    public DeleteByTagCommand(Tag targetTag) {
        this.targetTag = targetTag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Food> lastShownList = List.copyOf(model.getFilteredFoodList());
        String deletedFoodSuccessMessage = MESSAGE_DELETE_FOOD_SUCCESS;
        boolean throwError = true;

        for (Food foodToDelete : lastShownList) {
            Set<Tag> foodTags = foodToDelete.getTags();

            if (foodTags.contains(targetTag)) {
                throwError = false;
                deletedFoodSuccessMessage = deletedFoodSuccessMessage + "\n" + foodToDelete;
                model.deleteFood(foodToDelete);
            }
        }

        if (throwError) {
            throw new CommandException(String.format(Messages.MESSAGE_TAG_NOT_FOUND, this.targetTag.tagName));
        }

        return new CommandResult(deletedFoodSuccessMessage);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteByTagCommand // instanceof handles nulls
                && targetTag.equals(((DeleteByTagCommand) other).targetTag)); // state check
    }
}
