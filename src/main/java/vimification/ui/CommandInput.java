package vimification.ui;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import vimification.internal.Logic;
import vimification.internal.command.CommandResult;

/**
 * Panel containing the input of a command.
 */
public class CommandInput extends UiPart<HBox> {

    private static final String FXML = "CommandInput.fxml";
    private MainScreen mainScreen;
    private Logic logic;

    @FXML
    private TextField inputField;

    /**
     * Constructor for CommandInput.
     *
     * @param mainScreen the main screen of the application
     * @param logic the logic of the application
     */
    public CommandInput(MainScreen mainScreen, Logic logic) {
        super(FXML);
        this.mainScreen = mainScreen;
        this.logic = logic;
        inputField.prefWidthProperty().bind(this.getRoot().widthProperty());
        inputField.prefHeightProperty().bind(this.getRoot().heightProperty());
    }

    /**
     * Listener for handling all keyboard events to Vimification.
     *
     * @param event
     */
    @FXML
    private void handleKeyPressed(KeyEvent event) {

        boolean isEnterEvent = event.getCode().equals(KeyCode.ENTER);
        boolean isEscEvent = event.getCode().equals(KeyCode.ESCAPE);

        if (isEscEvent || isTextFieldEmpty()) {
            mainScreen.clearBottomComponent();
            returnFocusToTaskListPanel();
        }

        if (isEnterEvent) {
            String commandString = inputField.getText();
            executeCommand(commandString);
        }

    }

    /**
     * Cleans the command string by removing the colon and any leading or trailing whitespace.
     *
     * @param commandString the command string to be cleaned
     * @return the cleaned command string
     */
    private String cleanCommandString(String commandString) {
        boolean isCommandHasColon = commandString.startsWith(":");
        if (!isCommandHasColon) {
            return commandString;
        }
        String strippedCommandString = commandString.substring(1).strip();
        return strippedCommandString;
    }

    /**
     * Executes the command string.
     *
     * @param input the command string to be executed
     */
    private void executeCommand(String input) {

        String commandString = cleanCommandString(input);
        // System.out.println("Your command is " + input);
        CommandResult result = logic.execute(commandString);
        mainScreen.loadCommandResultComponent(result);
        returnFocusToTaskListPanel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void requestFocus() {
        super.requestFocus();
        inputField.setText(":");
        inputField.positionCaret(1);
        inputField.requestFocus();
    }

    private void returnFocusToTaskListPanel() {
        mainScreen.getTaskListPanel().requestFocus();
    }

    @FXML
    private void initialize() {
        this.getRoot().setFocusTraversable(true);

        // When CommandInput loses focus, clear CommandInput from mainScreen.bottomComponent and
        // return the focus back to TaskListPanel
        ChangeListener<Boolean> onLostFocusListener = (arg0, wasFocused, isCommandInputFocused) -> {
            // bottomComponent may be displaying CommandResult, we do not want to clear
            // CommandResult messages.
            boolean isCommandInputLingering =
                    mainScreen.getBottomComponent().getChildren().contains(this.getRoot());
            if (!isCommandInputFocused && isCommandInputLingering) {
                mainScreen.clearBottomComponent();
                returnFocusToTaskListPanel();
            }
        };

        inputField.focusedProperty().addListener(onLostFocusListener);
    }

    private boolean isTextFieldEmpty() {
        return inputField.getText().equals("");
    }
}
