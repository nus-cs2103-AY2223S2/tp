package seedu.powercards.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.powercards.model.card.Card;
import seedu.powercards.model.tag.Tag;

/**
 * A UI component that displays information of a flipped {@code Card} under review.
 */
public class ReviewElement extends UiPart<Region> {
    private static final String EMPTY_STRING = "";
    private static final String FXML = "ReviewElement.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on Deck level 4</a>
     */

    public final Card card;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label question;
    @FXML
    private Label answer;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code CardCode} with the given {@code Card} and index to display.
     */
    public ReviewElement(Card card) {
        super(FXML);
        this.card = card;

        question.setText(card.getQuestion().question);

        answer.setText(EMPTY_STRING);

        if (!card.getTag().tagName.equals(Tag.TagName.UNTAGGED)) {
            tags.getChildren().add(new CardElement.CardTag(card.getTagName()));
        }

        if (card.isFlipped()) {
            this.getRoot().setStyle("-fx-background-color: #6c68c3;");
            answer.setText(card.getAnswer().answer);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CardElement)) {
            return false;
        }

        // state check
        CardElement card = (CardElement) other;
        return this.card.equals(card.card);
    }
}
