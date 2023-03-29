package seedu.recipe.logic.commands;

import seedu.recipe.commons.core.Messages;
import seedu.recipe.commons.core.index.Index;
import seedu.recipe.logic.commands.exceptions.CommandException;
import seedu.recipe.model.Model;
import seedu.recipe.model.recipe.Ingredient;
import seedu.recipe.model.recipe.Recipe;

import java.util.List;
import java.util.Set;

import static java.util.Objects.requireNonNull;

/**
 * Given a list of recipe indexes, displays all the ingredients used within these recipes.
 */
public class GroceriesCommand extends Command {

    public static final String COMMAND_WORD = "groceries";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Given several recipe indices,"
            + " displays all the ingredients used within these recipes";

    private final List<Index> indices;

    public GroceriesCommand(List<Index> indices) {
        this.indices = indices;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Recipe> lastShownList = model.getFilteredRecipeList();

        StringBuilder groceries = new StringBuilder();

        for (Index idx : indices) {
            if (idx.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX + ": "
                                           + idx.getOneBased());
            }

            Recipe recipe = lastShownList.get(idx.getZeroBased());
            Set<Ingredient> ingredients = recipe.getIngredients();
            for (Ingredient ig : ingredients) {
                groceries.append(ig.toDisplayString());
                groceries.append("\n");
            }
        }
        return new CommandResult(groceries.toString());

    }
}
