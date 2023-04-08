package seedu.recipe.ui;

//Core Java Imports

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.model.recipe.Step;
import seedu.recipe.model.recipe.ingredient.Ingredient;
import seedu.recipe.model.recipe.ingredient.IngredientInformation;
import seedu.recipe.model.tag.Tag;
import seedu.recipe.model.util.IngredientUtil;
import seedu.recipe.ui.events.DeleteRecipeEvent;
import seedu.recipe.ui.events.EditRecipeEvent;


/**
 * A UI component that displays information of a {@code Recipe}.
 */
public class RecipeCard extends UiPart<Region> {
    public static final String MESSAGE_EMPTY_FIELD = "No %s added yet. Add some!";
    public static final String MESSAGE_EMPTY_FIELD_SHORT = "No %s added yet.";
    private static final String FXML = "RecipeListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/recipebook-level4/issues/336">The issue on RecipeBook level 4</a>
     */
    public final Recipe recipe;
    private boolean isRecipeSelected = false;
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
    private FlowPane emptyTags;
    @FXML
    private GridPane ingredients;
    @FXML
    private GridPane steps;
    @FXML
    private VBox borderContainer;

    /**
     * Creates a {@code RecipeCode} with the given {@code Recipe} and index to display
     *
     * @param recipe         the {@code Recipe} to display
     * @param displayedIndex the index of the {@code Recipe} in the list
     * @param isSelected     indicates if the card is being selected in the list panel
     */
    public RecipeCard(Recipe recipe, int displayedIndex, boolean isSelected) {
        super(FXML);
        borderContainer.minHeightProperty().bind(this.getRoot().heightProperty().multiply(0.8));
        this.recipe = recipe;
        this.isRecipeSelected = isSelected;
        cardPane.setFocusTraversable(true);
        id.setText(displayedIndex + ". ");
        name.setText(recipe.getName().recipeName);

        //Duration
        duration.setText("Duration: "
            + Optional.ofNullable(recipe.getDurationNullable())
            .map(Object::toString)
            .orElse(String.format(MESSAGE_EMPTY_FIELD_SHORT, "duration")));

        //Portion
        portion.setText("Portion: "
            + Optional.ofNullable(recipe.getPortionNullable())
            .map(Object::toString)
            .orElse(String.format(MESSAGE_EMPTY_FIELD_SHORT, "portion")));

        //Ingredients
        setIngredients(recipe.getIngredients());

        //Steps
        setSteps(recipe.getSteps());

        //Tags
        setTags(recipe.getTags());

        // Handle keypress events
        cardPane.setOnKeyPressed(event -> {
            KeyCode input = event.getCode();
            ConfirmationDialog deleteConfirmation = new ConfirmationDialog();
            if (input == KeyCode.DELETE
                || input == KeyCode.D
                || input == KeyCode.BACK_SPACE) {
                if (deleteConfirmation.getConfirmation()) {
                    DeleteRecipeEvent deleteEvent = new DeleteRecipeEvent(displayedIndex);
                    cardPane.fireEvent(deleteEvent);
                }
            } else if (event.getCode() == KeyCode.P) {
                RecipePopup popup = new RecipePopup(recipe, displayedIndex);
                popup.display();
            } else if (event.getCode() == KeyCode.F) {
                // create and display form
                StringBuilder data = new StringBuilder();
                EditRecipeForm form = new EditRecipeForm(recipe, displayedIndex, data);
                form.display();

                // trigger EditRecipeEvent with form data
                String commandText = data.toString();
                if (!commandText.isEmpty()) {
                    EditRecipeEvent editEvent = new EditRecipeEvent(commandText);
                    cardPane.fireEvent(editEvent);
                }
            }
        });

        //Selector focus
        cardPane.setOnMouseEntered(event -> {
            if (isRecipeSelected) {
                cardPane.requestFocus();
            }
        });

        // Add a click listener to the cardPane node
        cardPane.setOnMouseClicked(event -> {
            cardPane.requestFocus();
        });

    }

    private Label createUnorderedListItem(String text) {
        return createLabel("• " + text);
    }

    private Label createOrderedListItem(int count, String text) {
        return createLabel(count + ". " + text);
    }

    private Label createLabel(String text) {
        Label label = new Label(text);
        label.setWrapText(true);
        return label;
    }

    private void setIngredients(HashMap<Ingredient, IngredientInformation> ingredientsTable) {
        if (ingredientsTable.size() == 0) {
            ingredients.add(createLabel(String.format(MESSAGE_EMPTY_FIELD, "ingredients")), 0, 0);
            return;
        }
        int count = 0;
        Iterator<Entry<Ingredient, IngredientInformation>> entries = ingredientsTable.entrySet().iterator();
        while (entries.hasNext() && count < 3) {
            Entry<Ingredient, IngredientInformation> nextIngredient = entries.next();
            ingredients.add(createUnorderedListItem(
                IngredientUtil.ingredientKeyValuePairToString(nextIngredient.getKey(), nextIngredient.getValue())
                   ), 0, count);
            count += 1;
        }

        //Truncate for line count longer than 3
        if (count == 3 && entries.hasNext()) {
            ingredients.add(createLabel(
                "... and " + (ingredientsTable.size() - 3) + " more ingredients"), 0, count);
        }
    }

    private void setSteps(List<Step> stepList) {
        if (stepList.size() == 0) {
            steps.add(createLabel(String.format(MESSAGE_EMPTY_FIELD, "steps")), 0, 0);
            return;
        }
        int count = 1;
        while (count < 4 && count <= stepList.size()) {
            steps.add(createOrderedListItem(count, stepList.get(count - 1).toString()), 0, count - 1);
            count += 1;
        }
        if (count == 4 && stepList.size() > 4) {
            steps.add(createLabel("... and " + (stepList.size() - count) + " more steps"), 0, count);
        }
    }

    private void setTags(Set<Tag> tagSet) {
        if (tagSet.size() == 0) {
            Label emptyLabel = createLabel(String.format(MESSAGE_EMPTY_FIELD, "tags"));
            emptyLabel.getStyleClass().add("empty-label");
            emptyTags.getChildren().add(emptyLabel);
            return;
        }
        tagSet.forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
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

}
