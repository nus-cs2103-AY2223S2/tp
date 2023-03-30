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
     * If the recipe is not null, the form fields are pre-populated with the recipe's data.
     *
     * @param data   The StringBuilder used to store form data.
     * @param recipe The recipe to edit or null for creating a new recipe.
     * @param index  The index of the recipe in the displayed list.
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
    public void handle(Map<String, String> changedValues) {
        data.append("edit ");
        data.append(index);
        collectFields(changedValues);
    }
}
