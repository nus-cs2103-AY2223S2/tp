package seedu.recipe.ui;

//Core imports

import java.util.Map;

import seedu.recipe.model.recipe.Recipe;

/**
 * Represents the form element for users to edit {@code Recipe}s
 */
public class EditRecipeForm extends RecipeForm {
    private static final String title = "Edit Recipe";
    private final int index;

    /**
     * Creates a new EditRecipeForm with the given recipe and displayed index.
     */
    public EditRecipeForm(Recipe recipe, int index, StringBuilder data) {
        super(recipe, data, title);
        this.index = index;
    }

    /**
     * Handles the edit recipe event by updating the recipe with the changed values.
     *
     * @param changedValues A map of the changed recipe fields with keys as field names and values as the new data.
     */
    @Override
    protected void handle(Map<String, String> changedValues) {
        data.append("edit ");
        data.append(index);
        collectFields(changedValues);
    }
}
