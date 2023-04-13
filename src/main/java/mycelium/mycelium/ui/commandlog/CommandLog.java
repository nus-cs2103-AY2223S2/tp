package mycelium.mycelium.ui.commandlog;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import mycelium.mycelium.ui.UiPart;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class CommandLog extends UiPart<Region> {

    private static final String FXML = "CommandLog.fxml";

    @FXML
    private TextArea resultDisplay;

    public CommandLog() {
        super(FXML);
    }

    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        resultDisplay.setText(feedbackToUser);
    }
}
