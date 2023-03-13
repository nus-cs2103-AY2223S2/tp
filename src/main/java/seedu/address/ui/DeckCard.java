package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.deck.Deck;

import java.util.Comparator;

public class DeckCard extends UiPart<Region> {
    private static final String FXML = "DeckListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on Deck level 4</a>
     */

    public final Deck deck;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;

    /**
     * Creates a {@code PersonCode} with the given {@code Card} and index to display.
     */
    public DeckCard(Deck deck, int displayedIndex) {
        super(FXML);
        this.deck = deck;
        id.setText(displayedIndex + ". ");
        name.setText(deck.getDeckName());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeckCard)) {
            return false;
        }

        // state check
        DeckCard deck = (DeckCard) other;
        return id.getText().equals(deck.id.getText())
                && this.deck.equals(deck.deck);
    }
}
