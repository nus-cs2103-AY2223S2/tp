package seedu.recipe.ui;

import java.util.Map;

import javafx.scene.control.TextArea;
import seedu.recipe.ui.util.FieldsUtil;

/**
 * Represents the form element for users to add {@code Recipe}s
 */
public class AddRecipeForm extends RecipeForm {
    private static final String title = "Add New Recipe";

    /**
     * Creates a new AddRecipeForm.
     */
    public AddRecipeForm(StringBuilder data) {
        super(null, data, title);
        init();
    }

    /**
     * Initializes the AddRecipeForm.
     * <p>
     * Adds blank TextAreas under the Ingredient and Step boxes.
     */
    private void init() {
        TextArea emptyIngredientField = FieldsUtil.createDynamicTextArea("");
        ingredientsBox.getChildren().add(emptyIngredientField);
        TextArea emptyStepField = FieldsUtil.createDynamicTextArea("");
        stepsBox.getChildren().add(emptyStepField);
    }

    /**
     * Handles the add recipe event by updating the recipe with the changed values.
     *
     * @param changedValues A map of the changed recipe fields with keys as field names and values as the new data.
     */
    @Override
    protected void handle(Map<String, String> changedValues) {
        data.append("add ");
        collectFields(changedValues);
    }
}
