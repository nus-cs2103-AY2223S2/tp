package seedu.recipe.ui;

import java.io.IOException;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.recipe.commons.core.GuiSettings;
import seedu.recipe.commons.core.LogsCenter;
import seedu.recipe.commons.exceptions.DataConversionException;
import seedu.recipe.commons.exceptions.IllegalValueException;
import seedu.recipe.logic.Logic;
import seedu.recipe.logic.commands.CommandResult;
import seedu.recipe.logic.commands.exceptions.CommandException;
import seedu.recipe.logic.parser.exceptions.ParseException;
import seedu.recipe.model.recipe.Recipe;
import seedu.recipe.storage.ExportManager;
import seedu.recipe.storage.ImportManager;
import seedu.recipe.ui.events.DeleteRecipeEvent;


/**
 * Represents the main window of the application. This class is responsible for
 * initializing and configuring the UI components and managing the primary stage.
 * It also contains methods for handling UI events, such as deleting or editing recipes.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private final Stage primaryStage;
    private final Logic logic;
    private final HelpWindow helpWindow;

    // Independent Ui parts residing in this Ui container
    private RecipeListPanel recipeListPanel;
    private ResultDisplay resultDisplay;
    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private MenuItem importMenuItem;

    @FXML
    private StackPane recipeListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusBarPlaceholder;

    /**
     * Constructor that creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     * Initializes and configures the UI components.
     *
     * @param primaryStage the primary stage for this main window.
     * @param logic the main logic instance of the application.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        assert primaryStage != null : "Primary stage cannot be null";
        assert logic != null : "Logic cannot be null";

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        //setAccelerator(importMenuItem, KeyCombination.valueOf("F2"));

        getRoot().addEventFilter(DeleteRecipeEvent.DELETE_RECIPE_EVENT_TYPE, this::handleDeleteRecipeEvent);

        helpWindow = new HelpWindow();
    }

    /**
     * Returns the primary stage of this main window.
     *
     * @return the primary stage.
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Sets accelerator for the help menu.
     */
    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
        setAccelerator(importMenuItem, KeyCombination.valueOf("F3"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     *
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Handles the import file action for importing an existing Recipe Book JSON file into the app.
     */
    @FXML
    private void handleImport() {
        ImportManager importManager = new ImportManager(primaryStage);
        try {
            ObservableList<Recipe> importedRecipes = importManager.execute();
            if (importedRecipes == null) {
                logger.info("No file selected");
                resultDisplay.setFeedbackToUser("No file selected");
                return;
            }

            // Marked for refactor into separate util class
            // Validate uniqueness
            ObservableList<Recipe> currentRecipes = logic.getFilteredRecipeList();
            for (Recipe recipe : importedRecipes) {
                if (!currentRecipes.stream().anyMatch(recipe::isSameRecipe)) {
                    String commandText = "add" + importManager.getCommandText(recipe);
                    logic.execute(commandText);
                }
            }
            logger.info("Import Successfully");
            resultDisplay.setFeedbackToUser("Import Successfully");
        } catch (DataConversionException | IOException | IllegalValueException | CommandException e) {
            logger.info("Invalid import: " + e.getMessage());
            resultDisplay.setFeedbackToUser(e.getMessage());
        }
    }

    /**
     * Handles the export file action for exporting the current Recipe Book JSON file.
     */
    @FXML
    private void handleExport() {
        ExportManager exportManager = new ExportManager(primaryStage);
        try {
            exportManager.execute();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handles the DeleteRecipeEvent by executing the appropriate delete command
     * based on the provided event data.
     *
     * @param event the DeleteRecipeEvent containing the index of the recipe to be deleted.
     */
    private void handleDeleteRecipeEvent(DeleteRecipeEvent event) {
        int recipeIndex = event.getRecipeIndex();
        try {
            String commandText = "delete " + (recipeIndex);
            executeCommand(commandText);
        } catch (CommandException | ParseException e) {
            logger.info("Failed to delete recipe: " + recipeIndex);
        }
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        recipeListPanel = new RecipeListPanel(logic.getFilteredRecipeList(), this::executeCommand);
        recipeListPanelPlaceholder.getChildren().add(recipeListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getRecipeBookFilePath());
        statusBarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    @FXML
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
        } else {
            helpWindow.focus();
        }
    }
    /**
     * Renders the primary stage of this main window visible.
     */
    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                                                  (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    /**
     * Returns the {@code RecipeListPanel} contained in this main window.
     *
     * @return the {@code RecipeListPanel} instance.
     */
    public RecipeListPanel getRecipeListPanel() {
        return recipeListPanel;
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
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }
}
