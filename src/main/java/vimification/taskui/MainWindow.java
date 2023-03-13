package vimification.taskui;


import javafx.fxml.FXML;
// import javafx.scene.control.MenuItem;
// import javafx.scene.control.TextInputControl;
// import javafx.scene.input.KeyCombination;
// import javafx.scene.input.KeyEvent;
// import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import vimification.logic.Logic;


/**
 * The Main Window. Provides the basic application layout containing a menu bar and space where
 * other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private TaskListPanel taskListPanel;
    // private ResultDisplay resultDisplay;
    // private HelpWindow helpWindow;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        // setWindowDefaultSize(logic.getGuiSettings());
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        // taskListPanel = new TaskListPanel();

        // personListPanelPlaceholder.getChildren().add(taskListPanel.getRoot());

        // resultDisplay = new ResultDisplay();
        // resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        // StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        // statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        // CommandBox commandBox = new CommandBox(this::executeCommand);
        // commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        // GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(),
        // primaryStage.getHeight(),
        // (int) primaryStage.getX(), (int) primaryStage.getY());
        // logic.setGuiSettings(guiSettings);
        // helpWindow.hide();
        // primaryStage.hide();
    }

    public TaskListPanel getTaskListPanel() {
        return taskListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see vimification.logic.Logic#execute(String)
     */
    private void executeCommand(String commandText) {
        // throws CommandException, ParseException {
        // try {
        // CommandResult commandResult = logic.execute(commandText);
        // logger.info("Result: " + commandResult.getFeedbackToUser());
        // resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

        // if (commandResult.isShowHelp()) {
        // handleHelp();
        // }

        // if (commandResult.isExit()) {
        // handleExit();
        // }

        // return commandResult;
        // } catch (CommandException | ParseException e) {
        // logger.info("Invalid command: " + commandText);
        // resultDisplay.setFeedbackToUser(e.getMessage());
        // throw e;
        // }
    }

    void show() {
        primaryStage.show();
    }
}
