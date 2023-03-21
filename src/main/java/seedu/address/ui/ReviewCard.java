package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.card.Card;

public class ReviewCard extends UiPart<Region> {
    private static final String FXML = "ReviewCard.fxml";

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
    private Label name;
    @FXML
    private Label address;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Card} and index to display.
     */
    public ReviewCard(Card card) {
        super(FXML);
        this.card = card;
        name.setText(card.getQuestion().question);

        if (card.isFlipped()) {
            address.setText(card.getAnswer().answer);
        } else {
            address.setText("");
        }

        card.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new PersonCard.CardTag(tag.tagName)));
    }

    static class CardTag extends Label {
        public CardTag(String name) {
            super(name);
            switch (name) {
            case "easy":
                setStyle("-fx-background-color:#00FF00;");
                break;
            case "medium":
                setStyle("-fx-background-color:#FFA500;");
                break;
            case "hard":
                setStyle("-fx-background-color:#ff0000;");
                break;
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PersonCard card = (PersonCard) other;
        return this.card.equals(card.card);
    }
}
