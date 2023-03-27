package seedu.recipe.ui;

import static seedu.recipe.model.util.IngredientUtil.ingredientKeyValuePairToString;

import java.awt.*;
import java.util.Comparator;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.ui.CommandBox.CommandExecutor;
import seedu.recipe.ui.events.DeleteRecipeEvent;


/**
 * A UI component that displays information of a {@code Recipe}.
 */
public class RecipeCard extends UiPart<Region> {

    private static final String FXML = "RecipeListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/recipebook-level4/issues/336">The issue on RecipeBook level 4</a>
     */
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
    private Label ingredientsTitle;

    @FXML
    private Label stepsTitle;

    @FXML
    private Label tagsTitle;

    @FXML
    private FlowPane tags;

    @FXML
    private GridPane ingredients;

    @FXML
    private GridPane steps;

    private final CommandExecutor commandExecutor;
    /**
     * Creates a {@code RecipeCode} with the given {@code Recipe} and index to display
     * @param recipe the {@code Recipe} to display
     * @param displayedIndex the index of the {@code Recipe} in the list
     */
    public RecipeCard(Recipe recipe, int displayedIndex, CommandExecutor executor) {
        super(FXML);
        this.recipe = recipe;
        this.commandExecutor = executor;

        cardPane.setFocusTraversable(true);
        id.setText(displayedIndex + ". ");
        name.setText(recipe.getName().recipeName);

        // ingredients.setOrientation(Orientation.VERTICAL);
        // steps.setOrientation(Orientation.VERTICAL);

        //Duration
        duration.setText("Duration: "
                + Optional.ofNullable(recipe.getDurationNullable())
                        .map(Object::toString)
                        .orElse("Duration was not added."));

        //Portion
        portion.setText("Portion: "
                + Optional.ofNullable(recipe.getPortionNullable())
                        .map(Object::toString)
                        .orElse("Portion was not added."));

        //Ingredients
        ingredientsTitle.setText("Ingredients:");
        GridManager ingredientManager = new GridManager();
        recipe.getIngredients()
            .forEach((ingredient, information) -> ingredients
                .add(
                    createUnorderedListItem(
                        ingredientKeyValuePairToString(ingredient, information)
                    ),
                    0, ingredientManager.getCount())
            );

        //Steps
        GridManager stepLabelManager = new GridManager(1);
        GridManager stepGridManager = new GridManager();
        stepsTitle.setText("Steps:");
        recipe.getSteps()
            .forEach(step -> steps.add(
                createOrderedListItem(stepLabelManager, step.toString()), 0, stepGridManager.getCount()));

        //Tags
        tagsTitle.setText("Steps:");
        recipe.getTags().stream()
            .sorted(Comparator.comparing(tag -> tag.tagName))
            .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        //Selector focus
        cardPane.setOnMouseEntered(event -> {
            cardPane.requestFocus();
        });

        // Add a click listener to the cardPane node
        cardPane.setOnMouseClicked(event -> {
            cardPane.requestFocus();
            RecipePopup popup = new RecipePopup(recipe, displayedIndex);
            popup.display();
        });

        // Handle keypress events
        cardPane.setOnKeyPressed(event -> {
            cardPane.requestFocus();
            KeyCode input = event.getCode();
            if (input == KeyCode.DELETE
                    || input == KeyCode.D
                    || input == KeyCode.BACK_SPACE) {
                DeleteRecipeEvent deleteEvent = new DeleteRecipeEvent(displayedIndex);
                cardPane.fireEvent(deleteEvent);
            } else if (event.getCode() == KeyCode.P) {
                RecipePopup popup = new RecipePopup(recipe, displayedIndex);
                popup.display();
            } else if (event.getCode() == KeyCode.F) {
                try {
                    RecipeForm form = new RecipeForm(recipe, displayedIndex, commandExecutor);
                    form.display();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });
    }

    private Label createUnorderedListItem(String text) {
        return createLabel("• " + text);
    }

    private Label createOrderedListItem(GridManager manager, String text) {
        return createLabel(manager.getCount() + ". " + text);
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        label.setWrapText(true);
        return label;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RecipeCard)) {
            return false;
        }

        // state check
        RecipeCard card = (RecipeCard) other;
        return id.getText().equals(card.id.getText())
                && recipe.equals(card.recipe);
    }

    private static class GridManager {
        private int count;

        public GridManager() {
            count = 0;
        }

        public GridManager(int startCount) {
            assert startCount >= 0;
            count = startCount;
        }

        public int getCount() {
            int out = count;
            count += 1;
            return out;
        }
        public void reset() {
            count = 0;
        }
    }
}
