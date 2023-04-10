package seedu.address.ui;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.CommandRecommendationEngine;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.exceptions.RecommendationException;
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
    private TextField commandRecommendationTextField;

    private final CommandRecommendationEngine commandRecommendationEngine;

    private final Logger logger = LogsCenter.getLogger(CommandBox.class);

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     *
     * @param commandExecutor             Function that can execute commands.
     */
    public CommandBox(CommandExecutor commandExecutor, CommandRecommendationEngine commandRecommendationEngine) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        this.commandRecommendationEngine = commandRecommendationEngine;

        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty()
                .addListener((observable, oldValue, newValue) -> {
                    setStyleToDefault();
                    setRecommendationsWithUserInput(newValue);
                });

        commandTextField.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            handleCaret(event.getSource());
        });

        commandTextField.addEventHandler(KeyEvent.ANY, (e) -> {
            if (isOverflow()) {
                commandRecommendationTextField.setText("");
            } else {
                handleCaret(e.getSource());
            }
        });

        commandTextField.addEventFilter(KeyEvent.KEY_PRESSED, (e) -> {
            if (e.getCode() != KeyCode.TAB) {
                return;
            }
            TextField field = (TextField) e.getSource();
            String userInput = field.getText();

            try {
                String autocompletedCommand = commandRecommendationEngine.autocompleteCommand(userInput);
                commandTextField.setText(autocompletedCommand);
                commandTextField.end(); // shift caret to end
                setRecommendationsWithUserInput(commandTextField.getText());
                e.consume();
            } catch (RecommendationException ce) {
                setStyleToIndicateCommandFailure(true);
                commandRecommendationTextField.setText("");
                e.consume();
            }
        });

        commandRecommendationTextField.setEditable(false);
    }

    private void handleCaret(Object event) {
        TextField field = (TextField) event;
        String text = field.getText();
        int caretPos = field.getCaretPosition();
        if (text.trim().length() > caretPos) {
            commandRecommendationTextField.setText("");
        } else {
            setRecommendationsWithUserInput(text);
        }
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
            setStyleToIndicateCommandFailure(false);
        }
    }

    /**
     * Sets the command box style to use the default style.
     */
    private void setStyleToDefault() {
        commandTextField.getStyleClass().remove(ERROR_STYLE_CLASS);
        commandRecommendationTextField.getStyleClass().remove(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the command box style to indicate a failed command.
     */
    private void setStyleToIndicateCommandFailure(boolean setAutoCompleteToError) {
        ObservableList<String> styleClass = commandTextField.getStyleClass();
        ObservableList<String> recommendationTextFieldStyleClass = commandRecommendationTextField.getStyleClass();

        if (!styleClass.contains(ERROR_STYLE_CLASS)) {
            styleClass.add(ERROR_STYLE_CLASS);
        }

        if (setAutoCompleteToError && !recommendationTextFieldStyleClass.contains(ERROR_STYLE_CLASS)) {
            recommendationTextFieldStyleClass.add(ERROR_STYLE_CLASS);
        }
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
     * Updates the command recommendation text field.
     */
    private void setRecommendationsWithUserInput(String userInput) {
        try {
            String recommendedText = commandRecommendationEngine.generateCommandRecommendations(userInput);
            if (isOverflow()) {
                commandRecommendationTextField.setText("");
            } else {
                commandRecommendationTextField.setText(recommendedText);
            }
        } catch (RecommendationException e) {
            setStyleToIndicateCommandFailure(true);
            commandRecommendationTextField.setText("");

        }
    }

    private boolean isOverflow() {
        Text text = new Text();
        text.setFont(commandTextField.getFont());
        text.setText(commandTextField.getText() + "                   ");
        return commandTextField.getWidth() < text.getLayoutBounds().getWidth();
    }
}
