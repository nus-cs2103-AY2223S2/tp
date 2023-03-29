package seedu.recipe.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
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
import javafx.stage.Stage;
import seedu.recipe.commons.core.LogsCenter;
import seedu.recipe.ui.util.FieldsUtil;

/**
 * Represents the form element for users to add {@code Recipe}s
 */
public class AddRecipeForm extends UiPart<Region> {
    private static final String FXML = "AddRecipeForm.fxml";

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

    @FXML
    private Region buttonCtrLeft;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    //Logic executors and system logging
    private final Logger logger = LogsCenter.getLogger(getClass());

    private StringBuilder commands;

    @FunctionalInterface
    interface CustomFocusChangeListener {
        void onFocusChange(boolean newValue);
    }

    /**
     * Creates a new AddRecipeForm instance.
     *
     * @param commandString The {@code StringBuilder} that stores the command string.
     */
    public AddRecipeForm(StringBuilder commandString) {
        super(FXML);
        this.commands = commandString;
        TextArea emptyIngredientField = FieldsUtil.createDynamicTextArea("");
        ingredientsBox.getChildren().add(emptyIngredientField);
        TextArea emptyStepField = FieldsUtil.createDynamicTextArea("");
        stepsBox.getChildren().add(emptyStepField);

        HBox.setHgrow(buttonCtrLeft, Priority.ALWAYS);
        saveButton.setOnAction(event -> saveRecipe());
        cancelButton.setOnAction(event -> closeForm());
    }

    /**
     * Saves the changes made to the recipe and closes the form.
     * If any fields have been modified, the new values are stored
     * in a map of added values.
     */
    private void saveRecipe() {
        // Check which fields have been added
        Map<String, String> inputValues = new HashMap<>();

        inputValues.put("name", nameField.getText());
        if (!durationField.getText().isEmpty()) {
            inputValues.put("duration", durationField.getText());
        }
        if (!portionField.getText().isEmpty()) {
            inputValues.put("portion", portionField.getText());
        }
        String ingredientsValue = ingredientsBox.getChildren().stream()
            .map(node -> ((TextArea) node).getText())
            .collect(Collectors.joining(", "));
        if (!ingredientsValue.isEmpty()) {
            inputValues.put("ingredients", ingredientsValue);
        }

        String stepsValue = stepsBox.getChildren().stream()
            .map(node -> ((TextArea) node).getText())
            .collect(Collectors.joining(", "));
        if (!stepsValue.isEmpty()) {
            inputValues.put("steps", stepsValue);
        }

        if (!tagsField.getText().isEmpty()) {
            inputValues.put("tags", tagsField.getText());
        }
        this.commands = handleAddRecipeEvent(inputValues);
        closeForm();
    }

    /**
     * Handles the add recipe event.
     *
     * @param inputValues A map of the added recipe fields with keys as field names and values as the new data.
     * @return commands The StringBuilder instance containing the command string, to be passed back to saveRecipe().
     */
    private StringBuilder handleAddRecipeEvent(Map<String, String> inputValues) {
        commands.append("add");
        // Check if the name has been added and append the name prefix and value.
        if (inputValues.containsKey("name")) {
            commands.append(" n/");
            commands.append(inputValues.get("name"));
        }

        // Check if the duration has been added and append the duration prefix and value.
        if (inputValues.containsKey("duration")) {
            commands.append(" d/");
            commands.append(inputValues.get("duration"));
        }

        // Check if the portion has been added and append the duration prefix and value.
        if (inputValues.containsKey("portion")) {
            commands.append(" p/");
            commands.append(inputValues.get("portion"));
        }

        // Check if the ingredients have been added and append the ingredients prefix and value.
        if (inputValues.containsKey("ingredients")) {
            String[] ingredients = inputValues.get("ingredients").split(", ");
            for (String ingredient : ingredients) {
                commands.append(" i/");
                commands.append(ingredient);
            }
        }

        // Check if the steps have been added and append the steps prefix and value.
        if (inputValues.containsKey("steps")) {
            String[] steps = inputValues.get("steps").split(", ");
            for (String step : steps) {
                commands.append(" s/");
                commands.append(step);
            }
        }

        // Check if the tags have been added and append the tags prefix and value.
        if (inputValues.containsKey("tags")) {
            String[] tags = inputValues.get("tags").split(", ");
            for (String tag : tags) {
                commands.append(" t/");
                commands.append(tag);
            }
        }
        return commands;
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
        window.setTitle("Add Recipe");
        window.setResizable(false);

        //Individual elements
        VBox pane = new VBox(getRoot());
        pane.setStyle("-fx-background-color: #3f3f46");
        Scene scene = new Scene(pane);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                window.close();
            }
        });
        window.setScene(scene);
        window.showAndWait();
    }
}
