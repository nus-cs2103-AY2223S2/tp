package seedu.internship.ui;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.internship.logic.commands.CommandResult;
import seedu.internship.logic.commands.exceptions.CommandException;
import seedu.internship.logic.parser.exceptions.ParseException;
import seedu.internship.storage.CommandHistory;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;
    private final CommandHistory commandHistory;

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
        this.commandHistory = new CommandHistory();
        // Reused from
        // https://github.com/AY2122S2-CS2103T-W13-3/tp/blob/master/src/main/java/seedu/address/ui/CommandBox.java
        this.commandTextField.addEventFilter(KeyEvent.KEY_PRESSED, this::handleUpDownButtonEvent);
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

        this.commandHistory.addInput(commandText);

        try {
            commandExecutor.execute(commandText);
            commandTextField.setText("");
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    @FXML
    private void handleUpDownButtonEvent(KeyEvent event) {
        // Reused from
        // https://github.com/AY2122S2-CS2103T-W13-3/tp/blob/master/src/main/java/seedu/address/ui/CommandBox.java
        if (event.getCode().isArrowKey()) {
            switch (event.getCode()) {
            case UP:
                String olderInput = this.commandHistory.getOlderInput();
                commandTextField.setText(olderInput);
                // Reused from
                // https://stackoverflow.com/q/8293774
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        commandTextField.end();
                    }
                });
                break;

            case DOWN:
                commandTextField.setText(this.commandHistory.getNewerInput());
                break;

            default:
                break;
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
         * @see seedu.internship.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }

}
