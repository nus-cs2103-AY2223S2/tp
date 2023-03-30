package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.AutocompleteEngine;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;
    private final AutocompleteEngine autocompleteEngine;

    @FXML
    private TextField commandTextField;

    @FXML
    private TextField commandSuggestionTextField;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());

        //@@author EvitanRelta-reused
        // Reused from https://github.com/AY2223S1-CS2103T-T12-2/tp
        commandTextField.textProperty()
                .addListener((observable, oldValue, newValue) -> updateCommandSuggestion(newValue));
        commandTextField.addEventFilter(KeyEvent.KEY_PRESSED, event -> handleKeyPressed(event));

        commandSuggestionTextField.setEditable(false);
        commandSuggestionTextField.setFocusTraversable(false);
        commandSuggestionTextField.setMouseTransparent(true);
        autocompleteEngine = new AutocompleteEngine();
        //@@author
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        String commandText = commandTextField.getText();
        if (commandText.isEmpty()) {
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

    //@@author EvitanRelta-reused
    // Reused from https://github.com/AY2223S1-CS2103T-T12-2/tp
    /**
     * Auto-completes user input when the user presses the Tab key.
     */
    public void handleKeyPressed(KeyEvent e) {
        if (e.getCode() != KeyCode.TAB) {
            return;
        }
        String userInput = commandTextField.getText();
        String commandSuggestion = commandSuggestionTextField.getText();
        String autocompletedCommand = autocompleteEngine.autocompleteCommand(userInput, commandSuggestion);
        if (!autocompletedCommand.isEmpty()) {
            commandTextField.setText(autocompletedCommand);
            commandTextField.end();
        }
        updateCommandSuggestion(commandTextField.getText());
        e.consume();
    }

    /**
     * Updates the command suggestion text field.
     */
    private void updateCommandSuggestion(String commandText) {
        if (commandText.isEmpty() || isOverflow()) {
            commandSuggestionTextField.setText("");
            return;
        }
        try {
            commandSuggestionTextField.setText(autocompleteEngine.suggestCommand(commandText));
            commandSuggestionTextField.positionCaret(commandTextField.getText().length());
        } catch (CommandException e) {
            commandSuggestionTextField.setText(commandText);
            setStyleToIndicateCommandFailure();
        }
    }

    /**
     * Checks if the command text field is overflowing.
     * @return true if the command text field is overflowing.
     */
    public boolean isOverflow() {
        Text t = new Text(commandTextField.getText() + "12345"); // `12345` is used to pad the text
        t.setFont(commandTextField.getFont());
        double width = t.getLayoutBounds().getWidth();
        return width > commandTextField.getWidth();
    }
    //@@author
}
