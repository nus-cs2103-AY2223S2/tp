package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Person;

/**
 * An UI component that displays appointment information of a {@code Person}.
 */
public class AppointmentCard extends UiPart<Region> {
    private static final String FXML = "AppointmentListCard.fxml";

    public final Appointment appointment;

    public final Person person;

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */
    @FXML
    private HBox cardPane;
    @FXML
    private Label id;

    @FXML
    private Label nric;
    @FXML
    private Label booking;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public AppointmentCard(Person person, Appointment appointment, int displayedIndex) {
        super(FXML);
        this.person = person;
        this.appointment = appointment;

        setLabels(person, appointment, displayedIndex);
    }

    private void setLabels(Person person, Appointment appointment, int displayedIndex) {
        id.setText(displayedIndex + ". ");
        if (person.isPatient()) {
            nric.setText(appointment.getDrNric().nric);
        } else {
            nric.setText(appointment.getPatientNric().nric);
        }
        booking.setText(appointment.getBooking().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AppointmentCard)) {
            return false;
        }

        // state check
        AppointmentCard card = (AppointmentCard) other;
        return id.getText().equals(card.id.getText())
                && appointment.equals(card.appointment);
    }
}
