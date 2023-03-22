package mycelium.mycelium.ui.commandbox;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import mycelium.mycelium.logic.Logic;
import mycelium.mycelium.logic.commands.CommandResult;
import mycelium.mycelium.logic.commands.exceptions.CommandException;
import mycelium.mycelium.logic.parser.exceptions.ParseException;
import mycelium.mycelium.ui.UiPart;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    public static final String LISTENING_STYLE_CLASS = "listening";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;
    private final CommandInputListener commandInputListener;
    private boolean listening;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor, CommandInputListener commandInputListener) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        this.commandInputListener = commandInputListener;
        this.listening = false;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        if (listening) {
            return;
        }
        String commandText = commandTextField.getText();
        if (commandText.equals("")) {
            return;
        }

        try {
            commandExecutor.execute(commandText);
            commandTextField.setText("");
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    @FXML
    private void handleInputChange() {
        if (!listening) {
            return;
        }
        String currentText = commandTextField.getText();
        commandInputListener.onInputChanged(currentText);
    }

    /**
     * Sets the command box style to use the default style.
     */
    private void setStyleToDefault() {
        if (listening) {
            setStyleToIndicateListening();
        } else {
            commandTextField.getStyleClass().remove(LISTENING_STYLE_CLASS);
        }
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
     * Sets the command box style to indicate listening.
     */
    private void setStyleToIndicateListening() {
        ObservableList<String> styleClass = commandTextField.getStyleClass();

        if (styleClass.contains(LISTENING_STYLE_CLASS)) {
            return;
        }

        styleClass.add(LISTENING_STYLE_CLASS);
    }

    /**
     * Toggles listening mode.
     */
    public void toggleListening() {
        listening = !listening;
        setStyleToDefault();
        commandTextField.setText("");
    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }

    /**
     * Represents a function that is called on input change.
     */
    @FunctionalInterface
    public interface CommandInputListener {
        void onInputChanged(String input);
    }
}
