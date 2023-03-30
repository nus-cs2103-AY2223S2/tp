package seedu.vms.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import seedu.vms.commons.util.StringUtil;
import seedu.vms.logic.CommandMessage;


/** A GUI representation of a command message to the user. */
public class ResultMessageBox extends UiPart<Region> {
    private static final String FXML_FILE = "ResultMessageBox.fxml";

    private static final String STYLE_CLASS_DEATH = "result-message-death-color";
    private static final String STYLE_CLASS_ERROR = "result-message-error-color";
    private static final String STYLE_CLASS_WARNING = "result-message-warning-color";
    private static final String STYLE_CLASS_INFO = "result-message-info-color";

    @FXML private Label stateLabel;
    @FXML private TextArea messageArea;


    /**
     * Constructs a {@code ResultMessageBox}.
     */
    public ResultMessageBox(CommandMessage result) {
        super(FXML_FILE);
        setStateLabel(result.getState().toString());
        setMessage(result.getMessage());
        setStyle(result.getState());
    }


    private void setStateLabel(String state) {
        stateLabel.setText(String.format("[%s]", state));
    }


    private void setMessage(String message) {
        String displayMessage = StringUtil.wrapText(message, 180);
        // listener block adapted from https://stackoverflow.com/a/25643696 and modified
        messageArea.textProperty().addListener((ob, oldText, newText) -> {
            Platform.runLater(() -> {
                Text text = new Text(newText);
                text.setFont(messageArea.getFont());
                double height = text.getLayoutBounds().getHeight() * 1.07
                        + messageArea.getPadding().getTop() + messageArea.getPadding().getBottom()
                        + 14D;
                messageArea.setPrefHeight(height);
            });
        });
        messageArea.setText(displayMessage);
    }


    private void setStyle(CommandMessage.State state) {
        String colorStyleClass;
        switch (state) {

        case ERROR:
            colorStyleClass = STYLE_CLASS_ERROR;
            break;

        case WARNING:
            colorStyleClass = STYLE_CLASS_WARNING;
            break;

        case DEATH:
            colorStyleClass = STYLE_CLASS_DEATH;
            break;

        default:
            colorStyleClass = STYLE_CLASS_INFO;
            break;

        }
        stateLabel.getStyleClass().add(colorStyleClass);
        messageArea.getStyleClass().add(colorStyleClass);
    }
}
