package seedu.recipe.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.recipe.commons.core.Messages;
import seedu.recipe.model.Model;
import seedu.recipe.model.recipe.RecipeIngredientsSubsetPredicate;

/**
 * Onlys and lists all recipes in recipe book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class OnlyCommand extends Command {

    public static final String COMMAND_WORD = "only";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Only selects the recipes that "
            + "can be made with just those ingredients listed "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: " + PREFIX_TITLE + "INGREDIENT [MORE_INGREDIENTS]...\n";


    private final RecipeIngredientsSubsetPredicate predicate;

    public OnlyCommand(RecipeIngredientsSubsetPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredRecipeList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_RECIPES_LISTED_OVERVIEW, model.getFilteredRecipeList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof OnlyCommand // instanceof handles nulls
                && predicate.equals(((OnlyCommand) other).predicate)); // state check
    }
}
