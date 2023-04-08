package fasttrack.ui;

import fasttrack.logic.commands.CommandResult;
import fasttrack.logic.commands.exceptions.CommandException;
import fasttrack.logic.parser.exceptions.ParseException;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import javafx.stage.Window;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;

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
        initialiseAutocompleteHandler();
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
     * Returns the current text property of the command text field
     * @return the text property of the command text field
     */
    public StringProperty getTextProperty() {
        return commandTextField.textProperty();
    }

    /**
     * Gives back focus to the command text field and positions the cursor at the end of the text.
     */
    public void setFocus() {
        commandTextField.requestFocus();
        commandTextField.positionCaret(commandTextField.getText().length());
    }

    /**
     * Updates the command text field with the given category name by
     * replacing the text after "c/" with the category name.
     * @param categoryName The name of the category to be inserted into the command text field.
     */
    public void updateCommandBoxText(String categoryName) {
        String currentText = commandTextField.getText();
        int inputIndex = currentText.indexOf("c/") + 2;
        String updatedString;
        updatedString = currentText.substring(0, inputIndex) + categoryName;
        commandTextField.setText(updatedString + " ");
    }

    /**
     * Adds a key press event filter to the command text field that listens for the UP arrow key.
     * When the up arrow key is pressed, focus is given to the suggestion list if it is visible.
     */
    private void initialiseAutocompleteHandler() {
        commandTextField.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            Window mainStage = commandTextField.getScene().getWindow();
            Node suggestionsList = mainStage.getScene().lookup("#suggestionListView");
            if (event.getCode() == KeyCode.UP && suggestionsList.isVisible()) {
                suggestionsList.requestFocus();
            }
            if (event.getCode() == KeyCode.TAB) {
                // simulate UP key press
                KeyEvent upEvent = new KeyEvent(
                    KeyEvent.KEY_PRESSED,
                    "",
                    "",
                    KeyCode.UP,
                    false,
                    false,
                    false,
                    false);
                commandTextField.fireEvent(upEvent);
                // simulate ENTER key press
                KeyEvent enterEvent = new KeyEvent(
                    KeyEvent.KEY_PRESSED,
                    "",
                    "", KeyCode.ENTER,
                    false,
                    false,
                    false,
                    false);
                suggestionsList.fireEvent(enterEvent);
                event.consume();
            }
        });
    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see fasttrack.logic.Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }

}
