package seedu.powercards.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.powercards.model.deck.Deck;

/**
 * An UI component that displays information of a {@code Deck}.
 */

public class DeckElement extends UiPart<Region> {
    private static final String FXML = "DeckElement.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on Deck level 4</a>
     */

    public final Deck deck;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;

    /**
     * Creates a {@code DeckCode} with the given {@code deck} and index to display.
     */

    public DeckElement(Deck deck, int displayedIndex) {
        super(FXML);
        this.deck = deck;
        id.setText(displayedIndex + ". ");
        name.setText(deck.getDeckName());

        if (deck.isSelected()) {
            this.getRoot().setStyle("-fx-background-color: #007aff");
            this.id.setStyle("-fx-text-fill: white");
            this.name.setStyle("-fx-text-fill: white");
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeckElement)) {
            return false;
        }

        // state check
        DeckElement deck = (DeckElement) other;
        return id.getText().equals(deck.id.getText())
                && this.deck.equals(deck.deck);
    }
}
