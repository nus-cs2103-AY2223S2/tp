package seedu.recipe.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.recipe.model.Model.PREDICATE_SHOW_ALL_RECIPES;

import java.util.List;
import java.util.Set;

import seedu.recipe.commons.core.Messages;
import seedu.recipe.commons.core.index.Index;
import seedu.recipe.logic.commands.exceptions.CommandException;
import seedu.recipe.model.Model;
import seedu.recipe.model.recipe.Description;
import seedu.recipe.model.recipe.Ingredient;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.model.recipe.Step;
import seedu.recipe.model.recipe.Title;
import seedu.recipe.model.tag.Tag;

/**
 * Marks a recipe as in favorites identified using it's displayed index from the recipe book.
 */
public class UnstarCommand extends Command {

    public static final String COMMAND_WORD = "unstar";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes the recipe at the specified recipe number from your favourites.\n"
            + "Format: unstar RECIPE_NUMBER\n"
            + "RECIPE_NUMBER must be a positive integer starting from 1 and must exist in the recipe book.\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_UNSTAR_RECIPE_SUCCESS = "Unstarred Recipe: %1$s";

    private final Index targetIndex;

    public UnstarCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Recipe> lastShownList = model.getFilteredRecipeList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_RECIPE_DISPLAYED_INDEX);
        }

        Recipe recipeToStar = lastShownList.get(targetIndex.getZeroBased());
        Recipe editedRecipe = createEditedRecipe(recipeToStar);
        if (!recipeToStar.isStarred()) {
            throw new CommandException(Messages.MESSAGE_RECIPE_NOT_STARRED);
        }
        model.setRecipe(recipeToStar, editedRecipe);
        model.updateFilteredRecipeList(PREDICATE_SHOW_ALL_RECIPES);
        return new CommandResult(String.format(MESSAGE_UNSTAR_RECIPE_SUCCESS, recipeToStar.getTitle()));
    }

    /**
     * Creates and returns a {@code Recipe} with the details of {@code recipeToEdit}
     * edited with {@code editRecipeDescriptor}.
     */
    public Recipe createEditedRecipe(Recipe recipeToStar) {
        assert recipeToStar != null;
        Title updatedTitle = recipeToStar.getTitle();
        Description updatedDesc = recipeToStar.getDesc();
        Set<Ingredient> updatedIngredients = recipeToStar.getIngredients();
        List<Step> updatedSteps = recipeToStar.getSteps();
        Set<Tag> updatedTags = recipeToStar.getTags();
        return new Recipe(updatedTitle, updatedDesc, updatedIngredients, updatedSteps, updatedTags,
                false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnstarCommand // instanceof handles nulls
                && targetIndex.equals(((UnstarCommand) other).targetIndex)); // state check
    }
}
