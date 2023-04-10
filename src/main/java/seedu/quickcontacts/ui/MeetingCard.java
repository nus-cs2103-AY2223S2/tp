package seedu.quickcontacts.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.quickcontacts.model.meeting.Meeting;

/**
 * An UI component that displays information of a {@code Meeting}.
 */
public class MeetingCard extends UiPart<Region> {
    public static final String ERROR_STYLE_CLASS = "error";
    public static final String DONE_STYLE_CLASS = "done";
    private static final String FXML = "MeetingListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */
    public final Meeting meeting;
    @FXML
    private Label id;
    @FXML
    private Label title;
    @FXML
    private Label dateTime;
    @FXML
    private Label attendees;
    @FXML
    private Label meetingLocation;
    @FXML
    private Label description;

    /**
     * Creates a {@code MeetingCard} with the given {@code Meeting} and index to display.
     */
    public MeetingCard(Meeting meeting, int displayedIndex) {
        super(FXML);
        this.meeting = meeting;
        id.setText(displayedIndex + ". ");
        title.setText(meeting.getTitle().toString());
        dateTime.setText(meeting.getDateTime().toString());
        if (meeting.getAttendees().size() > 0) {
            attendees.setText(
                "Attendees: "
                    + meeting.getAttendees()
                    .stream()
                    .map(person -> person.getName().toString())
                    .reduce((s, s2) -> s + "," + s2)
                    .get());
        }
        if (meeting.getLocation() != null) {
            meetingLocation.setText("Location: " + meeting.getLocation().toString());
        }
        if (meeting.getDescription() != null) {
            description.setText("Description: " + meeting.getDescription().toString());
        }
        formatDateTimePast();
        formatIsDone();
    }

    private void formatIsDone() {
        if (meeting.getIsCompleted()) {
            title.getStyleClass().add(DONE_STYLE_CLASS);
        } else {
            title.getStyleClass().remove(DONE_STYLE_CLASS);
        }
    }

    /**
     * Updates the font colour of the dateTime depending on whether the meeting is over.
     * If the meeting has passed based on the current system date/time, the font will be RED.
     * Else it will be WHITE by default.
     */
    public void formatDateTimePast() {
        if (meeting.hasPassed()) {
            dateTime.getStyleClass().add(ERROR_STYLE_CLASS);
        } else {
            dateTime.getStyleClass().remove(ERROR_STYLE_CLASS);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MeetingCard)) {
            return false;
        }

        // state check
        MeetingCard card = (MeetingCard) other;
        return meeting.equals(card.meeting);
    }
}
