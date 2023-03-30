package seedu.address.ui.person;

import java.io.FileNotFoundException;
import java.util.function.Consumer;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandGroup;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.person.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;
import seedu.address.ui.HelpWindow;
import seedu.address.ui.MainWindow;
import seedu.address.ui.UiPart;
import seedu.address.ui.main.CommandBox;
import seedu.address.ui.main.ResultDisplay;
import seedu.address.ui.main.StatusBarFooter;

/**
 * Displays contact list.
 */
public class AddressBookWindow extends UiPart<Stage> {

    private static final String FXML = "AddressBookWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(getClass());
    private final Consumer<Person> selectHandler;
    private final HelpWindow helpWindow;
    private Stage primaryStage;
    private Logic logic;

    private ResultDisplay resultDisplay;
    private PersonListPanel personListPanel;

    @FXML
    private StackPane commandBoxPlaceholder;
    @FXML
    private StackPane resultDisplayPlaceholder;
    @FXML
    private StackPane personListPanelPlaceholder;
    @FXML
    private StackPane statusbarPlaceholder;

    /**
     * Creates a {@code AddressBookWindow} with the given {@code Stage} and {@code Logic}.
     */
    public AddressBookWindow(Stage primaryStage, Logic logic) {
        this(primaryStage, logic, (person) -> {});
    }

    /**
     * Creates a {@code AddressBookWindow} with the given {@code Stage} and
     * {@code Logic} with a select handler.
     */
    public AddressBookWindow(Stage primaryStage, Logic logic, Consumer<Person> selectHandler) {
        super(FXML, primaryStage);
        this.helpWindow = new HelpWindow();

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;
        this.selectHandler = selectHandler;
    }

    /**
     * Creates a {@code AddressBookWindow} with the given {@code Stage} and
     * {@code Logic} with a select handler and {@code HelpWindow}.
     */
    public AddressBookWindow(Stage primaryStage, Logic logic,
                             Consumer<Person> selectHandler,
                             HelpWindow helpWindow) {
        super(FXML, primaryStage);
        this.helpWindow = helpWindow;

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;
        this.selectHandler = selectHandler;
    }

    /**
     * Show address book window.
     */
    public void show() {
        logger.fine("Showing address book page");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the address book window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the address book window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the address book window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Opens the help window or focuses on it if it's already opened.
     */
    public void handleHelp() {
        if (!helpWindow.isShowing()) {
            helpWindow.show();
            logger.info("Opened help window.");
        } else {
            helpWindow.focus();
        }
    }

    /**
     * Fills inner parts and contents in all placeholders in the window.
     */
    public void fillInnerParts() {
        personListPanel = new PersonListPanel(logic.getFilteredPersonList(), (person) -> {
            selectHandler.accept(person);
        }, personIndex -> {
            try {
                logic.execute(new DeleteCommand(personIndex));
            } catch (ParseException | CommandException e) {
                logger.warning(e.getMessage());
            } catch (FileNotFoundException e) {
                logger.warning(e.getMessage());
            }
        });
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());
    }

    @FXML
    private void handleExit() {
        primaryStage.hide();
    }

    /**
     * Executes the command and returns the result.
     *
     * @see seedu.address.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText)
            throws CommandException, ParseException, FileNotFoundException {
        try {
            CommandResult commandResult = logic.execute(commandText,
                    g -> g.equals(CommandGroup.PERSON) || g.equals(CommandGroup.GENERAL));
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            if (commandResult.isExit()) {
                handleExit();
            }

            if (commandResult.isShowHelp()) {
                handleHelp();
            }
            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        } catch (FileNotFoundException e) {
            logger.info("File not found: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());
            throw e;
        }
    }

}
