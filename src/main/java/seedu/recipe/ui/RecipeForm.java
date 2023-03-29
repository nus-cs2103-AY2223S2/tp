package seedu.recipe.ui;

//Core imports
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

//JavaFX imports
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
//Custom imports
import seedu.recipe.commons.core.LogsCenter;
import seedu.recipe.logic.commands.CommandResult;
import seedu.recipe.logic.commands.exceptions.CommandException;
import seedu.recipe.logic.parser.exceptions.ParseException;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.ui.CommandBox.CommandExecutor;

/**
 * Represents the form element for users to edit {@code Recipe}s
 */
public class RecipeForm extends UiPart<Region> {
    private static final String FXML = "RecipeForm.fxml";
    private static final String INGREDIENT_PROMPT = "(i.e. `a/100 g n/parmesan cheese r/grated s/mozzarella`";

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

    //Data fields
    private final int displayedIndex;
    private Map<String, String> initialValues;
    private final Recipe recipe;

    //Logic executors and system logging
    private final CommandExecutor commandExecutor;
    private final Logger logger = LogsCenter.getLogger(getClass());

    @FunctionalInterface
    interface CustomFocusChangeListener {
        void onFocusChange(boolean newValue);
    }
    /**
     * Creates a new RecipeForm with the given recipe and displayed index.
     * If the recipe is not null, the form fields are pre-populated with the recipe's data.
     *
     * @param recipe         The recipe to edit or null for creating a new recipe.
     * @param displayedIndex The index of the recipe in the displayed list.
     */
    public RecipeForm(Recipe recipe, int displayedIndex, CommandExecutor commandExecutor) {
        super(FXML);
        this.recipe = recipe;
        this.commandExecutor = commandExecutor;
        this.displayedIndex = displayedIndex;
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
        try {
            // Add Recipe
            if (initialValues.isEmpty()) {
                handleAddRecipeEvent(changedValues);
            }
            handleEditRecipeEvent(displayedIndex, changedValues);
            closeForm();
        } catch (Exception e) {
            throw e;
        }
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

        //Set dimensions, scene graph
        window.setMinWidth(500);
        window.setMinHeight(700);
        window.setResizable(false);
        ScrollPane pane = new ScrollPane(getRoot());
        pane.setStyle("-fx-background-color: #3f3f46");
        Scene scene = new Scene(pane);

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

    /*----------------------------------------------------------------------------------------------------------- */
    // The following functions are helper functions used in the main code above.
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
                TextArea ingredientField = createDynamicTextArea(ingredient.toString());
                ingredientField.setWrapText(true);
                ingredientsBox.getChildren().add(ingredientField);
            });
        } else {
            TextArea emptyIngredientField = createDynamicTextArea("");
            emptyIngredientField.setPromptText("Add an ingredient " + INGREDIENT_PROMPT);
            ingredientsBox.getChildren().add(emptyIngredientField);
        }

        //Steps
        if (!recipe.getSteps().isEmpty()) {
            recipe.getSteps().forEach(step -> {
                TextArea stepField = createDynamicTextArea(step.toString());
                stepField.setWrapText(true);
                stepsBox.getChildren().add(stepField);
            });
        } else {
            TextArea emptyStepField = createDynamicTextArea("");
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
    public StringBuilder collectFields(StringBuilder commands, Map<String, String> changedValues) {
        // Check if the name has been changed and append the name prefix and value.
        if (changedValues.containsKey("name")) {
            commands.append(" n/");
            commands.append(changedValues.get("name"));
        }

        // Check if the duration has been changed and append the duration prefix and value.
        if (changedValues.containsKey("duration")) {
            commands.append(" d/");
            commands.append(changedValues.get("duration"));
        }

        // Check if the portion has been changed and append the portion prefix and value.
        if (changedValues.containsKey("portion")) {
            commands.append(" p/");
            commands.append(changedValues.get("portion"));
        }

        // Check if the ingredients have been changed and append the ingredients prefix and value.
        if (changedValues.containsKey("ingredients")) {
            String[] ingredients = changedValues.get("ingredients").split(", ");
            for (String ingredient : ingredients) {
                commands.append(" i/");
                commands.append(ingredient);
            }
        }

        // Check if the steps have been changed and append the steps prefix and value.
        if (changedValues.containsKey("steps")) {
            String[] steps = changedValues.get("steps").split(", ");
            for (String step : steps) {
                commands.append(" s/");
                commands.append(step);
            }
        }

        // Check if the tags have been changed and append the tags prefix and value.
        if (changedValues.containsKey("tags")) {
            String[] tags = changedValues.get("tags").split(", ");
            for (String tag : tags) {
                commands.append(" t/");
                commands.append(tag);
            }
        }
        return commands;
    }

    /**
     * Handles the add recipe event by adding the recipe with the changed values.
     *
     * @param changedValues A map of the changed recipe fields with keys as field names and values as the new data.
     */
    private void handleAddRecipeEvent(Map<String, String> changedValues) {
        try {
            StringBuilder commands = new StringBuilder();
            commands = collectFields(commands, changedValues);
            String commandText = "add " + commands.toString();
            executeCommand(commandText);
        } catch (CommandException | ParseException e) {
            logger.info("Failed to edit recipe: ");
        }
    }

    /**
     * Handles the edit recipe event by updating the recipe with the changed values.
     *
     * @param index The index of the recipe to be edited.
     * @param changedValues A map of the changed recipe fields with keys as field names and values as the new data.
     */
    private void handleEditRecipeEvent(int index, Map<String, String> changedValues) {
        try {
            StringBuilder commands = new StringBuilder();

            // Add the index of the item to edit.
            commands.append(index);
            commands = collectFields(commands, changedValues);
            String commandText = "edit " + commands.toString();

            executeCommand(commandText);
        } catch (CommandException | ParseException e) {
            logger.info("Failed to edit recipe: " + index);
        }
    }

    /**
     * Returns the next TextArea below the current TextArea within the same parent VBox, if any.
     *
     * @param currentTextArea The current TextArea.
     * @return The next TextArea below the current TextArea or null if there's no TextArea below it.
     */
    private TextArea getNextTextArea(TextArea currentTextArea) {
        VBox parentBox = (VBox) currentTextArea.getParent();
        int currentIndex = parentBox.getChildren().indexOf(currentTextArea);
        int lastIndex = parentBox.getChildren().size() - 1;

        if (currentIndex < lastIndex) {
            Node nextNode = parentBox.getChildren().get(currentIndex + 1);
            if (nextNode instanceof TextArea) {
                return (TextArea) nextNode;
            }
        }

        return null;
    }

    /**
     * Creates a dynamic TextArea with the specified initial text.
     * The TextArea will support UP, DOWN, and TAB navigation.
     * If the TextArea is the last in the VBox and gains focus, a new empty TextArea will be added below it.
     * If the TextArea loses focus and is the last in the VBox and empty, it will be removed.
     *
     * @param text The initial text for the TextArea.
     * @return The created dynamic TextArea.
     */
    private TextArea createDynamicTextArea(String text) {
        //Styling
        TextArea textArea = new TextArea(text);
        textArea.setWrapText(true);
        textArea.setMaxHeight(5.0);
        textArea.setPrefHeight(5.0);

        //Keyboard listener for navigation
        textArea.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            int currentIndex = ((VBox) textArea.getParent()).getChildren().indexOf(textArea);

            if (event.getCode() == KeyCode.UP) {
                // Condition 1: UP key pressed
                if (currentIndex > 0) {
                    TextInputControl prevField = (TextInputControl) ((VBox) textArea.getParent())
                                                                       .getChildren()
                                                                       .get(currentIndex - 1);
                    prevField.requestFocus();
                }
            } else if (event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.TAB) {
                TextArea nextField = (TextArea) ((VBox) textArea.getParent()).getChildren().get(currentIndex + 1);

                // >>> Should be removed - arrow keys are for navigation WITHIN text field
                // Condition 2.1: DOWN key pressed
                if (event.getCode() == KeyCode.DOWN) {
                    nextField.requestFocus();

                // Condition 2.2: Purely TAB key pressed
                } else if (event.getCode() == KeyCode.TAB && !event.isShiftDown()) {
                    // If it is a new placeholder row and there's another TextArea after it, skip to the field after
                    if (nextField.getText().isEmpty()
                            && currentIndex + 2
                                < ((VBox) textArea.getParent()).getChildren().size()) {
                        nextField = (TextArea) ((VBox) textArea.getParent())
                                .getChildren()
                                .get(currentIndex + 2);
                    }
                    nextField.requestFocus();
                // Shift + TAB
                } else if (event.getCode() == KeyCode.TAB) {
                    ObservableList<Node> childList = ((VBox) textArea.getParent()).getChildren();
                    if (textArea.getText().isEmpty()) {
                        childList.remove(textArea);
                    } else {
                        int index = childList.indexOf(textArea);
                        if (index > 0) {
                            childList.get(index - 1).requestFocus();
                        }
                    }
                }
                event.consume();
            }
        });

        //Text field listener for automatically adding/removing new input rows
        textArea.focusedProperty().addListener((observable, oldValue, newValue) -> {
            VBox parentBox = (VBox) textArea.getParent();
            if (parentBox == null) {
                return;
            }
            int lastIndex = parentBox.getChildren().size() - 1;

            // Check if the TextArea has gained focus
            if (newValue) {
                // Check if it's the last TextArea in the VBox
                if (parentBox.getChildren().indexOf(textArea) == lastIndex) {
                    TextArea newField = createDynamicTextArea("");
                    parentBox.getChildren().add(newField);
                }
            } else {
                // Check if it's the last TextArea, it's empty, and the focus is not in the same VBox, then remove it
                if (getNextTextArea(textArea) != null
                        && getNextTextArea(textArea).getText().isEmpty()
                        && textArea.getText().isEmpty()) {
                    parentBox.getChildren().remove(getNextTextArea(textArea));
                }
            }
        });

        return textArea;
    }

    /**
     * Executes the command based on the given {@code commandText} and returns the result.
     * Updates the UI components based on the command result.
     *
     * @param commandText the command text to execute.
     * @return the resulting {@code CommandResult} after executing the command.
     * @throws CommandException if the command execution fails.
     * @throws ParseException if the command text cannot be parsed.
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = commandExecutor.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            throw e;
        }
    }
}
