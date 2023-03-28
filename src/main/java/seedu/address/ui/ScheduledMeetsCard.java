package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.meetup.MeetUp;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class ScheduledMeetsCard extends UiPart<Region> {

    private static final String FXML = "ScheduledMeetsListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on EduMate level 4</a>
     */

    public final MeetUp meetUp;

    @FXML
    private HBox cardPane;
    @FXML
    private Label loc;
    @FXML
    private Label id;

    @FXML
    private Label time;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public ScheduledMeetsCard(MeetUp meetUp) {
        super(FXML);
        this.meetUp = meetUp;
        id.setText(meetUp.getMeetUpIndex() + ". ");
        loc.setText(meetUp.getLocation().getName());
        time.setText(meetUp.getTimePeriod().getUiDisplay());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ScheduledMeetsCard)) {
            return false;
        }

        // state check
        ScheduledMeetsCard card = (ScheduledMeetsCard) other;
        return id.getText().equals(card.id.getText())
                && meetUp.equals(card.meetUp);
    }
}

