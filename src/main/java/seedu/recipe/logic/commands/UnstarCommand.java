package seedu.recipe.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.recipe.commons.core.Messages;
import seedu.recipe.commons.core.index.Index;
import seedu.recipe.logic.commands.exceptions.CommandException;
import seedu.recipe.model.Model;
import seedu.recipe.model.recipe.Recipe;

/**
 * Marks a recipe as in favorites identified using it's displayed index from the recipe book.
 */
public class UnstarCommand extends Command {

    public static final String COMMAND_WORD = "unstar";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the recipe as out of favorites"
            + "identified by the index number used in the displayed recipe list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_STAR_RECIPE_SUCCESS = "Unstarred Recipe: %1$s";

    private final Index targetIndex;

    public UnstarCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Recipe> lastShownList = model.getFilteredRecipeList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
        }

        Recipe recipeToUnstar = lastShownList.get(targetIndex.getZeroBased());
        if (!recipeToUnstar.isStarred()) {
            throw new CommandException(Messages.MESSAGE_RECIPE_NOT_STARRED);
        }
        model.unstarRecipe(recipeToUnstar);

        return new CommandResult(String.format(MESSAGE_STAR_RECIPE_SUCCESS, recipeToUnstar.getTitle()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnstarCommand // instanceof handles nulls
                && targetIndex.equals(((UnstarCommand) other).targetIndex)); // state check
    }
}
