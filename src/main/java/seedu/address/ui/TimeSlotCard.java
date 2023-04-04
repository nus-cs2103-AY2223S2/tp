package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * An UI component that displays free timeslot of a {@code Person}.
 */
public class TimeSlotCard extends UiPart<Region> {

    private static final String FXML = "TimeSlotListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    //TODO: Update
    public final String timeSlot;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label timeSlotText;

    /**
     * Creates a {@code TimeSlotCode} with the given {@code TimeSlot} and index to display.
     */
    public TimeSlotCard(String timeSlot, int displayedIndex) {
        super(FXML);
        this.timeSlot = timeSlot;
        timeSlotText.setText(timeSlot);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TimeSlotCard)) {
            return false;
        }

        // state check
        TimeSlotCard card = (TimeSlotCard) other;
        return timeSlotText.getText().equals(card.timeSlotText.getText())
                && timeSlot.equals(card.timeSlot);
    }
}
