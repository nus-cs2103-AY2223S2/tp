package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.ReadOnlyPatientList;
import seedu.address.model.appointment.Appointment;

/**
 * UI component that displays information of an {@code Appointment}.
 */
public class AppointmentCard extends UiPart<Region> {
    private static final String FXML = "AppointmentCard.fxml";
    public final Appointment appointment;

    @FXML
    private HBox appointmentCardPane;
    @FXML
    private Label id;
    @FXML
    private Label patientName;

    @FXML
    private Label timeSlot;

    @FXML
    private Label description;

    @FXML
    private Label doctor;

    /**
     * Creates an {@code AppointmentCard} with the given {@code Appointment} and index to display.
     */
    public AppointmentCard(ReadOnlyPatientList patientList, Appointment appointment, int displayedIndex) {
        super(FXML);
        this.appointment = appointment;
        //System.out.println(appointment.toString());
        id.setText(displayedIndex + ". ");
        /*
        Patient thisPatient = patientList
                .getPatientList()
                .stream()
                .filter(patient -> patient.getId().equals(appointment.getPatientId()))
                .findFirst().orElseThrow();
        */
        // temporary workaround for buggy id
        patientName.setText(appointment.getPatientName().toString());
        timeSlot.setText(appointment.getTimeslot().toString());
        description.setText(appointment.getDescription().toString());
        doctor.setText("Doctor: " + appointment.getDoctor().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PatientCard)) {
            return false;
        }

        // state check
        AppointmentCard card = (AppointmentCard) other;
        return id.getText().equals(card.id.getText())
                && appointment.equals(card.appointment);
    }
}
