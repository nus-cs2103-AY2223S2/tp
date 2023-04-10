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
public class StarCommand extends Command {

    public static final String COMMAND_WORD = "star";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds the recipe at the specified recipe number into your favourites.\n"
            + "Format: star RECIPE_NUMBER\n"
            + "RECIPE_NUMBER must be a positive integer starting from 1 and must exist in the recipe book.\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_STAR_RECIPE_SUCCESS = "Starred Recipe: %1$s";

    private final Index targetIndex;

    public StarCommand(Index targetIndex) {
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
        if (recipeToStar.isStarred()) {
            throw new CommandException(Messages.MESSAGE_RECIPE_ALREADY_STARRED);
        }
        model.setRecipe(recipeToStar, editedRecipe);
        model.updateFilteredRecipeList(PREDICATE_SHOW_ALL_RECIPES);
        return new CommandResult(String.format(MESSAGE_STAR_RECIPE_SUCCESS, recipeToStar.getTitle()));

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
                true);
    }
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StarCommand // instanceof handles nulls
                && targetIndex.equals(((StarCommand) other).targetIndex)); // state check
    }
}
