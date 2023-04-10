package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.logic.autocompletion.Autocompletion;
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
    private HBox suggestionsBox;

    private List<String> suggestions;
    private boolean tabPressed;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        this.suggestions = new ArrayList<>();
        this.tabPressed = false;

        suggestionsBox.setVisible(false);
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, oldValue, newValue) -> {
                if (!tabPressed) {
                    handleInputUpdated();
                    setStyleToDefault();
                }
                tabPressed = false;
            }
        );

        commandTextField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.TAB) {
                tabPressed = true;
                cycleSuggestions();
            }
        });
    }

    /**
     * Handles autocompletion feature.
     */
    private void handleInputUpdated() {
        String input = commandTextField.getText();

        suggestions = new ArrayList<>();
        suggestions.add(input);
        List<String> suggestionsToAppend = new Autocompletion(input).getListOfSuggestions();
        for (String suggestionToAppend : suggestionsToAppend) {
            suggestions.add(input + suggestionToAppend);
        }

        suggestionsBox.setVisible(suggestions.size() > 1);
    }

    private void cycleSuggestions() {
        String inputText = commandTextField.getText();
        int index = suggestions.indexOf(inputText);

        String suggestion = "";

        if (index == -1) {
            // Input text is not in the suggestions, select the first suggestion
            if (!suggestions.isEmpty()) {
                suggestion = suggestions.get(0);
            }
        } else {
            // Input text is in the suggestions, select the next suggestion
            index = (index + 1) % suggestions.size();
            suggestion = suggestions.get(index);
        }

        commandTextField.setText(suggestion);
        commandTextField.requestFocus();
        commandTextField.positionCaret(suggestion.length());
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

}
