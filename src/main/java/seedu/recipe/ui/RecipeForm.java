package seedu.recipe.ui;

//Core imports

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.model.recipe.ingredient.IngredientBuilder;
import seedu.recipe.ui.util.FieldsUtil;

/**
 * Represents the form element for users to edit {@code Recipe}s
 */
public class RecipeForm extends UiPart<Region> {
    private static final String FXML = "RecipeForm.fxml";
    private static final String INGREDIENT_PROMPT = "(i.e. `a/100 g n/parmesan cheese r/grated s/mozzarella`";
    private static final double DEFAULT_HEIGHT = 500;
    //Data fields
    private final int displayedIndex;
    private final Recipe recipe;
    private final StringBuilder data;
    //UI child elements
    @FXML
    private TextField nameField;
    @FXML
    private TextField durationField;
    @FXML
    private TextField portionField;
    @FXML
    private TextField tagsField;
    @FXML
    private FlowPane tags;
    @FXML
    private VBox ingredientsBox;
    @FXML
    private VBox stepsBox;
    //Core CTA Group
    @FXML
    private Region buttonCtrLeft;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    private Map<String, String> initialValues;

    /**
     * Creates a new RecipeForm with the given recipe and displayed index.
     * If the recipe is not null, the form fields are pre-populated with the recipe's data.
     *
     * @param recipe         The recipe to edit or null for creating a new recipe.
     * @param displayedIndex The index of the recipe in the displayed list.
     */
    public RecipeForm(Recipe recipe, int displayedIndex, StringBuilder data) {
        super(FXML);
        this.recipe = recipe;
        this.displayedIndex = displayedIndex;
        this.data = data;
        if (recipe != null) {
            populateFields();
        }
        HBox.setHgrow(buttonCtrLeft, Priority.ALWAYS);
        saveButton.setOnAction(event -> saveRecipe());
        cancelButton.setOnAction(event -> closeForm());
    }

    /**
     * Saves the changes made to the recipe and closes the form.
     * If any fields have been modified, the new values are stored
     * in a map of changed values.
     */
    private void saveRecipe() {
        // Check which fields have been changed
        Map<String, String> changedValues = new HashMap<>();
        for (Map.Entry<String, String> entry : initialValues.entrySet()) {
            String key = entry.getKey();
            String initialValue = entry.getValue();
            String currentValue = null;

            switch (key) {
            case "name":
                currentValue = nameField.getText();
                break;
            case "duration":
                currentValue = durationField.getText();
                break;
            case "portion":
                currentValue = portionField.getText();
                break;
            case "ingredients":
                currentValue = ingredientsBox.getChildren().stream()
                    .map(node -> ((TextArea) node).getText())
                    .collect(Collectors.joining(", "));
                break;
            case "steps":
                currentValue = stepsBox.getChildren().stream()
                    .map(node -> ((TextArea) node).getText())
                    .collect(Collectors.joining(", "));
                break;
            case "tags":
                currentValue = tagsField.getText();
                break;
            default:
                currentValue = "";
                break;
            }

            if (!initialValue.equals(currentValue)) {
                changedValues.put(key, currentValue);
            }
        }

        if (!changedValues.isEmpty()) {
            handleEditRecipeEvent(displayedIndex, changedValues);
        }
        closeForm();
    }

    /**
     * Closes the form without saving any changes.
     */
    private void closeForm() {
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Displays the form in a new window.
     * The window's title will be "Add Recipe" if creating a new recipe,
     * or "Edit Recipe" if editing an existing recipe.
     */
    public void display() {
        Stage window = new Stage();
        // Ensures users do not exit the view by clicking outside
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(recipe == null ? "Add Recipe" : "Edit Recipe");

        double maxHeight = Screen.getPrimary().getBounds().getMaxY();
        window.setMaxHeight(maxHeight);
        window.setHeight(Math.min(maxHeight, DEFAULT_HEIGHT));

        //Set dimensions, scene graph
        Scene scene = new Scene(getRoot());

        //Event handler for Escape Key
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                window.close();
            }
        });

        //Display
        window.setScene(scene);
        window.showAndWait();
    }

    /**
     * Stores the initial values of the form fields in a HashMap.
     * This is used for comparison when saving the recipe to determine
     * which fields have been changed.
     */
    private void storeInitialValues() {
        initialValues = new HashMap<>();
        initialValues.put("name", nameField.getText());
        initialValues.put("duration", durationField.getText());
        initialValues.put("portion", portionField.getText());
        initialValues.put("ingredients", ingredientsBox.getChildren().stream()
            .map(node -> ((TextArea) node).getText())
            .collect(Collectors.joining(", ")));

        initialValues.put("steps", stepsBox.getChildren().stream()
            .map(node -> ((TextArea) node).getText())
            .collect(Collectors.joining(", ")));

        initialValues.put("tags", tagsField.getText());
    }

    /*----------------------------------------------------------------------------------------------------------- */
    // The following functions are helper functions used in the main code above.

    /**
     * Populates the form fields with the data from the existing recipe.
     * Stores all prepopulated data into a hashmap for comparison later when saving.
     */
    private void populateFields() {
        nameField.setText(recipe.getName().recipeName);
        //Duration
        durationField.setText(
            Optional.ofNullable(recipe.getDurationNullable())
                .map(Object::toString)
                .orElse("")
                             );
        //Portion
        portionField.setText(
            Optional.ofNullable(recipe.getPortionNullable())
                .map(Object::toString)
                .orElse("")
                            );

        //Ingredients
        if (!recipe.getIngredients().isEmpty()) {
            recipe.getIngredients().forEach((ingredient, information) -> {
                String ingredientBuilder = new IngredientBuilder(ingredient, information).toString();
                TextArea ingredientField = FieldsUtil.createDynamicTextArea(ingredientBuilder);
                ingredientField.setWrapText(true);
                ingredientsBox.getChildren().add(ingredientField);
            });
        } else {
            TextArea emptyIngredientField = FieldsUtil.createDynamicTextArea("");
            emptyIngredientField.setPromptText("Add an ingredient " + INGREDIENT_PROMPT);
            ingredientsBox.getChildren().add(emptyIngredientField);
        }

        //Steps
        if (!recipe.getSteps().isEmpty()) {
            recipe.getSteps().forEach(step -> {
                TextArea stepField = FieldsUtil.createDynamicTextArea(step.toString());
                stepField.setWrapText(true);
                stepsBox.getChildren().add(stepField);
            });
        } else {
            TextArea emptyStepField = FieldsUtil.createDynamicTextArea("");
            emptyStepField.setPromptText("Add a step");
            stepsBox.getChildren().add(emptyStepField);
        }

        //Tags
        if (!recipe.getTags().isEmpty()) { //To be set as individual tag pills
            tagsField.setText(recipe.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .map(tag -> tag.tagName)
                .collect(Collectors.joining(", ")));
        } else {
            tagsField.setText("");
        }

        storeInitialValues();
    }

    /**
     * Helper method to add all the changed field data into an existing StringBuilder instance.
     *
     * @param changedValues A map of the changed recipe fields with keys as field names and values as the new data.
     */
    public void collectFields(Map<String, String> changedValues) {
        // Check if the name has been changed and append the name prefix and value.
        if (changedValues.containsKey("name")) {
            data.append(" n/");
            data.append(changedValues.get("name"));
        }

        // Check if the duration has been changed and append the duration prefix and value.
        if (changedValues.containsKey("duration")) {
            data.append(" d/");
            data.append(changedValues.get("duration"));
        }

        // Check if the portion has been changed and append the portion prefix and value.
        if (changedValues.containsKey("portion")) {
            data.append(" p/");
            data.append(changedValues.get("portion"));
        }

        // Check if the ingredients have been changed and append the ingredients prefix and value.
        if (changedValues.containsKey("ingredients")) {
            String[] ingredients = changedValues.get("ingredients").split(", ");
            for (String ingredient : ingredients) {
                data.append(" i/");
                data.append(ingredient);
            }
        }

        // Check if the steps have been changed and append the steps prefix and value.
        if (changedValues.containsKey("steps")) {
            String[] steps = changedValues.get("steps").split(", ");
            for (String step : steps) {
                data.append(" s/");
                data.append(step);
            }
        }

        // Check if the tags have been changed and append the tags prefix and value.
        if (changedValues.containsKey("tags")) {
            String[] tags = changedValues.get("tags").split(", ");
            for (String tag : tags) {
                data.append(" t/");
                data.append(tag);
            }
        }
    }

    /**
     * Handles the edit recipe event by updating the recipe with the changed values.
     *
     * @param index         The index of the recipe to be edited.
     * @param changedValues A map of the changed recipe fields with keys as field names and values as the new data.
     */
    private void handleEditRecipeEvent(int index, Map<String, String> changedValues) {
        data.append("edit ");
        data.append(index);
        collectFields(changedValues);
    }
}
