package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;
import seedu.address.model.prescription.Prescription;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonInfoCard extends UiPart<Region> {
    private static final String FXML = "PersonInfoCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @javafx.fxml.FXML
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
    private Label nric;
    @FXML
    private Label email;
    @FXML
    private Label medication;
    @FXML
    private FlowPane tags;
    @FXML
    private Label appointments;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonInfoCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");

        if (person.isDoctor()) {
            name.setText("[Dr] " + person.getName().fullName);
        } else {
            name.setText(person.getName().fullName);
        }

        nric.setText(person.getNric().nric);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        if (person.isPatient()) {
            Patient patient = (Patient) person;
            StringBuilder prescriptionText = new StringBuilder("Prescription:\n");

            int i = 1;
            for (Prescription prescription: patient.getPrescriptions()) {
                prescriptionText.append(String.format("%d. %s\n", i++, prescription.toString()));
            }
            medication.setText(prescriptionText.toString());

            //medication.setText(patient.getMedication().value);
            appointments.setText("Appointments: \n" + patient.patientAppointmentstoString());
        }
        if (person.isDoctor()) {
            Doctor doctor = (Doctor) person;
            appointments.setText("Appointments: \n" + doctor.drAppointmentsToString());
        }

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonInfoCard)) {
            return false;
        }

        // state check
        PersonInfoCard card = (PersonInfoCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}
