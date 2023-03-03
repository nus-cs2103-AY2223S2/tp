package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Patient}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Patient patient;

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
    private Label birthDate;
    @FXML
    private Label gender;
    @FXML
    private Label ic;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PatientCode} with the given {@code Patient} and index to display.
     */
    public PersonCard(Patient patient, int displayedIndex) {
        super(FXML);
        this.patient = patient;
        id.setText(displayedIndex + ". ");
        name.setText(this.patient.getName().fullName);
        phone.setText(this.patient.getPhone().value);
        address.setText(this.patient.getAddress().value);
        email.setText(this.patient.getEmail().value);
        birthDate.setText(this.patient.getBirthDate().value);
        gender.setText(this.patient.getGender().value);
        ic.setText(this.patient.getIc().value);
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
                && patient.equals(card.patient);
    }
}
