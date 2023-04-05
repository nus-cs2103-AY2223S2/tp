package mycelium.mycelium.ui.commandbox;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import mycelium.mycelium.ui.UiPart;
import mycelium.mycelium.ui.commandbox.mode.Mode;
import mycelium.mycelium.ui.commandbox.mode.Mode.ModeType;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {
    public static final String ERROR_STYLE_CLASS = "error";
    public static final String LISTENING_STYLE_CLASS = "listening";
    private static final String FXML = "CommandBox.fxml";

    private Mode mode;

    @FXML
    private TextField commandTextField;

    /**
     * Creates a {@code CommandBox} with an initial mode.
     */
    public CommandBox(Mode mode) {
        super(FXML);
        this.mode = requireNonNull(mode);
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    public void handleInputSubmit() {
        Optional<Mode> nextMode = mode.onInputSubmit(commandTextField.getText());
        if (nextMode.isPresent()) {
            setMode(nextMode.get());
        }
    }

    @FXML
    private void handleInputChange() {
        mode.onInputChange(commandTextField.getText());
    }

    /**
     * Sets the command box style to use the default style.
     */
    public void setStyleDefault() {
        commandTextField.getStyleClass().remove(LISTENING_STYLE_CLASS);
        commandTextField.getStyleClass().remove(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the command box style to use the error style.
     */
    public void setStyleError() {
        ObservableList<String> styleClass = commandTextField.getStyleClass();

        if (styleClass.contains(ERROR_STYLE_CLASS)) {
            return;
        }

        styleClass.add(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the command box style to use the listening style.
     */
    public void setStyleListening() {
        ObservableList<String> styleClass = commandTextField.getStyleClass();

        if (styleClass.contains(LISTENING_STYLE_CLASS)) {
            return;
        }

        styleClass.add(LISTENING_STYLE_CLASS);
    }

    /**
     * Requests focus on the command box.
     *
     * If the command box is already focused, this method does nothing.
     * Otherwise, the command box will be focused and the cursor will be moved to the end of the line.
     */
    public void requestFocus() {
        if (commandTextField.isFocused()) {
            return;
        }
        commandTextField.requestFocus();
        moveToEndOfLine();
    }

    /**
     * Moves the cursor to the end of the command box.
     */
    public void moveToEndOfLine() {
        commandTextField.positionCaret(commandTextField.getLength());
    }

    /**
     * Moves the cursor to the start of the command box.
     */
    public void moveToStartOfLine() {
        commandTextField.positionCaret(0);
    }

    /**
     * Sets the input of the command box.
     *
     * @param input the input to set
     */
    public void setInput(String input) {
        commandTextField.setText(input);
        moveToEndOfLine();
    }

    /**
     * Appends the given string to the end of the command box.
     * The appended string is also selected.
     *
     * @param additive the string to append
     */
    public void appendHighlighted(String additive) {
        int start = commandTextField.getLength();
        commandTextField.setText(commandTextField.getText() + additive);
        commandTextField.positionCaret(start);
        commandTextField.selectEnd();
    }

    /**
     * Gets the input of the command box.
     *
     * @return the input of the command box
     */
    public String getInput() {
        return commandTextField.getText();
    }

    /**
     * Sets the mode of the command box.
     *
     * @param mode the mode to set
     */
    public void setMode(Mode mode) {
        String prevInput = getInput();
        this.mode.teardownMode();
        this.mode = mode;
        this.mode.setupMode(prevInput);
    }

    /**
     * Gets the mode type of the command box.
     *
     * @return the mode type of the command box
     */
    public ModeType getModeType() {
        return mode.getModeType();
    }
}
