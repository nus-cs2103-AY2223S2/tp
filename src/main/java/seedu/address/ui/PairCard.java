package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.pair.Pair;

/**
 * An UI component that displays information of a {@code Pair}.
 */
public class PairCard extends UiPart<Region> {

    private static final String FXML = "PairListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Pair pair;

    @FXML
    private HBox cardPane;
    @FXML
    private Label elderlyName;
    @FXML
    private Label volunteerName;
    @FXML
    private Label id;

    /**
     * Creates a {@code PairCode} with the given {@code Pair} and index to display.
     */
    public PairCard(Pair pair, int displayedIndex) {
        super(FXML);
        this.pair = pair;
        id.setText(displayedIndex + ". ");
        elderlyName.setText(pair.getElderly().getName().fullName);
        volunteerName.setText(pair.getVolunteer().getName().fullName);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PairCard)) {
            return false;
        }

        // state check
        PairCard card = (PairCard) other;
        return id.getText().equals(card.id.getText())
                && pair.equals(card.pair);
    }
}
