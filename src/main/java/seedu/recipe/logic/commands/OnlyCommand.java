package seedu.recipe.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.recipe.commons.core.Messages;
import seedu.recipe.model.Model;
import seedu.recipe.model.recipe.RecipeIngredientsSubsetPredicate;

/**
 * Onlys and lists all recipes in recipe book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class OnlyCommand extends Command {

    public static final String COMMAND_WORD = "only";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Searches for recipes that can be made with"
            + " only the specified ingredients.\n"
            + "Format: only INGREDIENT...\n"
            + "One or more ingredients can be provided.\n"
            + "Example: only eggs flour";


    private final RecipeIngredientsSubsetPredicate predicate;

    public OnlyCommand(RecipeIngredientsSubsetPredicate predicate) {
        this.predicate = predicate;
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

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OnlyCommand // instanceof handles nulls
                && predicate.equals(((OnlyCommand) other).predicate)); // state check
    }
}
