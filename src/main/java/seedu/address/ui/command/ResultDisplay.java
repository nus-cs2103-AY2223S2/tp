package seedu.address.ui.command;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import seedu.address.ui.UiPart;

/**
 * A UI for the status bar that is displayed at the header of the application.
 */
public class ResultDisplay extends UiPart<Region> {

    private static final String FXML = "ResultDisplay.fxml";

    @FXML
    private TextArea resultDisplay;

    /**
     * Creates a {@code ResultDisplay} with the given {@code Stage}.
     */
    public ResultDisplay() {
        super(FXML);
    }

    /**
     * Sets the feedback to be displayed.
     *
     * @param feedbackToUser The feedback to be displayed.
     */
    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        resultDisplay.setText(feedbackToUser);
    }
}
