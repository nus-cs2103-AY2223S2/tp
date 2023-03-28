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

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows all starred recipes.\n"
            + "Example: " + COMMAND_WORD;

    private final IsStarredPredicate predicate = new IsStarredPredicate();

    public FavoritesCommand() {
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredRecipeList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_RECIPES_LISTED_OVERVIEW, model.getFilteredRecipeList().size()));
    }
}
