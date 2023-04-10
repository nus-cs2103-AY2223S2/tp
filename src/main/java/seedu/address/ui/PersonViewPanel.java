package seedu.address.ui;

import com.sun.javafx.collections.ObservableListWrapper;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.model.prescription.Prescription;

// Implementation adapted from https://github.com/Charles1026/tp/blob/7f3cc48fb35418f1f6f6f4c1dc5e8a4a037d29d8/src/
// main/java/seedu/address/ui/PersonViewPanel.java
/**
 * Panel containing the detailed info of person.
 */
public class PersonViewPanel extends UiPart<Region> {
    private static final String FXML = "PersonViewPanel.fxml";

    private final Person person;

    private Patient patient;

    @FXML
    private Label name;

    @FXML
    private Label phone;
    @FXML
    private Label email;

    @FXML
    private Label nric;

    @FXML
    private Label address;

    @FXML
    private ListView<Appointment> appointments;

    @FXML
    private VBox prescriptionBox;

    @FXML
    private VBox appointmentBox;

    @FXML
    private VBox particularsBox;

    @FXML
    private Label medication;

    @FXML
    private Label appmtWith;

    /**
     * Generates a Person View Panel.
     * @param person Person to generate the panel about.
     */
    public PersonViewPanel(Person person) {
        super(FXML);
        if (person == null) {
            this.person = null;
            particularsBox.setVisible(false);
            appointmentBox.setVisible(false);
            prescriptionBox.setVisible(false);
            return;
        }

        this.person = person;

        setParticulars(person);
        setAppointmentDetails();
        setMedicationDetails();
    }

    private void setParticulars(Person person) {
        String sname = "";
        sname = getNameWithSalutation(person);
        name.setText(sname);
        phone.setText(person.getPhone().toString());
        email.setText(person.getEmail().toString());
        nric.setText(person.getNric().toString());
        address.setText(person.getAddress().toString());
    }

    private static String getNameWithSalutation(Person person) {
        String sname;
        if (person.isDoctor()) {
            sname = "[Dr] " + person.getName().toString();
        } else {
            sname = person.getName().toString();
        }
        return sname;
    }

    private void setAppointmentDetails() {
        if (person.isPatient()) {
            appmtWith.setText("Doctor");
        } else {
            appmtWith.setText("Patient");
        }
        ObservableList<Appointment> appointmentsObservableList =
                new ObservableListWrapper<>(person.getPatientAppointments());
        appointments.setItems(appointmentsObservableList);
        appointments.setCellFactory(item -> new AppointmentListViewCell());
    }

    private void setMedicationDetails() {
        if (person.isPatient()) {
            Patient pt = (Patient) person;
            this.patient = pt;
            prescriptionBox.setVisible(true);
            StringBuilder prescriptionText = new StringBuilder();

            int i = 1;
            for (Prescription prescription: patient.getPrescriptions()) {
                prescriptionText.append(String.format("%d.  %s\n", i++, prescription.toString()));
            }
            medication.setText(prescriptionText.toString());
        }
        if (person.isDoctor()) {
            prescriptionBox.setVisible(false);
        }
    }

    class AppointmentListViewCell extends ListCell<Appointment> {
        @Override
        protected void updateItem(Appointment appointment, boolean empty) {
            super.updateItem(appointment, empty);
            if (empty || appointment == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new AppointmentCard(person, appointment, getIndex() + 1).getRoot());
            }
        }
    }
}
