package seedu.recipe.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.recipe.commons.core.Messages;
import seedu.recipe.model.Model;
import seedu.recipe.model.recipe.IsStarredPredicate;

/**
 * Finds and lists all starred recipes in recipe book.
 */
public class FavoritesCommand extends Command {

    public static final String COMMAND_WORD = "favorites";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists out all starred recipes.\n"
            + "Format: " + COMMAND_WORD;

    private final IsStarredPredicate predicate = new IsStarredPredicate();

    public FavoritesCommand() {
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredRecipeList(predicate);
        boolean isSizeOne = model.getFilteredRecipeList().size() == 1;
        String returnedCommand;
        if (isSizeOne) {
            returnedCommand = String.format(Messages.MESSAGE_ONE_RECIPE_LISTED_OVERVIEW,
                    model.getFilteredRecipeList().size());
        } else {
            returnedCommand = String.format(Messages.MESSAGE_RECIPES_LISTED_OVERVIEW,
                    model.getFilteredRecipeList().size());

        }
        return new CommandResult(returnedCommand);

    }
}
