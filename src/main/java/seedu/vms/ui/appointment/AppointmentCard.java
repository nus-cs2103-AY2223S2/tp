package seedu.vms.ui.appointment;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

import seedu.vms.model.appointment.Appointment;
import seedu.vms.ui.UiPart;


/**
 * Graphical representation of an appointment.
 */
public class AppointmentCard extends UiPart<Region> {
    private static final String FXML_FILE = "AppointmentCard.fxml";

    @FXML
    private Label titleLabel;
    @FXML
    private Label appointmentTime;


    /**
     * Constructs a {@code AppointmentCard}.
     *
     * @param appointment - the appointment to display.
     */
    public AppointmentCard(Appointment appointment) {
        super(FXML_FILE);
        titleLabel.setText("1");
        appointmentTime.setText(appointment.getAppointmentTime().toString());
    }
}

