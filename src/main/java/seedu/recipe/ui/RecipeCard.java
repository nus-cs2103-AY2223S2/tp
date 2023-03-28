package seedu.recipe.ui;

//Core Java Imports
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

//JavaFX libraries
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
//Custom Imports
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.model.recipe.Step;
import seedu.recipe.model.recipe.ingredient.Ingredient;
import seedu.recipe.model.recipe.ingredient.IngredientInformation;
import seedu.recipe.model.tag.Tag;
import seedu.recipe.model.util.IngredientUtil;
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
    private FlowPane emptyTags;

    @FXML
    private GridPane ingredients;

    @FXML
    private GridPane steps;

    @FXML
    private VBox borderContainer;

    private final CommandExecutor commandExecutor;
    /**
     * Creates a {@code RecipeCode} with the given {@code Recipe} and index to display
     * @param recipe the {@code Recipe} to display
     * @param displayedIndex the index of the {@code Recipe} in the list
     */
    public RecipeCard(Recipe recipe, int displayedIndex, CommandExecutor executor) {
        super(FXML);
        borderContainer.minHeightProperty().bind(this.getRoot().heightProperty().multiply(0.8));
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
        setIngredients(recipe.getIngredients());

        //Steps
        setSteps(recipe.getSteps());

        //Tags
        setTags(recipe.getTags());

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
                if (showConfirmationDialog()) {
                    DeleteRecipeEvent deleteEvent = new DeleteRecipeEvent(displayedIndex);
                    cardPane.fireEvent(deleteEvent);
                }
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
            ingredients.add(createLabel("No ingredients were added for this Recipe. Add some!"), 0, 0);
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
        if (count == 3 && entries.hasNext()) {
            ingredients.add(createLabel(
                    "... and " + (ingredientsTable.size() - 3) + " more ingredients"), 0, count);
        }
    }

    private void setSteps(List<Step> stepList) {
        if (stepList.size() == 0) {
            steps.add(createLabel("No steps were added for this recipe. Add some!"), 0, 0);
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
            Label emptyLabel = createLabel("No Tags were added. Add some!");
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

    private boolean showConfirmationDialog() {
        Stage confirmationDialog = new Stage();
        confirmationDialog.initModality(Modality.APPLICATION_MODAL);
        confirmationDialog.setTitle("Confirm Deletion");

        AtomicBoolean confirmed = new AtomicBoolean(false);

        Label label = new Label("Are you sure you want to delete this recipe?");
        Button confirmButton = new Button("Confirm");
        Button cancelButton = new Button("Cancel");
        // set confirm button on action
        confirmButton.setOnAction(e -> {
            confirmed.set(true);
            confirmationDialog.close();
        });
        // set cancel button on action
        cancelButton.setOnAction(e -> confirmationDialog.close());
        // set confirm button on key pressed
        confirmButton.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                confirmed.set(true);
                confirmationDialog.close();
            }
        });
        // set cancel button on key pressed
        cancelButton.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                confirmed.set(false);
                confirmationDialog.close();
            }
        });
        // initialize HBox buttons
        HBox buttons = new HBox(10);
        buttons.getChildren().addAll(confirmButton, cancelButton);
        buttons.setAlignment(Pos.CENTER);
        // initialize VBox layout
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, buttons);
        layout.setAlignment(Pos.CENTER);
        // initialize scene
        Scene scene = new Scene(layout, 300, 200);
        confirmationDialog.setScene(scene);
        // set scene on key pressed
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                confirmed.set(true);
                confirmationDialog.close();
            } else if (event.getCode() == KeyCode.ESCAPE) {
                confirmed.set(false);
                confirmationDialog.close();
            }
        });
        confirmationDialog.showAndWait();
        return confirmed.get();
    }
}
