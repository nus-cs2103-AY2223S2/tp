package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.client.Client;

/**
 * A UI component that displays information of a {@code Appointment}.
 */
public class AppointmentLabel extends UiPart<Region> {

    private static final String FXML = "AppointmentLabel.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Client client;

    @FXML
    private Label appointmentName;
    @FXML
    private Label appointmentMeetUpDate;

    /**
     * Creates a {@code ClientCode} with the given {@code Client} and index to display.
     */
    public AppointmentLabel(Client client) {
        super(FXML);
        this.client = client;
        appointmentName.setText(client.getAppointment().getAppointmentName().value);
        appointmentMeetUpDate.setText(client.getAppointment().getMeetupDate().getDisplayString());
    }

    /**
     * Creates an empty AppointmentLabel
     */
    public AppointmentLabel() {
        super(FXML);
        this.client = null;
        appointmentName.setText("");
        appointmentMeetUpDate.setText("");
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AppointmentLabel)) {
            return false;
        }

        // state check
        AppointmentLabel card = (AppointmentLabel) other;
        return appointmentName.getText().equals(card.appointmentName.getText())
            && client.equals(card.client);
    }
}
