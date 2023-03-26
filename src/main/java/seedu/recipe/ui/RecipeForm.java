package seedu.recipe.ui;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
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
import seedu.recipe.logic.Logic;
import seedu.recipe.logic.commands.CommandResult;
import seedu.recipe.logic.commands.exceptions.CommandException;
import seedu.recipe.logic.parser.exceptions.ParseException;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.ui.CommandBox.CommandExecutor;
import seedu.recipe.ui.events.EditRecipeEvent;

/**
 * Represents the form element for users to edit {@code Recipe}s
 */
public class RecipeForm extends UiPart<Region> {
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

    //Data fields
    private int displayedIndex;
    private Map<String, String> initialValues;
    private Recipe recipe;

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
        } else {
            TextField emptyIngredientField = createDynamicTextField("");
            ingredientsBox.getChildren().add(emptyIngredientField);
            TextField emptyStepField = createDynamicTextField("");
            stepsBox.getChildren().add(emptyStepField);
        }
        assert saveButton != null;
        saveButton.setOnAction(event -> saveRecipe());
        cancelButton.setOnAction(event -> closeForm());
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
            .map(node -> ((TextField) node).getText())
            .collect(Collectors.joining(", ")));

        initialValues.put("steps", stepsBox.getChildren().stream()
            .map(node -> ((TextField) node).getText())
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
                        .orElse("Duration was not added.")
        );
        //Portion
        portionField.setText(
                Optional.ofNullable(recipe.getPortionNullable())
                        .map(Object::toString)
                        .orElse("Portion was not added.")
        );

        //Ingredients
        if (!recipe.getIngredients().isEmpty()) {
            recipe.getIngredients().forEach((ingredient, information) -> {
                TextField ingredientField = new TextField(ingredient.toString());
                ingredientsBox.getChildren().add(ingredientField);
            });
        } else {
            TextField ingredientField = createDynamicTextField("Field is not added.");
            ingredientsBox.getChildren().add(ingredientField);
        }

        //Steps
        if (!recipe.getSteps().isEmpty()) {
            recipe.getSteps().forEach(step -> {
                TextField stepField = createDynamicTextField(step.toString());
                stepsBox.getChildren().add(stepField);
            });
        } else {
            TextField stepField = createDynamicTextField("Field is not added.");
            stepsBox.getChildren().add(stepField);
        }

        //Tags
        if (!recipe.getTags().isEmpty()) {
            tagsField.setText(recipe.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .map(tag -> tag.tagName)
                .collect(Collectors.joining(", ")));
        } else {
            tagsField.setText("Field is not added.");
        }

        storeInitialValues();
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
                    .map(node -> ((TextField) node).getText())
                    .collect(Collectors.joining(", "));
                break;
            case "steps":
                currentValue = stepsBox.getChildren().stream()
                    .map(node -> ((TextField) node).getText())
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
        /*
        ...
        model.saveRecipe(recipe);
        EditRecipeEvent editEvent = new EditRecipeEvent(displayedIndex);
        cardPane.fireEvent(editEvent);
        */
        try {
            System.out.println(changedValues);
            //EditRecipeEvent editEvent = new EditRecipeEvent(displayedIndex, changedValues);
            //handleEditRecipeEvent(editEvent);
            handleEditRecipeEvent(displayedIndex, changedValues);
            closeForm();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handles the EditRecipeEvent by executing the appropriate edit command
     * based on the provided event data. Updates the recipe with the changed values
     * specified in the event.
     *
     * @param event the EditRecipeEvent containing the index of the recipe to be edited
     *              and a map of the changed values.
     */
    private void handleEditRecipeEvent(EditRecipeEvent event) {
        assert event != null : "EditRecipeEvent cannot be null";
        int recipeIndex = event.getRecipeIndex();
        Map<String, String> changedValues = event.getChangedValues();
        try {
            StringBuilder commands = new StringBuilder();

            // Add the index of the item to edit.
            commands.append(recipeIndex);

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
            //I'll add in more fields, but I need to make sure this works first.
            String commandText = "edit " + commands.toString(); // 1-indexed
            System.out.println(commandText);
            executeCommand(commandText);
        } catch (CommandException | ParseException e) {
            logger.info("Failed to edit recipe: " + recipeIndex);
        }
    }

    private void handleEditRecipeEvent(int index, Map<String, String> changedValues ) {
        try {
            StringBuilder commands = new StringBuilder();

            // Add the index of the item to edit.
            commands.append(index);

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
            //I'll add in more fields, but I need to make sure this works first.
            String commandText = "edit " + commands.toString(); // 1-indexed
            System.out.println(commandText);
            executeCommand(commandText);
        } catch (CommandException | ParseException e) {
            logger.info("Failed to edit recipe: " + index);
        }
    }

    private TextField createDynamicTextField(String text) {
        TextField textField = new TextField(text);

        textField.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            int currentIndex = ((VBox) textField.getParent()).getChildren().indexOf(textField);
            switch (event.getCode()) {
            case UP:
                if (currentIndex > 0) {
                    TextField prevField = (TextField) ((VBox) textField.getParent()).getChildren().get(currentIndex - 1);
                    prevField.requestFocus();
                }
                break;
            case DOWN:
                break;
            case TAB:
                TextField nextField = (TextField) ((VBox) textField.getParent()).getChildren().get(currentIndex + 1);
                nextField.requestFocus();
                event.consume();
                break;
            default:
                break;
            }
        });

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
                // Check if any other TextField has gained focus or the focus owner is not a TextField
                Node focusOwner = textField.getScene().getFocusOwner();
                boolean focusChangedToDifferentVBox = focusOwner instanceof TextField && !focusOwner.getParent().equals(parentBox);
                boolean focusChangedToNonTextField = !(focusOwner instanceof TextField);
        
                // Check if it's the last TextField, it's empty, and the focus is not in the same VBox, then remove it
                if (parentBox.getChildren().indexOf(textField) == lastIndex
                        && textField.getText().isEmpty()
                        && (focusChangedToDifferentVBox || focusChangedToNonTextField)) {
                    parentBox.getChildren().remove(textField);
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
        window.setTitle(recipe == null ? "Add Recipe" : "Edit Recipe");
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
            //logger.info("Result: " + commandResult.getFeedbackToUser());
            //resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());
            return commandResult;
        } catch (Exception e) {
            //logger.info("Invalid command: " + commandText);
            //resultDisplay.setFeedbackToUser(e.getMessage());
            System.out.println(e.getMessage());
            throw e;
        }
    }
}
