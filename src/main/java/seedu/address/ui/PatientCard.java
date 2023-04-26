package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seedu.address.model.person.patient.Patient;
import seedu.address.model.tag.Tag;

/**
 * An UI component that displays the name, phone number, email and tags of a {@code Patient}.
 */
public class PatientCard extends ContactCard {

    private static final String FXML = "PatientListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Patient patient;

    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label email;

    /**
     * Creates a {@code PatientCard} with the given {@code Patient} and index to display.
     */
    public PatientCard(Patient patient, int displayedIndex) {
        super(FXML);
        this.patient = patient;
        id.setText(displayedIndex + ". ");
        name.setText(patient.getName().getValue());
        phone.setText(patient.getPhone().getValue());
        email.setText(patient.getEmail().getValue());
        patient.getTags().stream()
                .sorted(Comparator.comparing(Tag::getTagName))
                .forEach(this::addTagToFlowPane);
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
        PatientCard card = (PatientCard) other;
        return id.getText().equals(card.id.getText())
                && patient.equals(card.patient);
    }
}
