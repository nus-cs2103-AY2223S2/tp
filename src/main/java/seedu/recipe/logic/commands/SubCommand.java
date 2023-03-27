package seedu.recipe.logic.commands;

import javafx.collections.ObservableList;
import seedu.recipe.model.Model;
import seedu.recipe.model.ReadOnlyRecipeBook;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.model.recipe.ingredient.Ingredient;
import seedu.recipe.model.recipe.ingredient.IngredientInformation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Searches all recipes within the recipe book for stored ingredient substitutions for given ingredient, and returns a
 * list of substitutable ingredients (without duplicates).
 * Ingredient name matching is case-insensitive.
 */
public class SubCommand extends Command {

    public static final String COMMAND_WORD = "sub";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Searches across RecipeBook for substitutions for the "
            + "specified ingredient (case insensitive) and displays them in a list.\n"
            + "Parameters: INGREDIENT\n"
            + "Example: " + COMMAND_WORD + " chicken";

    public static final String MESSAGE_NO_STORED_SUBS = "No substitutions are available. Check back later or " +
            "update the RecipeBook with some suggested substitutions!";

    public static final String MESSAGE_DISPLAY_STORED_SUBS = "Here's a list of potential substitutes for the " +
            "ingredient %s: \n%s";

    private final Ingredient queryIngredient;

    public SubCommand(Ingredient queryIngredient) {
        this.queryIngredient = queryIngredient;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        ReadOnlyRecipeBook recipeBook = model.getRecipeBook();
        ObservableList<Recipe> recipeObservableList = recipeBook.getRecipeList();

        HashSet<Ingredient> subList = new HashSet<>();

        // query all recipes in the current recipe book
        for (Recipe r : recipeObservableList) {
            HashMap<Ingredient, IngredientInformation> tokens = r.getIngredients();
            // if recipe contains queried ingredient
            if (tokens.containsKey(queryIngredient)) {
                //inquire for subs
                IngredientInformation rInfo = tokens.get(queryIngredient);
                if (!rInfo.getSubstitutions().isEmpty()) {
                    // List of substitutions within that recipe
                    List<Ingredient> subs = rInfo.getSubstitutions();
                    // store substitutions into subList, ensuring no duplication
                    subList.addAll(subs);
                }
            }
        }

        if (subList.isEmpty()) {
            return new CommandResult((MESSAGE_NO_STORED_SUBS));
        }

        String formattedSubList = subList.toString().substring(1, subList.toString().length() - 1);

        return new CommandResult(String.format(MESSAGE_DISPLAY_STORED_SUBS, queryIngredient,
                formattedSubList));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SubCommand) // instanceof handles nulls
                && queryIngredient.equals(((SubCommand) other).queryIngredient); //state check
    }
}
