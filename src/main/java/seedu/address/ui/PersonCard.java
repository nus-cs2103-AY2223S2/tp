package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.tutee.Tutee;

/**
 * An UI component that displays information of a {@code Tutee}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on
     * TuteeManagingSystem level 4</a>
     */

    public final Tutee tutee;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label subject;
    @FXML
    private Label schedule;
    @FXML
    private Label startTime;
    @FXML
    private Label endTime;
    @FXML
    private Label remark;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane lessons;

    /**
     * Creates a {@code PersonCode} with the given {@code Tutee} and index to display.
     */
    public PersonCard(Tutee tutee, int displayedIndex) {
        super(FXML);
        this.tutee = tutee;
        id.setText(displayedIndex + ". ");
        name.setText(tutee.getName().toString());
        phone.setText(tutee.getPhone().toString());
        address.setText(tutee.getAddress().toString());
        email.setText(tutee.getEmail().toString());
        subject.setText(tutee.getSubject().toString());
        schedule.setText(tutee.getSchedule().toString());
        startTime.setText(tutee.getStartTime().toString());
        endTime.setText(tutee.getEndTime().toString());
        remark.setText(tutee.getRemark().toString());
        tutee.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        tutee.getLessons().stream()
                .forEach(lesson -> lessons.getChildren().add(new Label(lesson)));
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
        return id.getText().equals(card.id.getText())
                && tutee.equals(card.tutee);
    }
}
