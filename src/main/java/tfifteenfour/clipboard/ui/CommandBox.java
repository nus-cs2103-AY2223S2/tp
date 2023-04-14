package tfifteenfour.clipboard.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import tfifteenfour.clipboard.logic.commands.CommandResult;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.logic.parser.exceptions.ParseException;


/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;
    private final List<String> commandHistory = new ArrayList<>();
    private int commandHistoryIndex = -1;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
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

        // Add the command to the history
        commandHistory.add(0, commandText);
        commandHistoryIndex = -1;

        try {
            commandExecutor.execute(commandText);
            commandTextField.setText("");
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    /**
     * Handles key press event.
     * @param event Key press event.
     */
    @FXML
    private void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.DOWN) {
            // Navigate to the previous command in the history
            if (commandHistoryIndex >= 0) {
                commandHistoryIndex--;
                if (commandHistoryIndex >= 0 && commandHistoryIndex < commandHistory.size()) {
                    commandTextField.setText(commandHistory.get(commandHistoryIndex));
                    commandTextField.positionCaret(commandTextField.getText().length());
                } else {
                    commandHistoryIndex = -1;
                    commandTextField.setText("");
                }
            }
        } else if (event.getCode() == KeyCode.UP) {
            // Navigate to the next command in the history
            if (commandHistoryIndex < commandHistory.size() - 1) {
                commandHistoryIndex++;
                commandTextField.setText(commandHistory.get(commandHistoryIndex));
                commandTextField.positionCaret(commandTextField.getText().length());
            } else if (commandHistoryIndex == commandHistory.size() - 1) {
                commandTextField.positionCaret(commandTextField.getText().length());
            }
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
         * @see tfifteenfour.clipboard.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }

}
