package seedu.recipe.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

/**
 * Represents the UI component for the command results that is displayed within the header of the application.
 */
public class ResultDisplay extends UiPart<Region> {
    private static final String FXML = "ResultDisplay.fxml";

    @FXML
    private TextArea resultDisplay;

    /**
     * Generates an instance of this UI Component.
     */
    public ResultDisplay() {
        super(FXML);
    }

    /**
     * Sets the text value to be displayed back to the user.
     * @param feedbackToUser The result of the command, as retrieved from the backend of the app.
     */
    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        resultDisplay.setText(feedbackToUser);
    }

}
