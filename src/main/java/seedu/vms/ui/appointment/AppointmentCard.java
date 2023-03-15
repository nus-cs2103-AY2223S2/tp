package seedu.vms.ui.appointment;

import java.time.format.DateTimeFormatter;

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
    private Label idLabel;
    @FXML
    private Label patientId;
    @FXML
    private Label appointmentTime;
    @FXML
    private Label vaccine;

    /**
     * Constructs a {@code AppointmentCard}.
     *
     * @param appointment - the appointment to display.
     * @param id - the id of the appointment to display.
     */
    public AppointmentCard(Appointment appointment, int id) {
        super(FXML_FILE);
        idLabel.setText(String.format("#%04d", id));
        patientId.setText(Integer.toString(appointment.getPatient().getOneBased()));
        appointmentTime.setText(appointment.getAppointmentTime()
                .format(DateTimeFormatter.ofPattern("h:mm a MMM d yyyy"))
                + " ~ "
                + appointment.getAppointmentEndTime()
                .format(DateTimeFormatter.ofPattern("h:mm a MMM d yyyy")));
        vaccine.setText(appointment.getVaccination().toString());
    }
}

