package seedu.recipe.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.recipe.commons.util.RecipeComparatorUtil;
import seedu.recipe.model.Model;
import seedu.recipe.model.RecipeBook;
import seedu.recipe.model.recipe.Recipe;

/**
 * Sorts the recipe book in ascending or descending order of price.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_SUCCESS = "Recipe book has been sorted!";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the recipes in your recipe book by price in the order specified.\n"
            + "Format: " + COMMAND_WORD + " ORDER\n"
            + "ORDER can only be `asc` or `desc`\n"
            + "Example: " + COMMAND_WORD + " asc";

    private final boolean isAsc;
    public SortCommand(boolean isAsc) {
        this.isAsc = isAsc;
    }
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        RecipeBook sortedRecipeBook = new RecipeBook();
        ObservableList<Recipe> oldRecipes = model.getRecipeBook().getRecipeList();
        List<Recipe> sortedRecipes = new ArrayList<>();
        for (Recipe r : oldRecipes) {
            sortedRecipes.add(r);
        }

        if (isAsc) {
            Collections.sort(sortedRecipes, new RecipeComparatorUtil());
        } else {
            Collections.sort(sortedRecipes, new RecipeComparatorUtil().reversed());
        }

        for (Recipe r : sortedRecipes) {
            sortedRecipeBook.addRecipe(r);
        }
        model.setRecipeBook(sortedRecipeBook);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
