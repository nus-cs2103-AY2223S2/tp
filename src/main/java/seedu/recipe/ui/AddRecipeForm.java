package seedu.recipe.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import seedu.recipe.commons.core.LogsCenter;

/**
 * Represents the form element for users to add {@code Recipe}s
 */
public class AddRecipeForm extends UiPart<Region> {
    private static final String FXML = "RecipeForm.fxml";

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
     * @param stringBuilder The {@code StringBuilder} that stores the command string.
     */
    public AddRecipeForm(StringBuilder stringBuilder) {
        super(FXML);
        this.commands = stringBuilder;
        TextField emptyIngredientField = createDynamicTextField("");
        ingredientsBox.getChildren().add(emptyIngredientField);
        TextField emptyStepField = createDynamicTextField("");
        stepsBox.getChildren().add(emptyStepField);
        // assert test on save button
        assert saveButton != null;
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
            .map(node -> ((TextField) node).getText())
            .collect(Collectors.joining(", "));
        if (!ingredientsValue.isEmpty()) {
            inputValues.put("ingredients", ingredientsValue);
        }

        String stepsValue = stepsBox.getChildren().stream()
            .map(node -> ((TextField) node).getText())
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
     * Returns the next TextField below the current TextField within the same parent VBox, if any.
     *
     * @param currentTextField The current TextField.
     * @return The next TextField below the current TextField or null if there's no TextField below it.
     */
    private TextField getNextTextField(TextField currentTextField) {
        VBox parentBox = (VBox) currentTextField.getParent();
        int currentIndex = parentBox.getChildren().indexOf(currentTextField);
        int lastIndex = parentBox.getChildren().size() - 1;
        if (currentIndex < lastIndex) {
            Node nextNode = parentBox.getChildren().get(currentIndex + 1);
            if (nextNode instanceof TextField) {
                return (TextField) nextNode;
            }
        }
        return null;
    }

    /**
     * Creates a dynamic TextField with the specified initial text.
     * The TextField will support UP, DOWN, and TAB navigation.
     * If the TextField is the last in the VBox and gains focus, a new empty TextField will be added below it.
     * If the TextField loses focus and is the last in the VBox and empty, it will be removed.
     *
     * @param text The initial text for the TextField.
     * @return The created dynamic TextField.
     */
    private TextField createDynamicTextField(String text) {
        TextField textField = new TextField(text);

        //Keyboard listener for navigation
        textField.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            int currentIndex = ((VBox) textField.getParent()).getChildren().indexOf(textField);
            if (event.getCode() == KeyCode.UP) {
                // Condition 1: UP key pressed
                if (currentIndex > 0) {
                    TextField prevField = (TextField) ((VBox) textField.getParent())
                                                                       .getChildren().get(currentIndex - 1);
                    prevField.requestFocus();
                }
            } else if (event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.TAB) {
                TextField nextField = (TextField) ((VBox) textField.getParent()).getChildren().get(currentIndex + 1);
                // Condition 2.1: DOWN key pressed
                if (event.getCode() == KeyCode.DOWN) {
                    nextField.requestFocus();
                // Condition 2.2: TAB key pressed
                } else if (event.getCode() == KeyCode.TAB) {
                    // If it is a new placeholder row and there's another TextField after it, skip to the field after
                    if (nextField.getText().isEmpty() && currentIndex + 2
                            < ((VBox) textField.getParent()).getChildren().size()) {
                        nextField = (TextField) ((VBox) textField.getParent()).getChildren().get(currentIndex + 2);
                    }
                    nextField.requestFocus();
                }
                event.consume();
            } else {
                // Default: Do nothing
            }
        });
        //Textfield listener for automatically adding/removing new input rows
        textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            VBox parentBox = (VBox) textField.getParent();
            int lastIndex = parentBox.getChildren().size() - 1;
            // Check if the TextField has gained focus
            if (newValue) {
                // Check if it's the last TextField in the VBox
                if (parentBox.getChildren().indexOf(textField) == lastIndex) {
                    TextField newField = createDynamicTextField("");
                    parentBox.getChildren().add(newField);
                }
            } else {
                // Check if it's the last TextField, it's empty, and the focus is not in the same VBox, then remove it
                if (getNextTextField(textField).getText().isEmpty() && textField.getText().isEmpty()) {
                    parentBox.getChildren().remove(getNextTextField(textField));
                }
            }
        });
        return textField;
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
        window.setMinWidth(500);
        window.setMinHeight(700);
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
