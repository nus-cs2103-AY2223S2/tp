package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.recommendation.Recommendation;

/**
 * An UI component that displays information of a {@code Recommendation}.
 */
public class MeetCard extends UiPart<Region> {
    private static final String FXML = "MeetListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on EduMate level 4</a>
     */

    public final Recommendation recommendation;

    @FXML
    private HBox cardPane;
    @FXML
    private Label place;
    @FXML
    private Label id;

    @FXML
    private FlowPane time;

    /**
     * Creates a {@code Recommendation} with the given {@code Recommendation} and index to display.
     */
    public MeetCard(Recommendation recommendation) {
        super(FXML);
        this.recommendation = recommendation;
        id.setText(recommendation.getContactIndex().toString() + ". ");
        place.setText(recommendation.getLocation().getName() + " : " + recommendation.getTimePeriod().getUiDisplay());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MeetCard)) {
            return false;
        }

        // state check
        MeetCard card = (MeetCard) other;
        return id.getText().equals(card.id.getText())
                && recommendation.equals(card.recommendation);
    }
}
