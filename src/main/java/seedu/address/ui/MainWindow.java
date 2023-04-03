package seedu.address.ui;

import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.files.FilesManager;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;

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
    private PersonListPanel personListPanel;
    private ResultDisplay resultDisplay;
    private HelpWindow helpWindow;
    private DetailDisplay detailDisplay;
    private FileList fileList;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane personListPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private StackPane detailDisplayPlaceholder;
    @FXML
    private FileCard fileCard;

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

        setAccelerators();

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
    public void fillInnerParts() {
        personListPanel = new PersonListPanel(logic.getFilteredPersonList(), this, this::executeCommand);
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getAddressBookFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        //fileList = new FileList(logic.getFilteredPersonList(), this);
        detailDisplay = new DetailDisplay(this::executeCommand, personListPanel.getPersonListView());
        detailDisplayPlaceholder.getChildren().add(detailDisplay.getRoot());

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

    public void show() {
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

    public PersonListPanel getPersonListPanel() {
        return personListPanel;
    }

    /**
     * Executes the command and returns the result.
     *
     * @see Logic#execute(String)
     */
    protected CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            String commandWord = commandText.trim().split("\\s+")[0].toLowerCase();
            if (Arrays.asList("clear", "delete", "deletes", "deletefile").contains(commandWord)) {
                boolean proceedWithCommand = showWarningDialog(commandWord);
                if (!proceedWithCommand) {
                    return new CommandResult("Operation cancelled.");
                }
            }
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            //added to avoid clearing when doing view operations
            if (!commandWord.contains("view")) {
                //@@author lxz333-reused
                //Reused from https://github.com/AY2223S1-CS2103T-T17-1/tp/tree/master/src/main/java/seedu/address/ui
                // with minor modifications
                detailDisplay.clearDetailDisplay();
                detailDisplay.hideAppointmentButton();
                detailDisplay.hideUploadButton();
                detailDisplay.hideGenerateButton();
                detailDisplay.hideViewDisplay();
            }

            if (commandResult.hasPersonToShow()) {
                Person personToShow = commandResult.getPersonToShow();
                assert personToShow != null;
                FilesManager filesManager = new FilesManager(personToShow);
                detailDisplay.setInfo(personToShow, new ObservableFile(filesManager.getFileNames(), personToShow)
                        .getObservableFileList());
                detailDisplay.setUploadButton(filesManager);
                detailDisplay.setGenerateButton(filesManager);
                detailDisplay.showAppointmentButton();
                detailDisplay.showGenerateButton();
                detailDisplay.showUploadButton();
                detailDisplay.showViewDisplay();
            }
            //@@author

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

    /**
     * Execute the input command when click on the person card in the personListPanel.
     *
     * @param commandText Input command.
     * @throws CommandException If an error occurs during execution of the command.
     * @throws ParseException If a parse error occurs during execution of the command.
     */
    public void handleClickInPersonListPanel(String commandText) throws CommandException, ParseException {
        this.executeCommand(commandText);
    }

    public DetailDisplay getDetailDisplay() {
        return detailDisplay;
    }

    private boolean showWarningDialog(String command) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        //Create the OK and Cancel buttons with the event filter
        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButtonType, cancelButtonType);

        Button okButton = (Button) alert.getDialogPane().lookupButton(okButtonType);
        Button cancelButton = (Button) alert.getDialogPane().lookupButton(cancelButtonType);

        //Consume the Enter key event when the focus is on the OK or Cancel buttons
        okButton.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                event.consume();
                alert.setResult(okButtonType);
                alert.hide();
            }
        });

        cancelButton.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                event.consume();
                alert.setResult(cancelButtonType);
                alert.hide();
            }
        });
        if (command.equalsIgnoreCase("clear")) {
            alert.setTitle("Warning");
            alert.setHeaderText("Clear Patient List");
            alert.setContentText("This operation will clear the Patient List. Are you sure you want to proceed?");
        } else if (command.equalsIgnoreCase("delete")) {
            alert.setTitle("Warning");
            alert.setHeaderText("Delete Entry");
            alert.setContentText("This operation will delete a patient. Are you sure you want to proceed?");
        } else if (command.equalsIgnoreCase("deletes")) {
            alert.setTitle("Warning");
            alert.setHeaderText("Delete Multiple Entries");
            alert.setContentText("This operation will delete multiple patients. Are you sure you want to proceed?");
        } else if (command.equalsIgnoreCase("deletefile")) {
            alert.setTitle("Warning");
            alert.setHeaderText("Delete File");
            alert.setContentText("This operation will delete a file. Are you sure you want to proceed?");
        } else {
            return true;
        }

        //Show the alert and wait for user's response
        Optional<ButtonType> result = alert.showAndWait();

        //Return true if the user confirms the operation, false otherwise
        return result.orElse(cancelButtonType) == okButtonType;
    }
}
