package seedu.recipe.ui;

import java.util.Map;

import javafx.scene.control.TextArea;
import seedu.recipe.ui.util.FieldsUtil;

/**
 * Represents the form element for users to add {@code Recipe}s
 */
public class AddRecipeForm extends RecipeForm {
    private static final String title = "Add New Recipe";
    private static final String INGREDIENT_PROMPT = "(i.e. `-a 100 g -n parmesan cheese -r grated -s mozzarella`";

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
        emptyIngredientField.setPromptText("Add an ingredient " + INGREDIENT_PROMPT);
        ingredientsBox.getChildren().add(emptyIngredientField);
        TextArea emptyStepField = FieldsUtil.createDynamicTextArea("");
        emptyStepField.setPromptText("Add a step");
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
