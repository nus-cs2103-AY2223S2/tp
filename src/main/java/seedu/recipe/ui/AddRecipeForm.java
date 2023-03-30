package seedu.recipe.ui;

//Core imports

import java.util.Map;

import javafx.scene.control.TextArea;
import seedu.recipe.ui.util.FieldsUtil;

/**
 * Represents the form element for users to edit {@code Recipe}s
 */
public class AddRecipeForm extends RecipeForm {
    private static final String title = "Add New Recipe";

    /**
     * Creates a new RecipeForm with the given recipe and displayed index.
     * If the recipe is not null, the form fields are pre-populated with the recipe's data.
     *
     * @param data The StringBuilder used to store form data.
     */
    public AddRecipeForm(StringBuilder data) {
        super(null, data, title);
        init();
    }

    private void init() {
        TextArea emptyIngredientField = FieldsUtil.createDynamicTextArea("");
        ingredientsBox.getChildren().add(emptyIngredientField);
        TextArea emptyStepField = FieldsUtil.createDynamicTextArea("");
        stepsBox.getChildren().add(emptyStepField);
    }

    /**
     * Handles the edit recipe event by updating the recipe with the changed values.
     *
     * @param changedValues A map of the changed recipe fields with keys as field names and values as the new data.
     */
    @Override
    public void handle(Map<String, String> changedValues) {
        data.append("add ");
        collectFields(changedValues);
    }
}
