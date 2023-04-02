package seedu.recipe.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.recipe.commons.core.Messages;
import seedu.recipe.model.Model;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.model.recipe.SatisfyPriceConditionPredicate;

/**
 * Filters all recipes based on price condition set.
 */
public class FilterPriceCommand extends Command {

    public static final String COMMAND_WORD = "fp";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Returns a filtered list of recipes based on price condition set.\n"
            + "Parameters: {< or >} {real positive number}\n"
            + "Example: " + COMMAND_WORD + " < 2.5";

    public static final String MESSAGE_STAR_RECIPE_SUCCESS = "Starred Recipe: %1$s";

    private final SatisfyPriceConditionPredicate predicate;

    public FilterPriceCommand(SatisfyPriceConditionPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        if (model.getCurrentPredicate() != null) {
            Predicate<Recipe> newPredicate = model.getCurrentPredicate().and(predicate);
            model.updateFilteredRecipeList(newPredicate);
        } else {
            model.updateFilteredRecipeList(predicate);
        }

        return new CommandResult(
                String.format(Messages.MESSAGE_RECIPES_LISTED_OVERVIEW, model.getFilteredRecipeList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterPriceCommand // instanceof handles nulls
                && predicate.equals(((FilterPriceCommand) other).predicate)); // state check
    }
}
