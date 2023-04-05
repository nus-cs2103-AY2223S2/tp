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
            if (recipe.isStarred()) {
                recipeTitle.setText(recipe.getTitle().toString() + " ★"
                        + " [Price: $" + String.format("%.2f", recipe.getCost()) + "]");
            } else {
                recipeTitle.setText(recipe.getTitle().toString()
                                    + " [Price: $" + String.format("%.2f", recipe.getCost()) + "]");
            }
            recipeDesc.setText(recipe.getDesc().toString());
            ingredients.getChildren().clear();
            ingredientsTitle.setText("Ingredients");
            recipe.getIngredients().stream()
                    .forEach(ingredient -> {
                        String eachIngredient = "•  " + ingredient.toDisplayString();
                        Label ingredientLabel = new Label(eachIngredient);
                        ingredientLabel.setWrapText(true);
                        ingredients.getChildren().add(ingredientLabel);
                    });
            steps.getChildren().clear();
            stepsTitle.setText("Instructions");
            /*
            recipe.getSteps().stream()
                    .forEach(step -> {
                        String eachStep = recipe.getSteps().indexOf(step) + 1 + ".  " + step.step;
                        Label stepLabel = new Label(eachStep);
                        stepLabel.setWrapText(true);
                        steps.getChildren().add(stepLabel);
                    });
             */
            for (int i = 1; i <= recipe.getSteps().size(); ++i) {
                String eachStep = i + ".  " + recipe.getSteps().get(i - 1).step;
                Label stepLabel = new Label(eachStep);
                stepLabel.setWrapText(true);
                steps.getChildren().add(stepLabel);
            }
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
