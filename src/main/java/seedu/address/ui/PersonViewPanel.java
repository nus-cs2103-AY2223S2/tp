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

//code reuse https://github.com/AY2223S1-CS2103T-W16-3/tp/pull/112/files
/**
 * Panel containing the detailed info of person.
 */
public class PersonViewPanel extends UiPart<Region> {
    private static final String FXML = "PersonViewPanel.fxml";

    private final Person person;

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
    private Label medication;

    @FXML
    private VBox prescriptionBox;

    /**
     * Generates a Person View Panel.
     * @param person Person to generate the panel about.
     */
    public PersonViewPanel(Person person) {
        super(FXML);
        if (person == null) {
            this.person = null;
            return;
        }
        /*
        this.person = person;
        setPersonalDetails();
        setAppointmentDetails();
         */
        this.person = person;
        String sname = person.getName().toString();
        name.setText(sname);
        phone.setText(person.getPhone().toString());
        email.setText(person.getEmail().toString());
        nric.setText(person.getNric().toString());
        address.setText(person.getAddress().toString());

        //PersonInfoCard personInfoCard = new PersonInfoCard(person);

        /*if (person.getMedications().size() > 0) {
            medications.getChildren().add(new Label(person.getMedicationString()));
        }
         */
        setAppointmentDetails();
        setMedicationDetails();
    }

    private void setAppointmentDetails() {
        ObservableList<Appointment> appointmentsObservableList =
                new ObservableListWrapper<>(person.getPatientAppointments());
        appointments.setItems(appointmentsObservableList);
        appointments.setCellFactory(item -> new AppointmentListViewCell());
    }

    private void setMedicationDetails() {
        if (person.isPatient()) {
            Patient patient = (Patient) person;
            prescriptionBox.setVisible(true);
            //medication.setText(patient.getMedication().toString());
            StringBuilder prescriptionText = new StringBuilder("Prescription:\n");

            int i = 1;
            for (Prescription prescription: patient.getPrescriptions()) {
                prescriptionText.append(String.format("%d. %s\n", i++, prescription.toString()));
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
                setGraphic(new AppointmentCard(appointment, getIndex() + 1).getRoot());
            }
        }
    }
}
