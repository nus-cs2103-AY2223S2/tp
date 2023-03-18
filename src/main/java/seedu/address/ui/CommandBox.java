package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.AutocompleteResult;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.command.CommandHistory;
import seedu.address.model.command.exceptions.OutOfBoundsCommandHistoryException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;
    private final CommandInputSuggester commandInputSuggester;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor, CommandInputSuggester commandInputSuggester) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        this.commandInputSuggester = commandInputSuggester;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
        commandTextField.addEventFilter(KeyEvent.KEY_PRESSED, new KeyPressedHandler());
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
            commandTextField.setText("");
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
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

    /**
     * Represents a function that can suggest inputs.
     */
    @FunctionalInterface
    public interface CommandInputSuggester {
        /**
         * Suggest prefixes based on current text.
         *
         * @see seedu.address.logic.Logic#autocomplete(String)
         */
        AutocompleteResult suggest(String commandText);
    }

    /**
     * Represents the handler for {@code KeyCode.UP} and {@code KeyCode.DOWN} key-presses.
     */
    public class KeyPressedHandler implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent event) {
            KeyCode key = event.getCode();

            switch (key) {
            case UP:
                if (commandTextField.getText().equals("")) {
                    CommandHistory.setLast();
                }

                try {
                    commandTextField.setStyle("-fx-display-caret: false;");
                    commandTextField.setText(CommandHistory.prev());
                } catch (OutOfBoundsCommandHistoryException e) {
                    // Nothing to do
                } finally {
                    commandTextField.end();
                    commandTextField.setStyle("-fx-display-caret: true;");
                }
                break;
            case DOWN:
                try {
                    commandTextField.setText(CommandHistory.next());
                } catch (OutOfBoundsCommandHistoryException e) {
                    commandTextField.setText("");
                } finally {
                    commandTextField.end();
                }
                break;
            case TAB:
                try {
                    AutocompleteResult suggestion = commandInputSuggester.suggest(commandTextField.getText());
                    String currInput = commandTextField.getText().trim();

                    if (suggestion.getPrefix().isEmpty()) {
                        break;
                    }

                    String prefix = suggestion.getPrefix().get().getPrefix();

                    if (!suggestion.isReplaceCurrent()) {
                        commandTextField.setText(currInput + " " + prefix);
                        break;
                    }

                    // Replaces current prefix
                    String[] currInputSplit = currInput.split(" ");
                    if (currInputSplit[currInputSplit.length - 1].endsWith("/")) {
                        currInputSplit[currInputSplit.length - 1] = prefix;
                        commandTextField.setText(String.join(" ", currInputSplit));
                    } else {
                        commandTextField.setText(currInput + " " + prefix);
                    }
                } finally {
                    event.consume();
                    commandTextField.end();
                }
                break;
            default:
                break;
            }
        }
    }

}
