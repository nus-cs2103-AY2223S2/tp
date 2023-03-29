package seedu.recipe.ui;

import static seedu.recipe.model.util.IngredientUtil.ingredientKeyValuePairToString;

import java.util.Comparator;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import seedu.recipe.model.recipe.Recipe;


/**
 * Represents the UI component that pops up and displays the detailed view of a Recipe.
 */
public class RecipePopup extends UiPart<Region> {
    private static final String FXML = "RecipePopup.fxml";

    public final Recipe recipe;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;

    @FXML
    private Label duration;

    @FXML
    private Label portion;

    @FXML
    private VBox ingredients;

    @FXML
    private VBox steps;

    @FXML
    private GridPane tags;

    /**
     * Generates and returns the UI instance for this Recipe card.
     *
     * @param recipe         The recipe details to display in the card.
     * @param displayedIndex The index number to display in the card.
     */
    public RecipePopup(Recipe recipe, int displayedIndex) {
        super(FXML);
        this.recipe = recipe;
        id.setText(displayedIndex + ". ");
        name.setText(recipe.getName().recipeName);

        //Duration
        duration.setText(
                Optional.ofNullable(recipe.getDurationNullable())
                        .map(Object::toString)
                        .orElse("Duration was not added."));

        //Portion
        portion.setText(
                Optional.ofNullable(recipe.getPortionNullable())
                        .map(Object::toString)
                        .orElse("Portion was not added."));

        // Ingredients
        AtomicInteger ingredientIndex = new AtomicInteger(1); // Initialize AtomicInteger for ingredients
        recipe.getIngredients()
            .forEach((ingredient, information) -> {
                Label ingredientLabel = new Label(ingredientIndex.getAndIncrement() + ". " + ingredientKeyValuePairToString(ingredient, information));
                ingredientLabel.setWrapText(true);
                ingredientLabel.setMaxWidth(500);
                ingredients.getChildren().add(ingredientLabel);
            });

        // Steps
        AtomicInteger stepIndex = new AtomicInteger(1); // Initialize AtomicInteger for steps
        recipe.getSteps()
            .forEach(step -> {
                Label stepLabel = new Label(stepIndex.getAndIncrement() + ". " + step.toString());
                stepLabel.setWrapText(true);
                stepLabel.setMaxWidth(500);
                steps.getChildren().add(stepLabel);
            });

        //Tags
        recipe.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    /**
     * Displays the UI as a new window for the JavaFX Scene.
     */
    public void display() {
        Stage window = new Stage();
        // Ensures users do not exit the view by clicking outside
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Recipe Details");
        VBox vbox = new VBox(getRoot());
        Scene scene = new Scene(vbox);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                window.close();
            }
        });
        window.setScene(scene);
        window.showAndWait();
    }
}
