package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import seedu.address.logic.commands.CommandRecommendationEngine;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";

    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;

    @FXML
    private TextField commandTextField;

    @FXML
    private TextField commandSuggestionTextField;

    private final CommandRecommendationEngine commandSuggestor;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty()
                .addListener((unused1, unused2, unused3) -> setStyleToDefault());
        commandTextField.textProperty()
                .addListener((observable, oldValue, newValue) -> updateStringAutoCompletion(newValue));
        commandTextField.addEventFilter(KeyEvent.KEY_PRESSED, this::handleKeyPressed);

        commandSuggestionTextField.setEditable(false);
        commandSuggestionTextField.setFocusTraversable(false);
        commandSuggestionTextField.setMouseTransparent(true);
        commandSuggestor = new CommandRecommendationEngine();
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
     * Auto-completes user input when the user presses the Tab key.
     */
    public void handleKeyPressed(KeyEvent e) {
        try {
            String userInput = commandTextField.getText();
            if (e.getCode() == KeyCode.TAB) {
                String commandSuggestion = commandSuggestor.recommendCommand(userInput);
                String autocompletedCommand = commandSuggestor.autocompleteCommand(userInput, commandSuggestion);
                if (!autocompletedCommand.equals("")) {
                    commandTextField.setText(autocompletedCommand);
                    commandTextField.end();
                }
                updateStringAutoCompletion(commandTextField.getText());
                e.consume();
            }
        } catch (CommandException ce) {
            setStyleToIndicateCommandFailure();
            e.consume();
        }
    }

    /**
     * Updates the command suggestion text field.
     */
    private void updateStringAutoCompletion(String commandText) {
        if (isEmpty() || isOverflow()) {
            commandSuggestionTextField.setText("");
            return;
        }
        try {
            commandSuggestionTextField.setText(commandSuggestor.recommendCommand(commandText));
            commandSuggestionTextField.positionCaret(commandTextField.getText().length());
        } catch (CommandException e) {
            commandSuggestionTextField.setText(commandText);
            setStyleToIndicateCommandFailure();
        }
    }

    private boolean isOverflow() {
        String check = commandTextField.getText();
        Text text = new Text(check);
        text.setFont(commandTextField.getFont());
        text.setText(commandTextField.getText());
        double textWidth = text.getLayoutBounds().getWidth();

        return commandTextField.getWidth() < textWidth;
    }


    private boolean isEmpty() {
        String check = commandTextField.getText();
        return check.isEmpty();
    }

}
