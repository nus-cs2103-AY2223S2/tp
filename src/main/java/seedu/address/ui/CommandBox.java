package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.CommandHistory;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;
    private final CommandHistory commandHistory;

    private String prevCommand = "";

    @FXML
    private TextField commandTextField;

    @FXML
    private Label contextLabel;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        this.commandHistory = new CommandHistory();
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
    }

    public void setContextLabel(String text) {
        contextLabel.setText(text);
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        String commandText = commandTextField.getText();
        if (commandText.equals("")) {
            return;
        }

        try {
            commandExecutor.execute(commandText);
            // Push the previous popped command (caused by 'UP' arrow key event)
            // back into the previous command stack
            // if it is not the same as the previous command in stack to prevent duplicates
            if (!prevCommand.equals(commandHistory.peekPrevious())) {
                commandHistory.pushToPrevious(prevCommand);
            }
            prevCommand = commandText;
            commandHistory.pushAllNextToPrevious();
            commandHistory.pushToPrevious(commandText);
            commandTextField.setText("");
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    /**
     * Handles the key pressed event.
     * @param event
     */
    @FXML
    private void handleKeyPress(KeyEvent event) {
        String keyString = event.getCode().toString();
        handleOnKeyUpAndDownPress(keyString);
    }

    /**
     * Handles the up and down arrow keys pressed event.
     * @param keyString
     */
    private void handleOnKeyUpAndDownPress(String keyString) {
        String curCommand = commandTextField.getText();

        // If the user presses the up arrow key, we want to retrieve the previous command
        if (keyString.equals("UP")) {
            // If previousCommandStack is empty, do nothing
            if (commandHistory.isPreviousEmpty()) {
                return;
            }
            // add current command to nextCommandStack
            commandHistory.pushToNext(curCommand);
            // pop the previous command and set it as the current command
            String previousCommand = commandHistory.popFromPrevious();
            commandTextField.setText(previousCommand);
            this.prevCommand = previousCommand;
            commandTextField.positionCaret(previousCommand.length());
        }
        if (keyString.equals("DOWN")) {
            // If nextCommandStack is empty, do nothing
            if (commandHistory.isNextEmpty()) {
                return;
            }
            // add current command to previousCommandStack if it is not empty
            if (!curCommand.equals("")) {
                commandHistory.pushToPrevious(curCommand);
            }
            // pop the next command and set it as the current command
            String nextCommand = commandHistory.popFromNext();
            commandTextField.setText(nextCommand);
            this.prevCommand = nextCommand;
            commandTextField.positionCaret(nextCommand.length());
        }
    }

    /**
     * Sets the command box style to use the default style.
     */
    private void setStyleToDefault() {
        commandTextField.getStyleClass().remove(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the command box style to indicate a failed command.
     */
    private void setStyleToIndicateCommandFailure() {
        ObservableList<String> styleClass = commandTextField.getStyleClass();

        if (styleClass.contains(ERROR_STYLE_CLASS)) {
            return;
        }

        styleClass.add(ERROR_STYLE_CLASS);
    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see seedu.address.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }

}
