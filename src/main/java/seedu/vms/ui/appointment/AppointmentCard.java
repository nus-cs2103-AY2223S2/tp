package seedu.vms.ui.appointment;

import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.vms.model.IdData;
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
    @FXML
    private Label vaccine;

    /**
     * Constructs a {@code AppointmentCard}.
     *
     * @param data - the appointment to display.
     */
    public AppointmentCard(IdData<Appointment> data) {
        super(FXML_FILE);
        Appointment appointment = data.getValue();
        titleLabel.setText(String.format("#%04d", appointment
                .getPatient()
                .getOneBased()));
        appointmentTime.setText(appointment.getAppointmentTime()
                .format(DateTimeFormatter.ofPattern("h:mm a MMM d yyyy"))
                + " ~ "
                + appointment.getAppointmentEndTime()
                .format(DateTimeFormatter.ofPattern("h:mm a MMM d yyyy")));
        vaccine.setText(appointment.getVaccination().toString());
    }
}

