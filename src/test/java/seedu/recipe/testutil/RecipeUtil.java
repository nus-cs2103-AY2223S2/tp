package seedu.recipe.testutil;

import static seedu.recipe.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_STEP;
import static seedu.recipe.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.List;
import java.util.Set;

import seedu.recipe.logic.commands.AddCommand;
import seedu.recipe.logic.commands.EditCommand.EditRecipeDescriptor;
import seedu.recipe.model.recipe.Ingredient;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.model.recipe.Step;

/**
 * A utility class for Recipe.
 */
public class RecipeUtil {

    /**
     * Returns an add command string for adding the {@code recipe}.
     */
    public static String getAddCommand(Recipe recipe) {
        return AddCommand.COMMAND_WORD + " " + getRecipeDetails(recipe);
    }

    /**
     * Returns the part of command string for the given {@code recipe}'s details.
     */
    public static String getRecipeDetails(Recipe recipe) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_TITLE + recipe.getTitle().toString() + " ");
        sb.append(PREFIX_DESCRIPTION + recipe.getDesc().toString() + " ");
        sb.append(PREFIX_INGREDIENT + recipe.getIngredients().toString() + " ");
        sb.append(PREFIX_STEP + recipe.getSteps().toString() + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditRecipeDescriptor}'s details.
     */
    public static String getEditRecipeDescriptorDetails(EditRecipeDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getTitle().ifPresent(title -> sb.append(PREFIX_TITLE).append(title.toString()).append(" "));
        descriptor.getDesc().ifPresent(desc -> sb.append(PREFIX_DESCRIPTION).append(desc.toString()).append(" "));
        if (descriptor.getIngredients().isPresent()) {
            Set<Ingredient> ingredients = descriptor.getIngredients().get();
            if (ingredients.isEmpty()) {
                sb.append(PREFIX_INGREDIENT);
            } else {
                ingredients.forEach(s -> sb.append(PREFIX_INGREDIENT).append(s.toString()).append(" "));
            }
        }
        if (descriptor.getSteps().isPresent()) {
            List<Step> steps = descriptor.getSteps().get();
            if (steps.isEmpty()) {
                sb.append(PREFIX_STEP);
            } else {
                steps.forEach(s -> sb.append(PREFIX_STEP).append(s.toString()).append(" "));
            }
        }

        return sb.toString();
    }
}
