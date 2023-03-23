package mycelium.mycelium.ui.commandbox;

import static java.util.Objects.requireNonNull;

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
    private void handleCommandEntered() {
        mode.onInputSubmit(commandTextField.getText());
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

    public void setInput(String input) {
        commandTextField.setText(input);
        commandTextField.positionCaret(input.length());
    }

    public String getInput() {
        return commandTextField.getText();
    }

    public void setMode(Mode mode) {
        this.mode.teardownMode();
        this.mode = mode;
        this.mode.setupMode(getInput());
    }

    public ModeType getModeType() {
        return mode.getModeType();
    }
}
