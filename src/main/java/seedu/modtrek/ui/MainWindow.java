package seedu.modtrek.ui;

import java.util.logging.Logger;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import seedu.modtrek.commons.core.GuiSettings;
import seedu.modtrek.commons.core.LogsCenter;
import seedu.modtrek.logic.Logic;
import seedu.modtrek.logic.commands.CommandResult;
import seedu.modtrek.logic.commands.exceptions.CommandException;
import seedu.modtrek.logic.parser.exceptions.ParseException;
import seedu.modtrek.ui.clisection.CliSection;
import seedu.modtrek.ui.resultssection.ResultsSection;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private CliSection cliSection;
    private ResultsSection resultsSection;

    @FXML
    private VBox resultsSectionPlaceholder;

    @FXML
    private VBox cliSectionPlaceholder;

    /* add more... */

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        resultsSection = new ResultsSection(logic.getFilteredModuleList());
        resultsSectionPlaceholder.getChildren().add(resultsSection.getRoot());

        cliSection = new CliSection(this::executeCommand);
        cliSectionPlaceholder.getChildren().add(cliSection.getRoot());
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
        PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
        delay.setOnFinished(event -> primaryStage.hide());
        delay.play();
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.modtrek.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            // To refresh the graphics section to display updated list of modules
            resultsSection.displayAllModules(logic.getFilteredModuleList());

            if (commandResult.isExit()) {
                handleExit();
            }

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            throw e;
        }
    }
}
