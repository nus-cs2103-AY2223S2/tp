package seedu.recipe.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import seedu.recipe.commons.core.Messages;
import seedu.recipe.commons.core.index.Index;
import seedu.recipe.logic.commands.exceptions.CommandException;
import seedu.recipe.model.Model;
import seedu.recipe.model.recipe.Ingredient;
import seedu.recipe.model.recipe.Recipe;

/**
 * Given a list of recipe indexes, displays all the ingredients used within these recipes.
 */
public class GroceriesCommand extends Command {

    public static final String COMMAND_WORD = "groceries";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the groceries you need to cook the recipes at the specified recipe number.\n"
            + "Format: " + COMMAND_WORD + " RECIPE_NUMBER, ...\n"
            + "RECIPE_NUMBER must be a positive integer starting from 1 and must exist in the recipe book.\n"
            + "Two or more RECIPE_NUMBER can be provided, but must be separated by a comma `,`.\n"
            + "Example: " + COMMAND_WORD + " 1, 2";

    private final List<Index> indices;

    public GroceriesCommand(List<Index> indices) {
        this.indices = indices;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Recipe> lastShownList = model.getFilteredRecipeList();

        Map<String, Ingredient> groceries = new HashMap<>();

        for (Index idx : indices) {
            if (idx.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX + ": "
                                           + idx.getOneBased());
            }

            Recipe recipe = lastShownList.get(idx.getZeroBased());
            Set<Ingredient> ingredients = recipe.getIngredients();
            for (Ingredient ig : ingredients) {
                if (groceries.containsKey(ig.name)) {
                    Ingredient existingIngredient = groceries.get(ig.name);
                    Ingredient updatedIngredient = new Ingredient(existingIngredient.name,
                                                                  existingIngredient.quantity + ig.quantity,
                                                                  existingIngredient.unitOfMeasurement,
                                                                  existingIngredient.pricePerUnit);
                    groceries.put(ig.name, updatedIngredient);
                } else {
                    groceries.put(ig.name, ig);
                }
            }
        }

        StringBuilder groceriesString = new StringBuilder();
        for (Ingredient ig : groceries.values()) {
            groceriesString.append(ig.name + ", " + ig.quantity + " " + ig.unitOfMeasurement);
            groceriesString.append("\n");
        }

        return new CommandResult(groceriesString.toString());
    }
}
