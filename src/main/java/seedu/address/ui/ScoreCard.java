package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Score}.
 */
public class ScoreCard extends UiPart<Region> {
    private static final String FXML = "ScoreListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX. As
     * a consequence, UI elements' variable names cannot be set to such keywords or an exception
     * will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on
     *      AddressBook level 4</a>
     */

    public final Person score;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label title;
    @FXML
    private Label scoreValue;
    @FXML
    private Label date;

    /**
     * Creates a {@code PersonCode} with the given {@code Task} and index to display.
     */
    public ScoreCard(Person score, int displayedIndex) {
        super(FXML);
        this.score = score;
        id.setText(displayedIndex + ". ");
        title.setText(score.getName().fullName + ": ");
        scoreValue.setText(score.getPhone().value.substring(0, 2));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskCard)) {
            return false;
        }

        // state check
        ScoreCard card = (ScoreCard) other;
        return id.getText().equals(card.id.getText()) && score.equals(card.score);
    }
}
