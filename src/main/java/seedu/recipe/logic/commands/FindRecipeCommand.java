package seedu.recipe.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.recipe.commons.core.Messages;
import seedu.recipe.model.Model;
import seedu.recipe.model.recipe.RecipeContainsKeywordsPredicate;

/**
 * Finds and lists all recipes in recipe book whose title, ingredients,
 * tags or price contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindRecipeCommand extends Command {

    public static final String COMMAND_WORD = "findr";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all recipes whose title/ingredients/tags/price contain any of "
            + "the specified keywords (case-insensitive) and "
            + "displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n";


    private final RecipeContainsKeywordsPredicate predicate;

    public FindRecipeCommand(RecipeContainsKeywordsPredicate predicate) {
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
                || (other instanceof FindRecipeCommand // instanceof handles nulls
                && predicate.equals(((FindRecipeCommand) other).predicate)); // state check
    }
}
