package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Meeting;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class MeetingCard extends UiPart<Region> {

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
    private HBox cardPane;
    @FXML
    private Label meetingTitle;
    @FXML
    private Label id;
    @FXML
    private Label person;
    @FXML
    private Label date;
    @FXML
    private Label start;
    @FXML
    private Label end;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public MeetingCard(Meeting meeting, int displayedIndex) {
        super(FXML);
        this.meeting = meeting;
        // id.setText(displayedIndex + ". ");
        // person.setText(meeting.().fullName);
        // date.setText(meeting.getAddress().getRegionString());
        // start.setText(meeting.getPhone().value);
        // end.setText(meeting.getAddress().value);
        // meeting.getTags().stream()
        //         .sorted(Comparator.comparing(tag -> tag.tagName))
        //         .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        meetingTitle.setText("Test Meeting");
        id.setText(1 + ". ");
        person.setText("Bobby");
        date.setText("15/03/2023");
        start.setText("1500");
        end.setText("1900");
    }

    // @Override
    // public boolean equals(Object other) {
    //     // short circuit if same object
    //     if (other == this) {
    //         return true;
    //     }

    //     // instanceof handles nulls
    //     if (!(other instanceof PersonCard)) {
    //         return false;
    //     }

    //     // state check
    //     PersonCard card = (PersonCard) other;
    //     return id.getText().equals(card.id.getText())
    //             && person.equals(card.person);
    // }
}
