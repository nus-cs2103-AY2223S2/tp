package seedu.address.ui;

import static seedu.address.ui.theme.Theme.constructTheme;

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
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.ui.theme.Theme;
import seedu.address.ui.theme.ThemeException;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());

    private boolean isShowBackup = false;

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private BackupListPanel backupListPanel;
    private PersonListPanel personListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private ViewPane viewPane;
    private Theme theme;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane viewPanePlaceHolder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        GuiSettings guiSettings = logic.getGuiSettings();
        setWindowDefaultSize(logic.getGuiSettings());
        setAccelerators();
        applyTheme(constructTheme(guiSettings.getTheme()));

        helpWindow = new HelpWindow();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     *
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        personListPanel = new PersonListPanel(logic.getFilteredPersonList(), this);
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        if (logic.getAddressBook().getPersonList().size() != 0) {
            viewPane = new ViewPane(logic.getAddressBook().getPersonList().get(0));
            viewPanePlaceHolder.getChildren().add(viewPane.getRoot());
        }

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        applyTheme(Theme.DEFAULT_THEME);
    }

    void newFillInnerParts() {
        personListPanel = new PersonListPanel(logic.getFilteredPersonList(), this);
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());
    }

    void fillInnerPartsBackup() throws IllegalValueException {
        backupListPanel = new BackupListPanel(logic.getBackupList());
        personListPanelPlaceholder.getChildren().add(backupListPanel.getRoot());
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

    void show() {
        primaryStage.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
            (int) primaryStage.getX(), (int) primaryStage.getY(), theme.toString());
        logic.setGuiSettings(guiSettings);
        helpWindow.hide();
        primaryStage.hide();
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException,
                                                                        ParseException, IllegalValueException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());
            String feedback = commandResult.getFeedbackToUser();

            if (commandResult.isShowHelp()) {
                handleHelp();
            }

            if (commandResult.isExit()) {
                handleExit();
            }

            if (commandResult.isShowBackups()) {
                fillInnerPartsBackup();
                isShowBackup = true;
            }

            if (!commandResult.isShowBackups()) {
                newFillInnerParts();
                isShowBackup = false;
            }

            if (commandResult.isShowDark()) {
                applyDarkTheme();
            }

            if (commandResult.isShowLight()) {
                applyLightTheme();
            }

            handleUndoRedo(feedback);

            handleViewPane(feedback, commandText);

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

    public CommandResult execute(String commandText) throws CommandException, IllegalValueException {
        return executeCommand(commandText);
    }

    /**
     * Handles all view pane actions.
     *
     * @param feedback
     * @param commandText
     */
    public void handleViewPane(String feedback, String commandText) {
        viewPanePlaceHolder.getChildren().clear();

        if (feedback.equals(ViewCommand.MESSAGE_VIEW_PERSON_SUCCESS)) {
            personListPanel = new PersonListPanel(logic.getAddressBook().getPersonList(), this);
            personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());
            viewPane = new ViewPane(logic.getFilteredPersonList().get(0));
            viewPanePlaceHolder.getChildren().add(viewPane.getRoot());
            logic.updateFilteredPersonList(x -> true);
        } else if (feedback.startsWith("Edited Person:")) {
            int index = Character.getNumericValue(commandText.charAt(5));
            viewPane = new ViewPane(logic.getAddressBook().getPersonList().get(index - 1));
            viewPanePlaceHolder.getChildren().add(viewPane.getRoot());
        } else if (feedback.startsWith("New person")) {
            int len = logic.getAddressBook().getPersonList().size();
            viewPane = new ViewPane(logic.getAddressBook().getPersonList().get(len - 1));
            viewPanePlaceHolder.getChildren().add(viewPane.getRoot());
        } else if (logic.getAddressBook().getPersonList().size() > 0) {
            viewPane = new ViewPane(logic.getAddressBook().getPersonList().get(0));
            viewPanePlaceHolder.getChildren().add(viewPane.getRoot());
        }
    }

    /**
     * Updates the person list panel after the action 'undo' or 'redo'
     *
     * @param feedback
     */
    public void handleUndoRedo(String feedback) {
        if (feedback.startsWith("Undo") || feedback.startsWith("Redo")) {
            personListPanel = new PersonListPanel(logic.getAddressBook().getPersonList(), this);
            personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());
        }
    }

    private void applyTheme(Theme newTheme) {
        ObservableList<String> uiTheme = primaryStage.getScene().getStylesheets();
        uiTheme.clear();
        try {
            String switchedTheme = newTheme.getTheme();
            uiTheme.add(switchedTheme);
            uiTheme.add(Theme.EXTENSION.getTheme());
            theme = newTheme;
            logger.info(String.format(Theme.SUCCESS_MESSAGE, theme));
        } catch (ThemeException e) {
            logger.info(e.getMessage());
        }
    }

    /**
     * Sets theme to Light Theme.
     */
    @FXML
    public void applyLightTheme() {
        applyTheme(Theme.LIGHT);
        resultDisplay.setFeedbackToUser("Switched to light mode!");
    }

    /**
     * Sets theme to Dark Theme.
     */
    @FXML
    public void applyDarkTheme() {
        applyTheme(Theme.DARK);
        resultDisplay.setFeedbackToUser("Switched to dark mode!");
    }

}
