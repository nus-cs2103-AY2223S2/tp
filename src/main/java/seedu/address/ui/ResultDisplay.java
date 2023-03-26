package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class ResultDisplay extends UiPart<Region> {

    private static final String FXML = "ResultDisplay.fxml";

    @FXML
    private TextFlow resultDisplay;

    /**
     * Creates ResultDisplay ui with initial messages
     */
    public ResultDisplay() {
        super(FXML);
        Text welcomeText = new Text("Welcome to AutoM8!");
        welcomeText.getStyleClass().add("welcome-text");
        Text initViewText = new Text("Currently listing all Customers");
        initViewText.getStyleClass().add("text");

        resultDisplay.getChildren().add(welcomeText);
        resultDisplay.getChildren().add(new Text(System.lineSeparator()));
        resultDisplay.getChildren().add(initViewText);
    }

    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        resultDisplay.getChildren().clear();
        Text feedbackText = new Text(feedbackToUser);
        feedbackText.getStyleClass().add("text");
        resultDisplay.getChildren().add(feedbackText);
    }

}
