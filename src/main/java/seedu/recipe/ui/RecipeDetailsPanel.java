package seedu.recipe.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.recipe.commons.core.LogsCenter;
import seedu.recipe.model.recipe.Recipe;


/**
 * Panel containing the list of recipes.
 */
public class RecipeDetailsPanel extends UiPart<Region> {
    private static final String FXML = "RecipeDetailsPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(RecipeListPanel.class);

    @FXML
    private Label recipeTitle;

    @FXML
    private Label recipeDesc;

    @FXML
    private VBox steps;

    @FXML
    private Label stepsTitle;

    @FXML
    private VBox ingredients;

    @FXML
    private Label ingredientsTitle;


    /**
     * Creates a {@code RecipeListPanel} with the given {@code ObservableList}.
     */
    public RecipeDetailsPanel() {
        super(FXML);
    }

    public void setRecipeDetails(Recipe recipe) {
        if (recipe != null) {
            recipeTitle.setText(recipe.getTitle().toString());
            recipeDesc.setText(recipe.getDesc().toString());
            ingredients.getChildren().clear();
            ingredientsTitle.setText("What you will need:");
            recipe.getIngredients().stream()
                    .forEach(ingredient -> {
                        Label ingredientLabel = new Label(ingredient.ingredient);
                        ingredients.getChildren().add(ingredientLabel);
                    });
            steps.getChildren().clear();
            stepsTitle.setText("Instructions:");
            recipe.getSteps().stream()
                    .forEach(step -> {
                        Label stepLabel = new Label(step.step);
                        steps.getChildren().add(stepLabel);
                    });
        }
    }

    public void setEmptyRecipeDetails() {
        recipeTitle.setText(null);
        recipeDesc.setText(null);
        stepsTitle.setText(null);
        ingredientsTitle.setText(null);
        steps.getChildren().clear();
        ingredients.getChildren().clear();
    }
}
