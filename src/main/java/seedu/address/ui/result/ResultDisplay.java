package seedu.address.ui.result;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FavoriteCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.TabCommand;
import seedu.address.logic.commands.UnfavoriteCommand;
import seedu.address.ui.UiPart;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class ResultDisplay extends UiPart<Region> {

    private static final String FXML = "result/ResultDisplay.fxml";
    private static final List<String> KEYWORDS = List.of(AddCommand.COMMAND_WORD, ClearCommand.COMMAND_WORD,
            DeleteCommand.COMMAND_WORD, EditCommand.COMMAND_WORD, ExitCommand.COMMAND_WORD,
            FindCommand.COMMAND_WORD, HelpCommand.COMMAND_WORD, ListCommand.COMMAND_WORD,
            UnfavoriteCommand.COMMAND_WORD, FavoriteCommand.COMMAND_WORD, TabCommand.COMMAND_WORD,
            "Parameters", "Example");

    @FXML
    private VBox resultDisplayContainer;
    @FXML
    private Label resultDisplayLabel;

    /**
     * Creates a {@code ResultDisplay} with placeholder text.
     */
    public ResultDisplay() {
        super(FXML);
        resultDisplayLabel.setText("Enter command below");
    }

    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        FeedbackExtractor extractor = new FeedbackExtractor(feedbackToUser);

        ObservableList<Node> nodes = resultDisplayContainer.getChildren();
        nodes.clear();
        nodes.add(resultDisplayLabel);

        Iterator<String> keywords = KEYWORDS.iterator();
        Iterator<String> extractedStrings = KEYWORDS.stream()
                .map(keyword -> keyword + ':')
                .map(extractor::extractPart)
                .iterator();

        while (keywords.hasNext() && extractedStrings.hasNext()) {
            String title = keywords.next();
            String body = extractedStrings.next();
            if (body != null) {
                ResultDisplayCard card = new ResultDisplayCard(title, body);
                nodes.add(card.getRoot());
            }
        }
        resultDisplayLabel.setText(extractor.getLeftoverFeedback());
    }

    /**
     * Helper class to extract parts from the feedback string.
     */
    private static class FeedbackExtractor {
        private String feedback;

        public FeedbackExtractor(String feedback) {
            requireNonNull(feedback);
            this.feedback = feedback.trim();
        }

        public String getLeftoverFeedback() {
            return feedback;
        }

        public String extractPart(String keyword) {
            return extractPart(keyword, '\n');
        }

        /**
         * Returns part of the feedback, starting from
         * the first occurrence of {@code keyword} (excluded) to
         * the last character {@code endChar} (excluded).
         *
         * For example, {@code extractPart("parameters:", '\n')} on the string "Parameters: INDEX\n"
         * will return "INDEX".
         *
         * Additionally, {@code keyword} and the corresponding part extracted
         * will be spliced from the feedback string.
         *
         * @param keyword Keyword to search for, case-insensitive.
         * @param endChar Character representing the end of the search.
         * @return The extracted string if {@code keyword} exists and is not blank;
         *         {@code null} otherwise.
         */
        public String extractPart(String keyword, char endChar) {
            requireNonNull(keyword);
            if (keyword.isBlank()) {
                return null;
            }

            int startIndex = feedback.toLowerCase().indexOf(keyword.toLowerCase());
            if (startIndex < 0) {
                return null;
            }

            String extractedFeedback;
            String remainingFeedback = feedback.substring(0, startIndex).trim();
            startIndex += keyword.length();

            int endIndex = feedback.indexOf(endChar, startIndex);
            if (endIndex < 0) {
                extractedFeedback = feedback.substring(startIndex).trim();
            } else {
                extractedFeedback = feedback.substring(startIndex, endIndex).trim();
                remainingFeedback += endChar + feedback.substring(endIndex).trim();
            }

            feedback = remainingFeedback;
            return extractedFeedback.isBlank() ? null : extractedFeedback;
        }
    }
}
