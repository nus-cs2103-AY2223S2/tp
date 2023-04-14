package seedu.powercards.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.powercards.model.card.Card;
import seedu.powercards.model.tag.Tag;


/**
 * A UI component that displays information of an unflipped {@code Card} under review.
 */
public class UnflippedReviewElement extends UiPart<Region> {
    private static final String EMPTY_STRING = "";
    private static final String FXML = "UnflippedReviewElement.fxml";

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
     * Creates a {@code PersonCode} with the given {@code Card} and index to display.
     */
    public UnflippedReviewElement(Card card) {
        super(FXML);
        this.card = card;

        question.setText(card.getQuestion().question);

        answer.setText(EMPTY_STRING);

        if (!card.getTag().tagName.equals(Tag.TagName.UNTAGGED)) {
            tags.getChildren().add(new CardElement.CardTag(card.getTagName()));
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
