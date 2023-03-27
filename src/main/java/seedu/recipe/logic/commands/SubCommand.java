package seedu.recipe.logic.commands;

import javafx.collections.ObservableList;
import seedu.recipe.model.Model;
import seedu.recipe.model.ReadOnlyRecipeBook;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.model.recipe.ingredient.Ingredient;
import seedu.recipe.model.recipe.ingredient.IngredientInformation;

import java.util.HashMap;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

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

        StringBuilder subList = new StringBuilder();

        for (Recipe r : recipeObservableList) {
            HashMap<Ingredient, IngredientInformation> tokens = r.getIngredients();
            // if recipe requires said ingredient
            if (tokens.containsKey(queryIngredient)) {
                //inquire for subs
                IngredientInformation rInfo = tokens.get(queryIngredient);
                if (!rInfo.getSubstitutions().isEmpty()) {
                    String subs = rInfo.getSubstitutions().toString();
                    subList.append(subs.substring(1, subs.length() - 1) + "\n");
                }
            }
        }

        if (subList.toString().isBlank()) {
            return new CommandResult((MESSAGE_NO_STORED_SUBS));
        }
        return new CommandResult(String.format(MESSAGE_DISPLAY_STORED_SUBS, queryIngredient,
                subList));
    }

}
