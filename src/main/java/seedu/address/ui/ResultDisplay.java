package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.ui.body.address.PersonDetailCard;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class ResultDisplay extends UiPart<Region> {

    private static final String FXML = "ResultDisplay.fxml";

    @FXML
    private VBox resultDisplayContainer;

    /**
     * Creates a {@code ResultDisplay} with placeholder text.
     */
    public ResultDisplay() {
        super(FXML);
        resultDisplayContainer.getChildren().add(getFeedbackLabel("Enter command below"));
    }

    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        FeedbackExtractor parameters = new FeedbackExtractor(feedbackToUser, "parameters:");
        FeedbackExtractor example = parameters.fromRemainingPart("example:");

        ObservableList<Node> nodes = resultDisplayContainer.getChildren();
        nodes.clear();
        nodes.add(getFeedbackLabel(example.getRemainingPart()));
        if (parameters.hasExtractedPart()) {
            // TODO: replace with custom card
            PersonDetailCard card = new PersonDetailCard(new PersonDetailCard.DetailCardData("Parameters",
                    parameters.getExtractedPart()));
            nodes.add(card.getRoot());
        }
        if (example.hasExtractedPart()) {
            // TODO: replace with custom card
            PersonDetailCard card = new PersonDetailCard(new PersonDetailCard.DetailCardData("Example",
                    example.getExtractedPart()));
            nodes.add(card.getRoot());
        }
    }

    private Label getFeedbackLabel(String remainingFeedback) {
        Label label = new Label(remainingFeedback);
        label.getStyleClass().add("label-p");
        return label;
    }

    private static class FeedbackExtractor {
        private final String keyword;
        private String extractedPart;
        private String remainingPart;

        public FeedbackExtractor(String feedback, String keyword) {
            requireNonNull(feedback);
            requireNonNull(keyword);
            this.keyword = keyword;
            this.extractedPart = null;
            this.remainingPart = feedback;

            if (keyword.isBlank()) {
                return;
            }

            int startIndex = feedback.toLowerCase().indexOf(keyword.toLowerCase());
            if (startIndex < 0) {
                return;
            }
            remainingPart = feedback.substring(0, startIndex).trim();
            startIndex += keyword.length();

            int endIndex = feedback.indexOf('\n', startIndex);
            if (endIndex < 0) {
                extractedPart = feedback.substring(startIndex).trim();
            } else {
                extractedPart = feedback.substring(startIndex, endIndex).trim();
                remainingPart += " " + feedback.substring(endIndex).trim();
            }
        }

        public boolean hasExtractedPart() {
            return extractedPart != null && !extractedPart.isBlank();
        }

        public String getExtractedPart() {
            return extractedPart;
        }

        public String getRemainingPart() {
            return remainingPart;
        }

        public FeedbackExtractor fromRemainingPart(String keyword) {
            return new FeedbackExtractor(getRemainingPart(), keyword);
        }
    }
}
