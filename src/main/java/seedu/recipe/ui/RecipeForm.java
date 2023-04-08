package seedu.recipe.ui;

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
 * Represents a generic form element for modifying {@code Recipe}s.
 */
public abstract class RecipeForm extends UiPart<Region> {
    // constants
    private static final String FXML = "RecipeForm.fxml";
    private static final String INGREDIENT_PROMPT = "(i.e. `-a 100 g -n parmesan cheese -r grated -s mozzarella`)";
    private static final String DELIMITER = "``";
    private static final String TAG_DELIMITER = ", ";
    private static final double DEFAULT_HEIGHT = 500;

    // protected fields for package access
    protected final StringBuilder data;
    @FXML
    protected VBox ingredientsBox;
    @FXML
    protected VBox stepsBox;
    private final Recipe recipe;
    private final Map<String, String> initialValues = new HashMap<>();
    private final String title;
    @FXML
    private TextField nameField;
    @FXML
    private TextField durationField;
    @FXML
    private TextField portionField;
    @FXML
    private TextField tagsField;

    //Core CTA Group
    @FXML
    private Region buttonCtrLeft;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;

    /**
     * Creates a new RecipeForm with a given recipe and a StringBuilder to store data in.
     * If the recipe is not null, the form fields are pre-populated with the given recipe's data.
     *
     * @param recipe The given recipe to seed form data with.
     * @param data   The StringBuilder used to store form data.
     * @param title  The title of the form window.
     */
    public RecipeForm(Recipe recipe, StringBuilder data, String title) {
        super(FXML);
        this.recipe = recipe;
        this.data = data;
        this.title = title;

        // populate form fields, if a seed recipe was given
        if (recipe != null) {
            populateFields();
        }
        storeInitialValues();

        HBox.setHgrow(buttonCtrLeft, Priority.ALWAYS);
        saveButton.setOnAction(event -> saveRecipe());
        cancelButton.setOnAction(event -> closeForm());
    }

    /**
     * Saves the changes made to the recipe and closes the form.
     */
    private void saveRecipe() {
        Map<String, String> changedValues = getFormData();
        if (!changedValues.isEmpty()) {
            handle(changedValues);
        }
        closeForm();
    }

    /**
     * Reads form data and extracts changes from initial values.
     *
     * @return A map of the changed values.
     */
    private Map<String, String> getFormData() {
        // Check which fields have been changed
        Map<String, String> changedValues = new HashMap<>();
        for (Map.Entry<String, String> entry : initialValues.entrySet()) {
            String key = entry.getKey();
            String initialValue = entry.getValue();
            String currentValue;

            switch (key) {
            case "name":
                currentValue = nameField.getText().trim();
                break;
            case "duration":
                currentValue = durationField.getText().trim();
                break;
            case "portion":
                currentValue = portionField.getText().trim();
                break;
            case "ingredients":
                currentValue = ingredientsBox.getChildren().stream()
                    .map(node -> ((TextArea) node).getText().trim())
                    .filter(str -> !str.isEmpty())
                    .collect(Collectors.joining(DELIMITER));
                break;
            case "steps":
                currentValue = stepsBox.getChildren().stream()
                    .map(node -> ((TextArea) node).getText().trim())
                    .filter(str -> !str.isEmpty())
                    .collect(Collectors.joining(DELIMITER));
                break;
            case "tags":
                currentValue = tagsField.getText().trim();
                break;
            default:
                currentValue = "";
                break;
            }

            if (!initialValue.equals(currentValue)) {
                changedValues.put(key, currentValue);
            }
        }

        return changedValues;
    }

    /**
     * Closes the form window.
     */
    private void closeForm() {
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Displays the form by creating a new window.
     */
    public void display() {
        Stage window = new Stage();

        // Ensures users do not exit the view by clicking outside
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);

        double maxHeight = Screen.getPrimary().getBounds().getMaxY();
        window.setMaxHeight(maxHeight);
        window.setHeight(Math.min(maxHeight, DEFAULT_HEIGHT));

        //Set dimensions, scene graph
        Scene scene = new Scene(getRoot());

        //Event handler
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                window.close();
            } else if (event.getCode() == KeyCode.ENTER) {
                saveRecipe();
            }
        });


        //Display
        window.setScene(scene);
        window.showAndWait();
    }

    /**
     * Stores the initial values of the form fields in a HashMap.
     */
    private void storeInitialValues() {
        initialValues.put("name", nameField.getText().trim());
        initialValues.put("duration", durationField.getText().trim());
        initialValues.put("portion", portionField.getText().trim());
        initialValues.put("ingredients", ingredientsBox.getChildren().stream()
            .map(node -> ((TextArea) node).getText().trim())
            .collect(Collectors.joining(DELIMITER)));

        initialValues.put("steps", stepsBox.getChildren().stream()
            .map(node -> ((TextArea) node).getText().trim())
            .collect(Collectors.joining(DELIMITER)));

        initialValues.put("tags", tagsField.getText().trim());
    }

    /*----------------------------------------------------------------------------------------------------------- */
    // The following functions are helper functions used in the main code above.

    /**
     * Populates the form fields with the data from the seed recipe.
     */
    private void populateFields() {
        nameField.setText(recipe.getName().recipeName);
        //Duration
        durationField.setText(
            Optional.ofNullable(recipe.getDurationNullable())
                .map(Object::toString)
                .orElse(""));
        //Portion
        portionField.setText(
            Optional.ofNullable(recipe.getPortionNullable())
                .map(Object::toString)
                .orElse(""));

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
                .collect(Collectors.joining(TAG_DELIMITER)));
        } else {
            tagsField.setText("");
        }
    }

    /**
     * Stores changed recipe fields into the data StringBuilder.
     *
     * @param changedValues A map of the changed recipe fields with keys as field names and values as the new data.
     */
    protected void collectFields(Map<String, String> changedValues) {
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
            String[] ingredients = changedValues.get("ingredients").split(DELIMITER);
            for (String ingredient : ingredients) {
                data.append(" i/");
                data.append(ingredient);
            }
        }

        // Check if the steps have been changed and append the steps prefix and value.
        if (changedValues.containsKey("steps")) {
            String[] steps = changedValues.get("steps").split(DELIMITER);
            for (String step : steps) {
                data.append(" s/");
                data.append(step);
            }
        }

        // Check if the tags have been changed and append the tags prefix and value.
        if (changedValues.containsKey("tags")) {
            String[] tags = changedValues.get("tags").split(TAG_DELIMITER);
            for (String tag : tags) {
                data.append(" t/");
                data.append(tag);
            }
        }
    }

    /**
     * Processes the data in the form for use externally.
     *
     * @param changedValues A map of the changed recipe fields with keys as field names and values as the new data.
     */
    protected abstract void handle(Map<String, String> changedValues);
}
